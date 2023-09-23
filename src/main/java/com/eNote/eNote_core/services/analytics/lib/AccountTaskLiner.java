package com.eNote.eNote_core.services.analytics.lib;

import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.Project;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Alterranius
 */
@Component
public class AccountTaskLiner {
    public Map<String, String> getAccountTaskLine(Map<Account, Integer> accountTasks) {
        Map<String, String> result = new LinkedHashMap<>();
        List<Map.Entry<Account, Integer>> accountTasksList = new ArrayList<>(accountTasks.entrySet());
        accountTasksList.sort(Map.Entry.comparingByValue());
        for (Map.Entry<Account, Integer> entry : accountTasksList) {
            result.put(entry.getKey().getNickname(), entry.getValue().toString());
        }
        return result;
    }

    public Map<String, String> getProjectsTasksLine(Map<Project, Integer> projectTasks) {
        Map<String, String> result = new LinkedHashMap<>();
        List<Map.Entry<Project, Integer>> projectTasksList = new ArrayList<>(projectTasks.entrySet());
        projectTasksList.sort(Map.Entry.comparingByValue());
        for (Map.Entry<Project, Integer> entry : projectTasksList) {
            result.put(entry.getKey().getId() + " - " + entry.getKey().getName(), entry.getValue().toString());
        }
        return result;
    }
}
