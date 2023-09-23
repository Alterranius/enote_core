package com.eNote.eNote_core.services.auth;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @author Alterranius
 */
@Service
public class AdminService {
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ADMIN')")
    public void doAdminStaff() {
        System.out.println("admin is here");
    }
}
