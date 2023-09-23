package com.eNote.eNote_core.services.unit;

import com.eNote.eNote_core.dto.TeamDataDTO;
import com.eNote.eNote_core.interfaces.TeamManager;
import com.eNote.eNote_core.interfaces.TeamsShower;
import com.eNote.eNote_core.models.Role;
import com.eNote.eNote_core.models.Task;
import com.eNote.eNote_core.models.Team;
import com.eNote.eNote_core.repositories.postgres.RoleRepository;
import com.eNote.eNote_core.repositories.postgres.TaskRepository;
import com.eNote.eNote_core.repositories.postgres.TeamRepository;
import com.eNote.eNote_core.services.unit.lib.UnitRoleSpawner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Alterranius
 */
@Service
public class TeamService implements TeamManager, TeamsShower {
    private final UnitRoleSpawner unitRoleSpawner;
    private final TeamRepository teamRepository;
    private final RoleRepository roleRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TeamService(UnitRoleSpawner unitRoleSpawner, TeamRepository teamRepository, RoleRepository roleRepository, TaskRepository taskRepository) {
        this.unitRoleSpawner = unitRoleSpawner;
        this.teamRepository = teamRepository;
        this.roleRepository = roleRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public String create(Team team) {
        List<Role> currentRoles = unitRoleSpawner.spawn();
        currentRoles.forEach(r -> r.setTeam(team));
        team.setRoles(currentRoles);
        teamRepository.save(team);
        roleRepository.saveAll(currentRoles);
        return "Успешно добавлено";
    }

    @Override
    public String delete(int id) {
        Optional<Team> currentTeam = teamRepository.findById(id);
        if (currentTeam.isPresent()) {
            teamRepository.delete(currentTeam.get());
            return "Успешно удалено";
        } else {
            return "Неактуальная команда";
        }
    }

    @Override
    public String update(int id, Team team) {
        Optional<Team> currentTeam = teamRepository.findById(id);
        if (currentTeam.isPresent()) {
            currentTeam.get().setName(team.getName());
            currentTeam.get().setDescription(team.getDescription());
            currentTeam.get().setProduct(team.getProduct());
            currentTeam.get().setDepart(team.getDepart());
            teamRepository.save(currentTeam.get());
            return "Успешно обновлено";
        } else {
            return "Неактуальная команда";
        }
    }

    @Override
    public List<Team> getTeams(int id) {
        return teamRepository.findTeamsByDepart_Id(id);
    }

    @Override
    public List<Team> getProjectTeams(int id) {
        return teamRepository.findTeamsByDepart_Project_Id(id);
    }

    @Override
    @Transactional
    public TeamDataDTO getTasksStats(int id) {
        List<Task> tasks = taskRepository.findTasksByTeam_Id(id);
        int completed = tasks.stream().filter(t -> t.getStatus().getId() == 1).toList().size();
        int failed = tasks.stream().filter(t -> t.getStatus().getId() == 2).toList().size();
        int inWork = tasks.stream().filter(t -> t.getStatus().getId() == 0).toList().size();
        int unsigned = tasks.stream().filter(t -> t.getStatus().getId() == 3).toList().size();
        return new TeamDataDTO(
            String.valueOf(completed),
                String.valueOf(failed),
                String.valueOf(inWork),
                String.valueOf(unsigned)
        );
    }
}
