package com.hlstudios.payment.models;

import lombok.Getter;

public class JwtResponse {

    @Getter
    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }

}
