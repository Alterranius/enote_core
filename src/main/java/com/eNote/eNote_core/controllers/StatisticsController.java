package com.eNote.eNote_core.controllers;

import com.eNote.eNote_core.services.config.UserTarget;
import com.eNote.eNote_core.dto.AccountStatsDTO;
import com.eNote.eNote_core.dto.UnitStatsDTO;
import com.eNote.eNote_core.interfaces.UnitStatsShower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Alterranius
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final UnitStatsShower unitStatsShower;
    private final UserTarget userTarget;

    @Autowired
    public StatisticsController(UnitStatsShower unitStatsShower, UserTarget userTarget) {
        this.unitStatsShower = unitStatsShower;
        this.userTarget = userTarget;
    }

    @GetMapping("/team/{id}")
    public UnitStatsDTO getTeamStats(@PathVariable("id") int id) {
        try {
            Optional<UnitStatsDTO> result = unitStatsShower.getTeamStats(id);
            return result.orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/depart/{id}")
    public UnitStatsDTO getDepartStats(@PathVariable("id") int id) {
        try {
            Optional<UnitStatsDTO> result = unitStatsShower.getDepartStats(id);
            return result.orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/project/{id}")
    public UnitStatsDTO getProjectStats(@PathVariable("id") int id) {
        try {
            Optional<UnitStatsDTO> result = unitStatsShower.getProjectStats(id);
            return result.orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping
    public AccountStatsDTO getAccountStats() {
        try {
            Optional<AccountStatsDTO> result = unitStatsShower.getAccountStats(userTarget.getTokenUser().getId());
            return result.orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}
