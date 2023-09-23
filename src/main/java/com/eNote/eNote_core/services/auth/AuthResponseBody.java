package com.eNote.eNote_core.services.auth;

import com.eNote.eNote_core.models.Role;

import java.util.Set;

/**
 * @author Alterranius
 */
public class AuthResponseBody {
    private String JWT_Token;
    private Boolean succeed;
    private Set<Role> roles;

    public String getJWT_Token() {
        return JWT_Token;
    }

    public AuthResponseBody() {
    }

    public AuthResponseBody(String JWT_Token, Boolean succeed, Set<Role> roles) {
        this.JWT_Token = JWT_Token;
        this.succeed = succeed;
        this.roles = roles;
    }

    public void setJWT_Token(String JWT_Token) {
        this.JWT_Token = JWT_Token;
    }

    public Boolean getSucceed() {
        return succeed;
    }

    public void setSucceed(Boolean succeed) {
        this.succeed = succeed;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
