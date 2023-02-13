package com.example.test14.dto

class JwtResponse {
    private String jwtToken

    String getJwtToken() {
        return jwtToken
    }

    void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken
    }

    JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken
    }
}
