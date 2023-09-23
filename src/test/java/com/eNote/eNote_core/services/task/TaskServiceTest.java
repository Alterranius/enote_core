package com.eNote.eNote_core.services.task;

import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.Task;
import com.eNote.eNote_core.models.TaskStatus;
import com.eNote.eNote_core.repositories.postgres.TaskRepository;
import com.eNote.eNote_core.repositories.postgres.TaskStatusRepository;
import com.eNote.eNote_core.services.task.lib.RateResolver;
import com.eNote.eNote_core.services.task.lib.SpeedResolver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.Optional;

/**
 * @author Alterranius
 */
@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskStatusRepository taskStatusRepository;
    @InjectMocks
    private TaskService taskService;
    @Spy
    private final SpeedResolver speedResolver = new SpeedResolver();
    @Spy
    private final RateResolver rateResolver = new RateResolver();

    @Test
    public void complete_numberOfTaskRepQueriesIsTwo_test() {
        int id = 0;
        Account account = new Account();
        Task task = new Task();
        task.setAccount(account);
        task.setDelegatedAt(new Date());
        String status = "Выполнена";

        Mockito.when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        Mockito.when(taskRepository.save(task)).thenReturn(task);
        Mockito.when(taskStatusRepository.findTaskStatusByName(status)).thenReturn(Optional.of(new TaskStatus()));

        taskService.complete(id);
        Mockito.verify(taskRepository, Mockito.atLeast(1)).findById(id);
        Mockito.verify(taskRepository, Mockito.atLeast(1)).save(task);
    }
}