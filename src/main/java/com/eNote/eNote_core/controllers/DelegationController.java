package com.eNote.eNote_core.controllers;

import com.eNote.eNote_core.services.config.UserTarget;
import com.eNote.eNote_core.dto.DelegationDTO;
import com.eNote.eNote_core.interfaces.AccountsShower;
import com.eNote.eNote_core.interfaces.DelegationManager;
import com.eNote.eNote_core.interfaces.DelegationsShower;
import com.eNote.eNote_core.interfaces.TasksShower;
import com.eNote.eNote_core.models.Delegation;
import com.eNote.eNote_core.services.delegation.util.DelegationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alterranius
 */
@RestController
@RequestMapping("/delegation")
public class DelegationController {
    private final DelegationManager delegationManager;
    private final DelegationsShower delegationsShower;
    private final AccountsShower accountsShower;
    private final TasksShower tasksShower;
    private final DelegationValidator delegationValidator;
    private final UserTarget userTarget;

    @Autowired
    public DelegationController(DelegationManager delegationManager, DelegationsShower delegationsShower, AccountsShower accountsShower, TasksShower tasksShower, DelegationValidator delegationValidator, UserTarget userTarget) {
        this.delegationManager = delegationManager;
        this.delegationsShower = delegationsShower;
        this.accountsShower = accountsShower;
        this.tasksShower = tasksShower;
        this.delegationValidator = delegationValidator;
        this.userTarget = userTarget;
    }


    @PostMapping
    public ResponseEntity<HttpStatus> createDelegation(@RequestBody DelegationDTO delegationDTO) {
        Delegation delegation = convertFromDto(delegationDTO);
        delegationManager.create(delegation, String.valueOf(userTarget.getTokenUser().getId()));
        return ResponseEntity.created(URI.create("")).body(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateDelegation(@RequestBody Delegation delegation,
                                                   @PathVariable("id") String id,
                                                   BindingResult bindingResult) {
        delegationValidator.validate(delegation, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        delegationManager.update(id, delegation);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDelegation(@PathVariable("id") String id) {
        delegationManager.delete(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/getAll/{id}")
    @ResponseBody
    public List<DelegationDTO> getDelegations(@PathVariable("id") String id) {
        List<Delegation> delegations = delegationsShower.getDelegations(id);
        List<DelegationDTO> result = new ArrayList<>();
        for (Delegation d : delegations) {
            try {
                result.add(convertToDto(d));
            } catch (Exception ignored) {
            }
        }
        return result;
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<HttpStatus> completeDelegation(@PathVariable("id") String id) {
        delegationManager.complete(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    private Delegation convertFromDto(DelegationDTO delegationDTO) {
        return new Delegation(
                delegationDTO.getId(),
                delegationDTO.getHead(),
                delegationDTO.getContent(),
                delegationDTO.getCreatedAt(),
                delegationDTO.getCompletedAt(),
                null,
                String.valueOf(delegationDTO.getReceiver().getId()),
                String.valueOf(delegationDTO.getTask().getId())
        );
    }
    private DelegationDTO convertToDto(Delegation delegation) {
        return new DelegationDTO(
                delegation.getId(),
                delegation.getHead(),
                delegation.getContent(),
                delegation.getCreatedAt(),
                delegation.getCompletedAt(),
                accountsShower.getAccount(Integer.parseInt(delegation.getSender())).get(),
                accountsShower.getAccount(Integer.parseInt(delegation.getReceiver())).get(),
                tasksShower.getTaskDetails(Integer.parseInt(delegation.getTask())).get()
        );
    }
}
