package com.eNote.eNote_core.services.unit.lib;

import com.eNote.eNote_core.models.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alterranius
 */
@Component
public class UnitRoleSpawner {
    public List<Role> spawn() {
        Role USER = new Role("USER", "#45455D", "Базовая роль пользователя", null);
        Role MODERATOR = new Role("MODERATOR", "#45455D", "Базовая роль модератора", null);
        Role ADMIN = new Role("ADMIN", "#45455D", "Базовая роль администратора", null);
        List<Role> result = new ArrayList<>();
        result.add(USER);
        result.add(MODERATOR);
        result.add(ADMIN);
        return result;
    }
}
