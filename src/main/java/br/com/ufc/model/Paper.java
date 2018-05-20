package br.com.ufc.model;

import org.springframework.security.core.GrantedAuthority;

public enum Paper implements GrantedAuthority {

    ORGANIZER("ORGANIZER"), PARTICIPANT("PARTICIPANT");

    private String description;

    private Paper(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + this.description;
    }
}
