package com.eNote.eNote_core.services.role;

import com.eNote.eNote_core.dto.UnitAccountsDTO;
import com.eNote.eNote_core.interfaces.RoleManager;
import com.eNote.eNote_core.interfaces.RolesShower;
import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.Role;
import com.eNote.eNote_core.repositories.postgres.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Alterranius
 */
@Service
public class RoleService implements RolesShower, RoleManager {
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, AccountRepository accountRepository) {
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public String set(Role role, int account_id) {
        Optional<Role> currentRole = roleRepository.findById(role.getId());
        Optional<Account> currentAccount = accountRepository.findById(account_id);
        if (currentRole.isPresent() && currentAccount.isPresent()) {
            currentRole.get().getAccounts().add(currentAccount.get());
            currentAccount.get().getRoles().add(currentRole.get());
            roleRepository.save(currentRole.get());
            return "Назначено";
        } else {
            return "Неактуальный аккаунт";
        }
    }

    @Override
    public String reset(Role role, int account_id) {
        Optional<Role> currentRole = roleRepository.findById(role.getId());
        Optional<Account> currentAccount = accountRepository.findById(account_id);
        if (currentRole.isPresent() && currentAccount.isPresent()) {
            currentRole.get().getAccounts().remove(currentAccount.get());
            currentAccount.get().getRoles().remove(currentRole.get());
            roleRepository.save(currentRole.get());
            return "Снято";
        } else {
            return "Неактуальный аккаунт";
        }
    }

    @Override
    public List<Role> getProjectRoles(int project_id) {
        return roleRepository.findRolesByProject_Id(project_id);
    }

    @Override
    public List<Role> getDepartRoles(int depart_id) {
        return roleRepository.findRolesByDepart_Id(depart_id);
    }

    @Override
    public List<Role> getTeamRoles(int team_id) {
        return roleRepository.findRolesByTeam_Id(team_id);
    }

    @Override
    public List<UnitAccountsDTO> getProjectAccounts(int id) {
        List<UnitAccountsDTO> result = new ArrayList<>();
        List<Account> accounts = accountRepository.findByRoles_Project_Id(id);
        List<Role> roles = roleRepository.findRolesByProject_Id(id);
        for (Account a : accounts) {
            for (Role r : roles) {
                if (a.getRoles().contains(r) || r.getAccounts().contains(a)) {
                    result.add(new UnitAccountsDTO(a, r));
                }
            }
        }
        return result;
    }

    @Override
    public List<UnitAccountsDTO> getDepartAccounts(int id) {
        List<UnitAccountsDTO> result = new ArrayList<>();
        List<Account> accounts = accountRepository.findByRoles_Depart_Id(id);
        List<Role> roles = roleRepository.findRolesByDepart_Id(id);
        for (Account a : accounts) {
            for (Role r : roles) {
                if (a.getRoles().contains(r) || r.getAccounts().contains(a)) {
                    result.add(new UnitAccountsDTO(a, r));
                }
            }
        }
        return result;
    }

    @Override
    public List<UnitAccountsDTO> getTeamAccounts(int id) {
        List<UnitAccountsDTO> result = new ArrayList<>();
        List<Account> accounts = accountRepository.findByRoles_Team_Id(id);
        List<Role> roles = roleRepository.findRolesByTeam_Id(id);
        for (Account a : accounts) {
            for (Role r : roles) {
                if (a.getRoles().contains(r) || r.getAccounts().contains(a)) {
                    result.add(new UnitAccountsDTO(a, r));
                }
            }
        }
        return result;
    }
}
