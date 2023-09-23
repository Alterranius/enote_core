package com.eNote.eNote_core.services.task;

import com.eNote.eNote_core.interfaces.TaskCompleter;
import com.eNote.eNote_core.interfaces.TaskDelegator;
import com.eNote.eNote_core.interfaces.TaskManager;
import com.eNote.eNote_core.interfaces.TasksShower;
import com.eNote.eNote_core.models.*;
import com.eNote.eNote_core.repositories.postgres.*;
import com.eNote.eNote_core.services.task.lib.RateResolver;
import com.eNote.eNote_core.services.task.lib.SpeedResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Alterranius
 */
@Service
public class TaskService implements TaskCompleter, TaskDelegator, TasksShower, TaskManager {
    private final TaskRepository taskRepository;
    private final AccountRepository accountRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final TaskCategoryRepository taskCategoryRepository;
    private final TaskTypeRepository taskTypeRepository;
    private final SpeedResolver speedResolver;
    private final RateResolver rateResolver;

    @Autowired
    public TaskService(TaskRepository taskRepository, AccountRepository accountRepository, TaskStatusRepository taskStatusRepository, TaskCategoryRepository taskCategoryRepository, TaskTypeRepository taskTypeRepository, SpeedResolver speedResolver, RateResolver rateResolver) {
        this.taskRepository = taskRepository;
        this.accountRepository = accountRepository;
        this.taskStatusRepository = taskStatusRepository;
        this.taskCategoryRepository = taskCategoryRepository;
        this.taskTypeRepository = taskTypeRepository;
        this.speedResolver = speedResolver;
        this.rateResolver = rateResolver;
    }

    @Override
    public String setStatus(Task task, TaskStatus status) {
        Optional<Task> currentTask = taskRepository.findById(task.getId());
        if (currentTask.isPresent()) {
            currentTask.get().setStatus(status);
            taskRepository.save(currentTask.get());
            return "Сохранено";
        } else {
            return "Неактуальная задача";
        }
    }

    @Override
    @Transactional
    public String complete(int id) {
        Optional<Task> currentTask = taskRepository.findById(id);
        if (currentTask.isPresent()) {
            currentTask.get().setStatus(taskStatusRepository.findTaskStatusByName("Выполнена").get());
            currentTask.get().setCompletedAt(new Date());
            currentTask.get().getAccount().setSpeed(speedResolver.averageCumulate(
                    currentTask.get().getAccount().getSpeed(),
                    currentTask.get().getAccount().getCompleted(),
                    currentTask.get().getCompletedAt(),
                    currentTask.get().getDelegatedAt()
            ));
            int currentCompleted = currentTask.get().getAccount().getCompleted() + 1;
            int currentWorkable = currentTask.get().getAccount().getWorkable() - 1;
            currentTask.get().getAccount().setCompleted(currentCompleted);
            currentTask.get().getAccount().setWorkable(currentWorkable);
            currentTask.get().getAccount().setRate(rateResolver.calculateRating(currentCompleted,
                    currentTask.get().getAccount().getFailed()));
            taskRepository.save(currentTask.get());
            return "Выполнено";
        } else {
            return "Неактуальная задача";
        }
    }

    @Override
    @Transactional
    public String fail(int id) {
        Optional<Task> currentTask = taskRepository.findById(id);
        if (currentTask.isPresent()) {
            currentTask.get().setStatus(taskStatusRepository.findTaskStatusByName("Провалена").get());
            currentTask.get().setCompletedAt(new Date());
            int currentFailed = currentTask.get().getAccount().getFailed() + 1;
            int currentWorkable = currentTask.get().getAccount().getWorkable() - 1;
            currentTask.get().getAccount().setFailed(currentFailed);
            currentTask.get().getAccount().setWorkable(currentWorkable);
            currentTask.get().getAccount().setRate(rateResolver.calculateRating(currentTask.get().getAccount().getCompleted(),
                    currentFailed));
            taskRepository.save(currentTask.get());
            return "Выполнено";
        } else {
            return "Неактуальная задача";
        }
    }

    @Override
    @Transactional
    public String set(int task_id, int account_id) {
        Optional<Task> currentTask = taskRepository.findById(task_id);
        Optional<Account> currentAccount = accountRepository.findById(account_id);
        if (currentTask.isPresent() && currentAccount.isPresent()) {
            currentTask.get().setAccount(currentAccount.get());
            currentAccount.get().getTasks().add(currentTask.get());
            int currentWorkable = currentAccount.get().getWorkable() + 1;
            currentAccount.get().setWorkable(currentWorkable);
            currentTask.get().setDelegatedAt(new Date());
            currentTask.get().setStatus(taskStatusRepository.findTaskStatusByName("В работе").get());
            taskRepository.save(currentTask.get());
            return "Назначено";
        } else {
            return "Неактуальный пользователь или задача";
        }
    }

