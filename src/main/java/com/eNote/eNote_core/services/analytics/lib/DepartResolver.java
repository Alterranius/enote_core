package com.eNote.eNote_core.services.analytics.lib;

import com.eNote.eNote_core.models.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Alterranius
 */
public class DepartResolver implements UnitResolver {
    @Override
    public Map<Account, Integer> getAccountsMap(Set<Account> accounts, int id) {
        Map<Account, Integer> accountIntegerMap = new HashMap<>();
        accounts.forEach(a ->
                accountIntegerMap.put(a,
                        a.getTasks().stream().filter(t -> t.getTeam().getDepart().getId() == id).toList().size()));
        return accountIntegerMap;
    }
}
