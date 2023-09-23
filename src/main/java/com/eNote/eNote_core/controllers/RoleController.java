package com.eNote.eNote_core.controllers;

import com.eNote.eNote_core.services.config.UserTarget;
import com.eNote.eNote_core.dto.UnitAccountsDTO;
import com.eNote.eNote_core.interfaces.RoleManager;
import com.eNote.eNote_core.interfaces.RolesShower;
import com.eNote.eNote_core.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alterranius
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleManager roleManager;
    private final RolesShower rolesShower;
    private final UserTarget userTarget;

    @Autowired
    public RoleController(RoleManager roleManager, RolesShower rolesShower, UserTarget userTarget) {
        this.roleManager = roleManager;
        this.rolesShower = rolesShower;
        this.userTarget = userTarget;
    }

    @PostMapping("/set/{id}")
    public ResponseEntity<HttpStatus> setTask(@RequestBody Role role,
                                              @PathVariable("id") int id) {
        try {
            roleManager.set(role, id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PostMapping("/reset/{id}")
    public ResponseEntity<HttpStatus> resetTask(@RequestBody Role role,
                                                @PathVariable("id") int id) {
        try {
            roleManager.reset(role, id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/project/{id}")
    @ResponseBody
    public List<UnitAccountsDTO> getProjectRoles(@PathVariable("id") int id) {
        try {
            return rolesShower.getProjectAccounts(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/depart/{id}")
    @ResponseBody
    public List<UnitAccountsDTO> getDepartRoles(@PathVariable("id") int id) {
        try {
            return rolesShower.getDepartAccounts(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/team/{id}")
    @ResponseBody
    public List<UnitAccountsDTO> getTeamRoles(@PathVariable("id") int id) {
        try {
            return rolesShower.getTeamAccounts(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/projectRoles/{id}")
    @ResponseBody
    public List<Role> getProjectComboRoles(@PathVariable("id") int id) {
        try {
            return rolesShower.getProjectRoles(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/departRoles/{id}")
    @ResponseBody
    public List<Role> getDepartComboRoles(@PathVariable("id") int id) {
        try {
            return rolesShower.getDepartRoles(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/teamRoles/{id}")
    @ResponseBody
    public List<Role> getTeamComboRoles(@PathVariable("id") int id) {
        try {
            return rolesShower.getTeamRoles(id);
        } catch (Exception e) {
            return null;
        }
    }
}
