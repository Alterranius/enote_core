package com.eNote.eNote_core.services.analytics.lib;

import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.Role;
import com.eNote.eNote_core.models.Task;

import java.time.ZoneId;
import java.util.*;

/**
 * @author Alterranius
 */
public interface UnitResolver {
    int IN_WORK = 1;
    int COMPLETED = 2;
    int FAILED = 3;

    default int getCompleted(Collection<Task> tasks) {
        return tasks.stream().filter(t -> t.getStatus().getId() == COMPLETED).toList().size();
    }

    default int getFailed(Collection<Task> tasks) {
        return tasks.stream().filter(t -> t.getStatus().getId() == FAILED).toList().size();
    }

    default int getInWork(Collection<Task> tasks) {
        return tasks.stream().filter(t -> t.getStatus().getId() == IN_WORK).toList().size();
    }

    default int getWeek(Collection<Task> tasks, WeekPeeker weekPeeker) {
        return tasks.stream().filter(t -> t.getStatus().getId() == COMPLETED &&
                t.getCompletedAt().before(new Date()) &&
                t.getCompletedAt().after(weekPeeker.getPreviousWeek(new Date().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate()))).toList().size();
    }

    default double getEffectivency(int completed, int failed) {
        return failed != 0 ? ((double) Math.round(((double) completed / failed) * 100) / 100) : 1;
    }

    default HashMap<Date, Date> getDates(Collection<Task> tasks) {
        HashMap<Date, Date> dates = new HashMap<>();
        tasks.stream().filter(t -> t.getStatus().getId() == COMPLETED && t.getCompletedAt() != null && t.getDelegatedAt() != null)
                .forEach(t -> dates.put(t.getCompletedAt(), t.getDelegatedAt()));
        return dates;
    }

    default SortedMap<Date, Integer> getDateTaskPairs(Collection<Task> tasks) {
        SortedMap<Date, Integer> dateTaskPairs = new TreeMap<>();
        tasks.stream().filter(t -> t.getStatus().getId() == COMPLETED && t.getCompletedAt() != null)
                .forEach(t -> dateTaskPairs.put(t.getCompletedAt(), t.getId()));
        return dateTaskPairs;
    }

    default Set<Account> getAccounts(Collection<Role> roles) {
        Set<Account> accounts = new HashSet<>();
        roles.forEach(r -> accounts.addAll(r.getAccounts()));
        return accounts;
    }

    Map<Account, Integer> getAccountsMap(Set<Account> accounts, int id);
}
