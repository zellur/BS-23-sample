package com.zellur.brainstation23.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String access_token;
    private final String refresh_token;
    private final Long expires_in;
    private final String role;
    private String message;
    public JwtResponse(String access_token, String refresh_token, Long expires_in, String role, String message) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.expires_in = expires_in;
        this.role = role;
        this.message = message;
    }

}
