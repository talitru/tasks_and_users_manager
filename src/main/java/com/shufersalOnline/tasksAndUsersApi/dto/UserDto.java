package com.shufersalOnline.tasksAndUsersApi.dto;

import jakarta.persistence.Column;


public class UserDto {
    private Long id;
    private String name;
    private String password; //hashed
    private String email;
    private boolean isActive;
    private boolean isAdmin;

    public UserDto(Long id, String name, String password, String email, boolean isActive, boolean isAdmin) {
        this.id=id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.isActive = isActive;
        this.isAdmin= isAdmin;
    }

    //getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    //setters

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
