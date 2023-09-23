package com.eNote.eNote_core.services.appeal;

import com.eNote.eNote_core.interfaces.AppealManager;
import com.eNote.eNote_core.interfaces.AppealsShower;
import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.Appeal;
import com.eNote.eNote_core.repositories.mongo.AppealRepository;
import com.eNote.eNote_core.repositories.postgres.AccountRepository;
import com.eNote.eNote_core.repositories.postgres.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Alterranius
 */
@Service
public class AppealService implements AppealManager, AppealsShower {
    private final AppealRepository appealRepository;
    private final AccountRepository accountRepository;
    private final ProjectRepository projectRepository;
    private static final Logger appealLogger = LoggerFactory.getLogger(AppealService.class);

    @Autowired
    public AppealService(AppealRepository appealRepository, AccountRepository accountRepository, ProjectRepository projectRepository) {
        this.appealRepository = appealRepository;
        this.accountRepository = accountRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public String create(Appeal appeal, String account_id) {
        appeal.setSender(account_id);
        appealRepository.insert(appeal);
        appealLogger.debug("Appeal was sent");
        return "Сохранено";
    }

    @Override
    public String update(String id, Appeal appeal) {
        return null;
    }

    @Override
    public String recall(String id) {
        Optional<Appeal> currentAppeal = appealRepository.findById(id);
        if (currentAppeal.isPresent()) {
            currentAppeal.get().setSender(null);
            appealRepository.save(currentAppeal.get());
            appealLogger.debug("Appeal was recalled");
            return "Обращение отозвано";
        } else {
            return "Неактуальное обращение";
        }
    }

    @Override
    public String answer(Appeal request, Appeal response) {
        Optional<Appeal> currentAppeal = appealRepository.findById(request.getId());
        if (currentAppeal.isPresent()) {
            response.setIsResponse("true");
            response.setCreatedAt(new Date().toString());
            appealRepository.insert(response);
            appealLogger.debug("Appeal was answered");
            return "Сохранено";
        } else {
            return "Неактуальный запрос";
        }
    }

    @Override
    public List<Appeal> getAppeals(String project_id) {
        Set<Account> accounts = new HashSet<>( accountRepository.findByRoles_Project_Id(Integer.parseInt(project_id)));
        Set<Appeal> result = new HashSet<>();
        accounts.forEach(account ->
                result.addAll(appealRepository.findAppealsByReceiverOrSender(String.valueOf(account.getId()), String.valueOf(account.getId()))));
        return new ArrayList<>(result);
    }

    @Override
    public List<Appeal> getSentAppeals(Account account) {
        return new ArrayList<>(appealRepository.findAppealsBySender(String.valueOf(account.getId())));
    }

    @Override
    public List<Appeal> getReceivedAppeals(Account account) {
        return new ArrayList<>(appealRepository.findAppealsByReceiver(String.valueOf(account.getId())));
    }

    @Override
    public Optional<Appeal> getAppealDetails(Appeal appeal) {
        return appealRepository.findById(appeal.getId());
    }
}
