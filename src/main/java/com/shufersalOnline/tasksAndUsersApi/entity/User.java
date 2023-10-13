package com.shufersalOnline.tasksAndUsersApi.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity(name="user_details")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    private String name;
    private String password;
    private String email;

    @Column(name="is_active")
    private boolean isActive;

    @Column(name="is_admin")
    private boolean isAdmin;

    @OneToMany(mappedBy = "assignee")
    private List<Task> tasksOwned;

    @OneToMany(mappedBy = "user")
    private List<Comment> commentsOwned;

    public User(){}

    public User(Long id,String name, String password,String email, boolean isActive, boolean isAdmin){
        this.id=id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.isActive = isActive;
        this.isAdmin= isAdmin;
    }


    //getters
    public Long getId() { return id; }
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
    public List<Task> getTasksOwned() {
        return tasksOwned;
    }
    public List<Comment> getCommentsOwned() {
        return commentsOwned;
    }


    //setters

    public void setId(Long id){
        this.id=id;
    }
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
    public void setTasksOwned(List<Task> tasksOwned) {
        this.tasksOwned = tasksOwned;
    }
    public void setCommentsOwned(List<Comment> commentsOwned) {
        this.commentsOwned = commentsOwned;
    }



}
