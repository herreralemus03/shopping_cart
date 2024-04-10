package com.hlstudios.products.models;

import lombok.Getter;

public class JwtResponse {

    @Getter
    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }

}
