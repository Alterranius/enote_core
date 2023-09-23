package com.eNote.eNote_core.services.unit;

import com.eNote.eNote_core.dto.DepartDataDTO;
import com.eNote.eNote_core.interfaces.DepartManager;
import com.eNote.eNote_core.interfaces.DepartsShower;
import com.eNote.eNote_core.models.Depart;
import com.eNote.eNote_core.models.Role;
import com.eNote.eNote_core.models.Task;
import com.eNote.eNote_core.repositories.postgres.DepartRepository;
import com.eNote.eNote_core.repositories.postgres.RoleRepository;
import com.eNote.eNote_core.repositories.postgres.TaskRepository;
import com.eNote.eNote_core.services.unit.lib.UnitRoleSpawner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Alterranius
 */
@Service
public class DepartService implements DepartsShower, DepartManager {
    private final UnitRoleSpawner unitRoleSpawner;
    private final DepartRepository departRepository;
    private final RoleRepository roleRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public DepartService(UnitRoleSpawner unitRoleSpawner, DepartRepository departRepository, RoleRepository roleRepository, TaskRepository taskRepository) {
        this.unitRoleSpawner = unitRoleSpawner;
        this.departRepository = departRepository;
        this.roleRepository = roleRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public String create(Depart depart) {
        List<Role> currentRoles = unitRoleSpawner.spawn();
        currentRoles.forEach(r -> r.setDepart(depart));
        depart.setRoles(currentRoles);
        departRepository.save(depart);
        roleRepository.saveAll(currentRoles);
        return "Успешно добавлено";
    }

    @Override
    public String delete(int id) {
        Optional<Depart> currentDepart = departRepository.findById(id);
        if (currentDepart.isPresent()) {
            departRepository.delete(currentDepart.get());
            return "Успешно удалено";
        } else {
            return "Неактуальный отдел";
        }
    }

    @Override
    public String update(int id, Depart depart) {
        Optional<Depart> currentDepart = departRepository.findById(id);
        if (currentDepart.isPresent()) {
            currentDepart.get().setDescription(depart.getDescription());
            currentDepart.get().setName(depart.getName());
            currentDepart.get().setProduct(depart.getProduct());
            currentDepart.get().setProject(depart.getProject());
            departRepository.save(currentDepart.get());
            return "Успешно обновлено";
        } else {
            return "Неактуальный отдел";
        }
    }

    @Override
    public List<Depart> getDeparts(int id) {
        return departRepository.findDepartsByProject_Id(id);
    }

    @Override
    public DepartDataDTO getTasksStats(int id) {
        List<Task> tasks = taskRepository.findTasksByTeam_Depart_Id(id);
        int completed = tasks.stream().filter(t -> t.getStatus().getId() == 1).toList().size();
        int failed = tasks.stream().filter(t -> t.getStatus().getId() == 2).toList().size();
        int inWork = tasks.stream().filter(t -> t.getStatus().getId() == 0).toList().size();
        int unsigned = tasks.stream().filter(t -> t.getStatus().getId() == 3).toList().size();
        return new DepartDataDTO(
                String.valueOf(completed),
                String.valueOf(failed),
                String.valueOf(inWork),
                String.valueOf(unsigned)
        );
    }
}
