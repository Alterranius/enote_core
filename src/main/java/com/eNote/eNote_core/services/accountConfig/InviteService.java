package com.eNote.eNote_core.services.accountConfig;

import com.eNote.eNote_core.interfaces.InviteManager;
import com.eNote.eNote_core.interfaces.InvitesShower;
import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.Invite;
import com.eNote.eNote_core.models.Role;
import com.eNote.eNote_core.repositories.mongo.InviteRepository;
import com.eNote.eNote_core.repositories.postgres.AccountRepository;
import com.eNote.eNote_core.repositories.postgres.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Alterranius
 */
@Service
public class InviteService implements InvitesShower, InviteManager {
    private final InviteRepository inviteRepository;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public InviteService(InviteRepository inviteRepository, AccountRepository accountRepository, RoleRepository roleRepository) {
        this.inviteRepository = inviteRepository;
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public String acceptInvite(String id) {
        Optional<Invite> currentInvite = inviteRepository.findById(id);
        if (currentInvite.isPresent()) {
            Optional<Account> account = accountRepository.findById(Integer.parseInt(currentInvite.get().getAccount()));
            if (account.isEmpty()) {
                return "Неактуальный аккаунт";
            }
            Optional<Role> initialRole =  roleRepository.findRoleByProject_IdAndName(
                    Integer.parseInt(currentInvite.get().getProject()),
                    "USER");
            if (initialRole.isEmpty()) {
                return "Неактуальная роль";
            }
            account.get().getRoles()
                    .add(initialRole.get());
            initialRole.get().getAccounts().add(account.get());
            accountRepository.save(account.get());
            currentInvite.get().setStatus("Accepted");
            inviteRepository.save(currentInvite.get());
            return "Успешно обновлено";
        } else {
            return "Неактуальное приглашение";
        }
    }

    @Override
    public String declineInvite(String id) {
        Optional<Invite> currentInvite = inviteRepository.findById(id);
        if (currentInvite.isPresent()) {
            currentInvite.get().setStatus("Declined");
            inviteRepository.save(currentInvite.get());
            return "Успешно обновлено";
        } else {
            return "Неактуальное приглашение";
        }
    }

    @Override
    public String create(String project_id, String account_id) {
        if (account_id.isBlank() ||account_id.isEmpty()) {
            return "Неверный пользователь";
        }
        Invite invite = new Invite();
        invite.setAccount(account_id);
        invite.setProject(project_id);
        invite.setStatus("Ожидает");
        invite.setActed(Boolean.FALSE.toString());
        invite.setCreatedAt(new Date().toString());
        inviteRepository.insert(invite);
        return "Успешно добавлено";
    }

    @Override
    public String delete(String id) {
        Optional<Invite> currentInvite = inviteRepository.findById(id);
        if (currentInvite.isPresent()) {
            inviteRepository.delete(currentInvite.get());
            return "Успешно удалено";
        } else {
            return "Неактуальное приглашение";
        }
    }

    @Override
    public List<Invite> getInvitesByAccount(int account_id) {
        System.out.println(account_id);
        return inviteRepository.findInvitesByAccount(String.valueOf(account_id));
    }

    @Override
    public List<Invite> getInvitesByProject(int project_id) {
        return inviteRepository.findInvitesByProject(String.valueOf(project_id));
    }
}
