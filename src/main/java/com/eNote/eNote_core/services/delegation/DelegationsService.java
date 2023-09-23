package com.eNote.eNote_core.services.delegation;

import com.eNote.eNote_core.interfaces.DelegationManager;
import com.eNote.eNote_core.interfaces.DelegationsShower;
import com.eNote.eNote_core.models.*;
import com.eNote.eNote_core.repositories.mongo.DelegationRepository;
import com.eNote.eNote_core.repositories.postgres.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Alterranius
 */
@Service
public class DelegationsService implements DelegationManager, DelegationsShower {
    private final DelegationRepository delegationRepository;
    private final AccountRepository accountRepository;
    private static final Logger delegationLogger = LoggerFactory.getLogger(DelegationsService.class);

    @Autowired
    public DelegationsService(DelegationRepository delegationRepository, AccountRepository accountRepository) {
        this.delegationRepository = delegationRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public String create(Delegation delegation, String account_id) {
        delegation.setSender(account_id);
        delegationRepository.insert(delegation);
        delegationLogger.debug("Delegation was created");
        return "Добавлено";
    }

    @Override
    public String update(String id, Delegation delegation) {
        Optional<Delegation> currentDelegation = delegationRepository.findById(id);
        if (currentDelegation.isPresent()) {
            currentDelegation.get().setContent(delegation.getContent());
            currentDelegation.get().setHead(delegation.getHead());
            currentDelegation.get().setTask(delegation.getTask());
            delegationRepository.save(currentDelegation.get());
            return "Обновлено";
        } else {
            return "Неактуальное поручение";
        }
    }

    @Override
    public String delete(String id) {
        Optional<Delegation> currentDelegation = delegationRepository.findById(id);
        if (currentDelegation.isPresent()) {
            delegationRepository.delete(currentDelegation.get());
            delegationLogger.debug("Delegation was deleted");
            return "Удалено";
        } else {
            return "Неактуальное поручение";
        }
    }

    @Override
    public String complete(String id) {
        Optional<Delegation> currentDelegation = delegationRepository.findById(id);
        if (currentDelegation.isPresent()) {
            currentDelegation.get().setCompletedAt(new Date().toString());
            delegationRepository.save(currentDelegation.get());
            delegationLogger.debug("Delegation was completed");
            return "Выполнено";
        } else {
            return "Неактуальное поручение";
        }
    }

    @Override
    public List<Delegation> getDelegations(String project_id) {
        Set<Account> accounts = new HashSet<>( accountRepository.findByRoles_Project_Id(Integer.parseInt(project_id)));
        Set<Delegation> result = new HashSet<>();
        accounts.forEach(account ->
                result.addAll(delegationRepository.findDelegationsByReceiverOrSender(String.valueOf(account.getId()), String.valueOf(account.getId()))));
        return new ArrayList<>(result);
    }

    @Override
    public Optional<Delegation> getDelegationDetails(Delegation delegation) {
        return delegationRepository.findById(delegation.getId());
    }

    @Override
    public List<Delegation> getDelegationsByTask(Task task) {
        return new ArrayList<>(delegationRepository.findDelegationsByTask(String.valueOf(task.getId())));
    }

    @Override
    public List<Delegation> getDelegationsBySender(Account account) {
        return new ArrayList<>(delegationRepository.findDelegationsBySender(String.valueOf(account.getId())));
    }

    @Override
    public List<Delegation> getDelegationsByReceiver(Account account) {
        return new ArrayList<>(delegationRepository.findDelegationsByReceiver(String.valueOf(account.getId())));
    }
}
