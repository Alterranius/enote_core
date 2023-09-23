package com.eNote.eNote_core.controllers;

import com.eNote.eNote_core.services.config.UserTarget;
import com.eNote.eNote_core.dto.TeamDataDTO;
import com.eNote.eNote_core.interfaces.TeamManager;
import com.eNote.eNote_core.interfaces.TeamsShower;
import com.eNote.eNote_core.models.Team;
import com.eNote.eNote_core.services.unit.util.TeamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * @author Alterranius
 */
@RestController
@RequestMapping("/team")
public class TeamController {
    private final TeamManager teamManager;
    private final TeamsShower teamsShower;
    private final TeamValidator teamValidator;
    private final UserTarget userTarget;

    @Autowired
    public TeamController(TeamManager teamManager, TeamsShower teamsShower, TeamValidator teamValidator, UserTarget userTarget) {
        this.teamManager = teamManager;
        this.teamsShower = teamsShower;
        this.teamValidator = teamValidator;
        this.userTarget = userTarget;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createTeam(@RequestBody Team team, BindingResult bindingResult) {
        teamValidator.validate(team, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        teamManager.create(team);
        return ResponseEntity.created(URI.create("")).body(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateTeam(@RequestBody Team team,
                                                   @PathVariable("id") int id,
                                                   BindingResult bindingResult) {
        teamValidator.validate(team, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        teamManager.update(id, team);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable("id") int id) {
        try {
            teamManager.delete(id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public List<Team> getTeams(@PathVariable("id") int id) {
        try {
            return teamsShower.getTeams(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/stats/{id}")
    @ResponseBody
    public TeamDataDTO getTasksStats(@PathVariable("id") int id) {
        try {
            return teamsShower.getTasksStats(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/project/{id}")
    @ResponseBody
    public List<Team> getProjectTeams(@PathVariable("id") int id) {
        try {
            return teamsShower.getProjectTeams(id);
        } catch (Exception e) {
            return null;
        }
    }
}