    @Override
    @Transactional
    public String reset(int task_id, int account_id) {
        Optional<Task> currentTask = taskRepository.findById(task_id);
        Optional<Account> currentAccount = accountRepository.findById(account_id);
        if (currentTask.isPresent() && currentAccount.isPresent()) {
            currentTask.get().setAccount(null);
            currentAccount.get().getTasks().remove(currentTask.get());
            int currentWorkable = currentAccount.get().getWorkable() - 1;
            currentAccount.get().setWorkable(currentWorkable);
            currentTask.get().setDelegatedAt(null);
            currentTask.get().setStatus(taskStatusRepository.findTaskStatusByName("Не назначена").get());
            taskRepository.save(currentTask.get());
            accountRepository.save(currentAccount.get());
            return "Снято";
        } else {
            return "Неактуальный пользователь или задача";
        }
    }

    @Override
    public List<Task> getTasks(int id) {
        return taskRepository.findTasksByTeam_Depart_Project_Id(id).stream().filter(t ->
                t.getCompletedAt() != null && t.getStatus().getId() == 2).toList();
    }

    @Override
    public List<Task> getTasksByProject(int id) {
        return taskRepository.findTasksByTeam_Depart_Project_Id(id).stream().filter(t ->
                t.getCompletedAt() == null || t.getStatus().getId() != 2).toList();
    }

    @Override
    public List<Task> getTasksUnsigned(int id) {
        return taskRepository.findTasksByTeam_Depart_Project_IdAndStatus_Id(id, taskStatusRepository.findTaskStatusByName("Не назначена").get().getId());
    }

    @Override
    public List<Task> getTasksInWork(int id) {
        return taskRepository.findTasksByTeam_Depart_Project_IdAndStatus_Id(id, taskStatusRepository.findTaskStatusByName("В работе").get().getId());
    }

    @Override
    public List<Task> getMyTasks(int project_id, int account_id) {
        return taskRepository.findTasksByTeam_Depart_Project_IdAndAccount_Id(project_id, account_id).stream().filter(t ->
                t.getStatus().getId() != 2 && t.getStatus().getId() != 3).toList();
    }

    @Override
    public List<Task> getTaskHistory(int id) {
        return taskRepository.findTasksByTeam_Depart_Project_Id(id);
    }

    @Override
    public Optional<Task> getTaskDetails(int id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<TaskCategory> getCategories() {
        return taskCategoryRepository.findAll();
    }

    @Override
    public List<TaskType> getTypes() {
        return taskTypeRepository.findAll();
    }

    @Override
    public List<TaskStatus> getStatuses() {
        return taskStatusRepository.findAll();
    }

    @Override
    public String create(Task task) {
        if (task.getAccount() == null) {
            task.setStatus(taskStatusRepository.findTaskStatusByName("Не назначена").get());
            taskRepository.save(task);
        } else  {
            Optional<Account> currentAccount = accountRepository.findById(task.getAccount().getId());
            task.setAccount(currentAccount.get());
            if (currentAccount.get().getTasks() == null) {
                currentAccount.get().setTasks(new ArrayList<>());
            }
            currentAccount.get().getTasks().add(task);
            currentAccount.get().setWorkable(currentAccount.get().getWorkable() + 1);
            task.setDelegatedAt(new Date());
            taskRepository.save(task);
            accountRepository.save(currentAccount.get());
        }
        return "Добавлено успешно";
    }

    @Override
    public String update(int id, Task task) {
        Optional<Task> currentTask = taskRepository.findById(id);
        if (currentTask.isPresent()) {
            currentTask.get().setStatus(task.getStatus());
            currentTask.get().setName(task.getName());
            currentTask.get().setDescription(task.getDescription());
            currentTask.get().setCategory(task.getCategory());
            currentTask.get().setCode(task.getCode());
            currentTask.get().setPriority(task.getPriority());
            currentTask.get().setType(task.getType());
            currentTask.get().setTeam(task.getTeam());
            taskRepository.save(currentTask.get());
            return "Успешно обновлено";
        } else {
            return "Неактуальная задача";
        }
    }

    @Override
    public String delete(int id) {
        Optional<Task> currentTask = taskRepository.findById(id);
        if (currentTask.isPresent()) {
            taskRepository.delete(currentTask.get());
            return "Успешно удалено";
        } else {
            return "Неактуальная задача";
        }
    }
}
