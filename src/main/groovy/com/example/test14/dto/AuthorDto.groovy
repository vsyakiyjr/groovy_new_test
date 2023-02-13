package com.example.test14.dto

class AuthorDto {
    private String username
    private String profileLink

    AuthorDto(String username, String profileLink) {
        this.username = username
        this.profileLink = profileLink
    }

    String getUsername() {
        return username
    }

    void setUsername(String username) {
        this.username = username
    }

    String getProfileLink() {
        return profileLink
    }

    void setProfileLink(String profileLink) {
        this.profileLink = profileLink
    }
}
