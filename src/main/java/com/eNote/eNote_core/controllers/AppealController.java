package com.eNote.eNote_core.controllers;

import com.eNote.eNote_core.services.config.UserTarget;
import com.eNote.eNote_core.dto.AppealDTO;
import com.eNote.eNote_core.interfaces.AccountsShower;
import com.eNote.eNote_core.interfaces.AppealManager;
import com.eNote.eNote_core.interfaces.AppealsShower;
import com.eNote.eNote_core.models.Appeal;
import com.eNote.eNote_core.services.appeal.AppealService;
import com.eNote.eNote_core.services.appeal.util.AppealValidator;
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
@RequestMapping("/appeal")
public class AppealController {
    private final AppealManager appealManager;
    private final AppealsShower appealsShower;
    private final AccountsShower accountsShower;
    private final AppealValidator appealValidator;
    private final UserTarget userTarget;

    @Autowired
    public AppealController(AppealService appealService, AccountsShower accountsShower, AppealValidator appealValidator, UserTarget userTarget) {
        this.appealManager = appealService;
        this.appealsShower = appealService;
        this.accountsShower = accountsShower;
        this.appealValidator = appealValidator;
        this.userTarget = userTarget;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createAppeal(@RequestBody AppealDTO appealDTO) {
        Appeal appeal = convertFromDto(appealDTO);
        appealManager.create(appeal, String.valueOf(userTarget.getTokenUser().getId()));
        return ResponseEntity.created(URI.create("")).body(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateAppeal(@RequestBody Appeal appeal,
                                                   @PathVariable("id") String id,
                                                   BindingResult bindingResult) {
        appealValidator.validate(appeal, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        appealManager.update(id, appeal);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAppeal(@PathVariable("id") String id) {
        appealManager.recall(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/getAll/{id}")
    @ResponseBody
    public List<AppealDTO> getAppeals(@PathVariable("id") String id) {
        List<Appeal> appeals = appealsShower.getAppeals(id);
        List<AppealDTO> result = new ArrayList<>();
        for (Appeal a : appeals) {
            try {
                result.add(convertToDto(a));
            } catch (Exception ignored) {
            }
        }
        return result;
    }

    private Appeal convertFromDto(AppealDTO appealDTO) {
        return new Appeal(
                appealDTO.getId(),
                appealDTO.getHead(),
                appealDTO.getContent(),
                appealDTO.getCreatedAt(),
                appealDTO.getIsResponse(),
                null,
                String.valueOf(appealDTO.getReceiver().getId())
        );
    }
    private AppealDTO convertToDto(Appeal appeal) {
        return new AppealDTO(
                appeal.getId(),
                appeal.getHead(),
                appeal.getContent(),
                appeal.getCreatedAt(),
                appeal.getIsResponse(),
                accountsShower.getAccount(Integer.parseInt(appeal.getSender())).get(),
                accountsShower.getAccount(Integer.parseInt(appeal.getReceiver())).get()
        );
    }
}
