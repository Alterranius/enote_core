package com.eNote.eNote_core.controllers;

import com.eNote.eNote_core.services.config.UserTarget;
import com.eNote.eNote_core.dto.DepartDataDTO;
import com.eNote.eNote_core.interfaces.DepartManager;
import com.eNote.eNote_core.interfaces.DepartsShower;
import com.eNote.eNote_core.models.Depart;
import com.eNote.eNote_core.services.unit.util.DepartValidator;
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
@RequestMapping("/depart")
public class DepartController {
    private final DepartManager departManager;
    private final DepartsShower departsShower;
    private final DepartValidator departValidator;
    private final UserTarget userTarget;

    @Autowired
    public DepartController(DepartManager departManager, DepartsShower departsShower, DepartValidator departValidator, UserTarget userTarget) {
        this.departManager = departManager;
        this.departsShower = departsShower;
        this.departValidator = departValidator;
        this.userTarget = userTarget;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createDepart(@RequestBody Depart depart, BindingResult bindingResult) {
        departValidator.validate(depart, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        departManager.create(depart);
        return ResponseEntity.created(URI.create("")).body(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateDepart(@RequestBody Depart depart,
                                                   @PathVariable("id") int id,
                                                   BindingResult bindingResult) {
        departValidator.validate(depart, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        departManager.update(id, depart);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDepart(@PathVariable("id") int id) {
        try {
            departManager.delete(id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public List<Depart> getDeparts(@PathVariable("id") int id) {
        try {
            return departsShower.getDeparts(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/stats/{id}")
    public DepartDataDTO getTasksStats(@PathVariable("id") int id) {
        try {
            return departsShower.getTasksStats(id);
        } catch (Exception e) {
            return null;
        }
    }
}
