package com.example.test14.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import javax.validation.constraints.NotEmpty


@Document
class User implements UserDetails {

    @Id
    private String id
    @NotEmpty(message = "Name should not be empty")
    private String username
    private String password

    private Set<Role> roles

    private List<Note> notes = new ArrayList<>()

    User() {
    }

    User(String id, String username, String password, Set<Role> roles, List<Note> notes) {
        this.id = id
        this.username = username
        this.password = password
        this.roles = roles
        this.notes = notes
    }

    String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }

    void setUsername(String username) {
        this.username = username
    }

    void setPassword(String password) {
        this.password = password
    }

    Set<Role> getRoles() {
        return roles
    }

    void setRoles(Set<Role> roles) {
        this.roles = roles
    }

    List<Note> getNotes() {
        return notes
    }

    void setNotes(List<Note> notes) {
        this.notes = notes
    }

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        return roles
    }

    @Override
    String getPassword() {
        return password
    }

    @Override
    String getUsername() {
        return username
    }

    @Override
    boolean isAccountNonExpired() {
        return true
    }

    @Override
    boolean isAccountNonLocked() {
        return true
    }

    @Override
    boolean isCredentialsNonExpired() {
        return true
    }

    @Override
    boolean isEnabled() {
        return true
    }
}
