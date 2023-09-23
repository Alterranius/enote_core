package com.eNote.eNote_core.services.analytics;

import com.eNote.eNote_core.dto.AccountStatsDTO;
import com.eNote.eNote_core.dto.UnitStatsDTO;
import com.eNote.eNote_core.interfaces.UnitStatsShower;
import com.eNote.eNote_core.models.*;
import com.eNote.eNote_core.repositories.mongo.DelegationRepository;
import com.eNote.eNote_core.repositories.postgres.*;
import com.eNote.eNote_core.services.analytics.lib.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Alterranius
 */
@Service
public class AnalyticsService implements UnitStatsShower {
    private final TaskRepository taskRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final DepartRepository departRepository;
    private final TeamRepository teamRepository;
    private final DelegationRepository delegationRepository;
    private final AccountTaskLiner accountTaskLiner;
    private final WeekPeeker weekPeeker;
    private final AvgSolver avgSolver;
    private final CompletedTaskLiner completedTaskLiner;
    private static final Logger analyticsLogger = LoggerFactory.getLogger(AnalyticsService.class);

    @Autowired
    public AnalyticsService(TaskRepository taskRepository, RoleRepository roleRepository, AccountRepository accountRepository, DepartRepository departRepository, TeamRepository teamRepository, DelegationRepository delegationRepository, AccountTaskLiner accountTaskLiner, WeekPeeker weekPeeker, AvgSolver avgSolver, CompletedTaskLiner completedTaskLiner) {
        this.taskRepository = taskRepository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.departRepository = departRepository;
        this.teamRepository = teamRepository;
        this.delegationRepository = delegationRepository;
        this.accountTaskLiner = accountTaskLiner;
        this.weekPeeker = weekPeeker;
        this.avgSolver = avgSolver;
        this.completedTaskLiner = completedTaskLiner;
    }

    @Override
    @Transactional
    public Optional<UnitStatsDTO> getTeamStats(int id) {
        long startTime = System.currentTimeMillis();
        List<Task> tasks = taskRepository.findTasksByTeam_Id(id);
        List<Role> roles = roleRepository.findRolesByTeam_Id(id);
        UnitResolver resolver = new TeamResolver();
        Optional<UnitStatsDTO> result = getUnitStatsDTO(getProperties(id, tasks, roles, resolver));
        analyticsLogger.info("Team-handling performed at {} ms", System.currentTimeMillis() - startTime);
        return result;
    }

    @Override
    @Transactional
    public Optional<UnitStatsDTO> getDepartStats(int id) {
        long startTime = System.currentTimeMillis();
        List<Task> tasks = taskRepository.findTasksByTeam_Depart_Id(id);
        List<Role> roles = roleRepository.findRolesByDepart_Id(id);
        UnitResolver resolver = new DepartResolver();
        Optional<UnitStatsDTO> result = getUnitStatsDTO(getProperties(id, tasks, roles, resolver));
        analyticsLogger.info("Depart-handling performed at {} ms", System.currentTimeMillis() - startTime);
        return result;
    }

    @Override
    @Transactional
    public Optional<UnitStatsDTO> getProjectStats(int id) {
        long startTime = System.currentTimeMillis();
        List<Task> tasks = taskRepository.findTasksByTeam_Depart_Project_Id(id);
        List<Role> roles = roleRepository.findRolesByProject_Id(id);
        UnitResolver resolver = new ProjectResolver();
        Optional<UnitStatsDTO> result = getUnitStatsDTO(getProperties(id, tasks, roles, resolver));
        analyticsLogger.info("Project-handling performed at {} ms", System.currentTimeMillis() - startTime);
        return result;
    }

    @Override
    @Transactional
    public Optional<AccountStatsDTO> getAccountStats(int id) {
        long startTime = System.currentTimeMillis();
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) {
            return Optional.empty();
        }
        int delegationsCompleted = delegationRepository.findDelegationsByReceiverOrSender(String.valueOf(id), String.valueOf(id))
                .stream().filter(d -> d.getCompletedAt() != null).toList().size();
        double effectivency = (double) Math.round(((double) account.get().getCompleted() / (account.get().getCompleted() + account.get().getFailed())) * 100) / 100;
        List<Role> roles = account.get().getRoles();
        Set<Project> projects = new HashSet<>();
        for (Role role : roles) {
            if (role.getProject() != null) {
                AddProject(projects, role.getProject());
            } else if (role.getDepart() != null) {
                AddProject(projects, role.getDepart().getProject());
            } else if (role.getTeam() != null) {
                AddProject(projects, role.getTeam().getDepart().getProject());
            }
        }
        Map<Project, Integer> projectTasksMap = new HashMap<>();
        projects.forEach(p -> projectTasksMap.put(p,
                taskRepository.findTasksByTeam_Depart_Project_IdAndAccount_Id(p.getId(), id).size()));
        Map<String, String> taskAccounts = accountTaskLiner.getProjectsTasksLine(projectTasksMap);
        return Optional.of(new AccountStatsDTO(String.valueOf(account.get().getCompleted()),
                String.valueOf(account.get().getFailed()), String.valueOf(account.get().getWorkable()),
                String.valueOf(account.get().getSpeed()), String.valueOf(delegationsCompleted),
                String.valueOf(effectivency),
                taskAccounts));
    }

    private void AddProject(Set<Project> projects, Project project) {
        projects.add(project);
    }

    private Map<String, Object> getProperties(int id, Collection<Task> tasks, Collection<Role> roles, UnitResolver resolver) {
        Map<String, Object> properties = new HashMap<>();
        int completed = resolver.getCompleted(tasks);
        int failed = resolver.getFailed(tasks);
        HashMap<Date, Date> dates = resolver.getDates(tasks);
        SortedMap<Date, Integer> dateTaskPairs = resolver.getDateTaskPairs(tasks);
        Set<Account> accounts = resolver.getAccounts(roles);
        Map<Account, Integer> accountIntegerMap = resolver.getAccountsMap(accounts, id);

        properties.put("completed", completed);
        properties.put("failed", failed);
        properties.put("inWork", resolver.getInWork(tasks));
        properties.put("week", resolver.getWeek(tasks, weekPeeker));
        properties.put("effectivency", resolver.getEffectivency(completed, failed));
        properties.put("dates", dates);
        properties.put("speed", avgSolver.getSpeed(dates));
        properties.put("dateTaskPairs", dateTaskPairs);
        properties.put("completedTasks", completedTaskLiner.getCompletedTaskLine(dateTaskPairs).entrySet().stream().collect(Collectors.toMap(entry->entry.getKey().toString(), Map.Entry::getValue)));
        properties.put("accounts", accounts);
        properties.put("accountIntegerMap", accountIntegerMap);
        properties.put("accountTasks", accountTaskLiner.getAccountTaskLine(accountIntegerMap).entrySet().stream().collect(Collectors.toMap(entry->entry.getKey().toString(), Map.Entry::getValue)));
        return properties;
    }

    private Optional<UnitStatsDTO> getUnitStatsDTO(Map<String, Object> properties) {
        return Optional.of(new UnitStatsDTO(
                String.valueOf(properties.get("completed")),
                String.valueOf(properties.get("failed")),
                String.valueOf(properties.get("inWork")),
                String.valueOf(properties.get("speed")),
                String.valueOf(properties.get("week")),
                String.valueOf(properties.get("effectivency")),
                (Map<String, String>) properties.get("accountTasks"),
                (Map<String, String>) properties.get("completedTasks")));
    }
}
