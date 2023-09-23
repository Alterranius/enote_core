package com.eNote.eNote_core.controllers;

import com.eNote.eNote_core.services.config.UserTarget;
import com.eNote.eNote_core.interfaces.InviteManager;
import com.eNote.eNote_core.interfaces.InvitesShower;
import com.eNote.eNote_core.models.Invite;
import com.eNote.eNote_core.services.accountConfig.util.InviteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * @author Alterranius
 */
@RestController
@RequestMapping("/invite")
public class InviteController {
    private final InviteManager inviteManager;
    private final InvitesShower invitesShower;
    private final InviteValidator inviteValidator;
    private final UserTarget userTarget;

    @Autowired
    public InviteController(InviteManager inviteManager, InvitesShower invitesShower, InviteValidator inviteValidator, UserTarget userTarget) {
        this.inviteManager = inviteManager;
        this.invitesShower = invitesShower;
        this.inviteValidator = inviteValidator;
        this.userTarget = userTarget;
    }

    @PostMapping("/{id}")
    public ResponseEntity<HttpStatus> sendInvite(@RequestBody int project_id,
                                                 @PathVariable("id") String id) {
        inviteManager.create(String.valueOf(project_id), id);
        return ResponseEntity.created(URI.create("")).body(HttpStatus.CREATED);
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<HttpStatus> acceptInvite(@PathVariable("id") String id) {
        try {
            inviteManager.acceptInvite(id);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/decline/{id}")
    public ResponseEntity<HttpStatus> declineInvite(@PathVariable("id") String id) {
        try {
            inviteManager.declineInvite(id);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @ResponseBody
    public List<Invite> getInvites() {
        try {
            return invitesShower.getInvitesByAccount(userTarget.getTokenUser().getId());
        } catch (Exception e) {
            return null;
        }
    }
}
