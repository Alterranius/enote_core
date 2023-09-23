package com.eNote.eNote_core.services.unit;

import com.eNote.eNote_core.interfaces.ProjectManager;
import com.eNote.eNote_core.interfaces.ProjectsShower;
import com.eNote.eNote_core.models.*;
import com.eNote.eNote_core.repositories.postgres.*;
import com.eNote.eNote_core.services.unit.lib.UnitRoleSpawner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Alterranius
 */
@Service
public class ProjectService implements ProjectManager, ProjectsShower {
    private final UnitRoleSpawner unitRoleSpawner;
    private final ProjectRepository projectRepository;
    private final DepartRepository departRepository;
    private final TeamRepository teamRepository;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final MethodologyRepository methodologyRepository;
    private final static Logger projectLogger = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    public ProjectService(UnitRoleSpawner unitRoleSpawner, ProjectRepository projectRepository, DepartRepository departRepository, TeamRepository teamRepository, AccountRepository accountRepository, RoleRepository roleRepository, MethodologyRepository methodologyRepository) {
        this.unitRoleSpawner = unitRoleSpawner;
        this.projectRepository = projectRepository;
        this.departRepository = departRepository;
        this.teamRepository = teamRepository;
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.methodologyRepository = methodologyRepository;
    }


    @Override
    public String create(Project project, int account_id) {
        Optional<Account> account = accountRepository.findById(account_id);
        if (account.isEmpty()) {
            return "Неактуальный пользователь";
        }
        List<Role> currentRoles = unitRoleSpawner.spawn();
        currentRoles.forEach(r -> r.setProject(project));
        currentRoles.forEach(r -> r.setAccounts(new ArrayList<>()));
        currentRoles.forEach(r -> r.getAccounts().add(account.get()));
        currentRoles.forEach(r -> account.get().getRoles().add(r));
        project.setRoles(currentRoles);
        project.setMethodology(methodologyRepository.findById(1).get());
        projectRepository.save(project);
        roleRepository.saveAll(currentRoles);
        accountRepository.save(account.get());
        projectLogger.info("New project has been created by user with id={}", account_id);
        return "Успешно добавлено";
    }

    @Override
    public String update(int id, Project project) {
        Optional<Project> currentProject = projectRepository.findById(id);
        if (currentProject.isPresent()) {
            currentProject.get().setDeadline(project.getDeadline());
            currentProject.get().setName(project.getName());
            currentProject.get().setProduct(project.getProduct());
            currentProject.get().setHardDeadline(project.getHardDeadline());
            currentProject.get().setSpecialization(project.getSpecialization());
            currentProject.get().setPrefix(project.getPrefix());
            currentProject.get().setMission(project.getMission());
            currentProject.get().setMethodology(project.getMethodology());
            projectRepository.save(currentProject.get());
            return "Успешно сохранено";
        } else {
            return "Неактуальный проект";
        }
    }

    @Override
    public String delete(int id) {
        Optional<Project> currentProject = projectRepository.findById(id);
        if (currentProject.isPresent()) {
            projectRepository.delete(currentProject.get());
            projectLogger.info("Project with id={} was deleted", currentProject.get().getId());
            return "Успешно удалено";
        } else {
            return "Неактуальный проект";
        }
    }

    @Override
    @Transactional
    public String leave(int project_id, int account_id) {
        Optional<Account> account = accountRepository.findById(account_id);
        if (account.isEmpty()) {
            return "Неактуальный аккаунт";
        }
        List<Role> roles = roleRepository.findRolesByProject_Id(project_id);
        List<Role> departsRoles = new ArrayList<>();
        List<Role> teamsRoles = new ArrayList<>();
        List<Depart> projectDeparts = departRepository.findDepartsByProject_Id(project_id);
        List<Team> projectTeams = teamRepository.findTeamsByDepart_Project_Id(project_id);
        projectDeparts.forEach(d ->
                departsRoles.addAll(d.getRoles()));
        projectTeams.forEach(t ->
                teamsRoles.addAll(t.getRoles()));
        roles.addAll(departsRoles);
        roles.addAll(teamsRoles);
        roles = roles.stream().filter(r -> r.getAccounts().contains(account.get())).toList();
        for (Role role : roles) {
            role.getAccounts().remove(account.get());
            account.get().getRoles().remove(role);
        }
        accountRepository.save(account.get());
        return "Покинуто";
    }

    @Override
    public List<Depart> getDeparts(int id) {
        return departRepository.findDepartsByProject_Id(id);
    }

    @Override
    public List<Task> getActiveTasks(int id) {
        return null;
    }

    @Override
    public Optional<Project> getProjectDetails(int id) {
        return projectRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Project> getProjects(int id) {
        Optional<Account> currentAccount = accountRepository.findById(id);
        if (currentAccount.isEmpty()) {
            return null;
        }
        List<Role> roles = currentAccount.get().getRoles().stream().filter(r -> r.getProject() != null).toList();
        Set<Project> projects = new HashSet<>();
        roles.forEach(r -> projects.add(r.getProject()));
        return new ArrayList<>(projects);
    }

    @Override
    public List<Methodology> getMethodologies() {
        return methodologyRepository.findAll();
    }
}
