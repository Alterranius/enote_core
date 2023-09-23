package com.eNote.eNote_core.services.unit.util;

import com.eNote.eNote_core.models.Team;
import com.eNote.eNote_core.services.unit.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Alterranius
 */
@Component
public class TeamValidator implements Validator {
    private final TeamService teamService;

    @Autowired
    public TeamValidator(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Team.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Team team = (Team) target;
    }
}
