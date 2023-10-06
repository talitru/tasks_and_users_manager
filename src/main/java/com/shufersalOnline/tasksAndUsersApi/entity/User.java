package com.shufersalOnline.tasksAndUsersApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity(name="user_details")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    private String name;
    private String password; //hashed
    private String email;
    @Column(name="is_active")
    private boolean isActive;
    @Column(name="is_admin")
    private boolean isAdmin;
    @OneToMany(mappedBy = "assignee")
    private List<Task> tasksOwned;

    @OneToMany(mappedBy = "user")
    private List<Comment> commentsOwned;



    protected User(){}

    public User(String name, String password,String email, boolean isActive, boolean isAdmin){
        this.name = name;
        this.password = password;
        this.email = email;
        this.isActive = isActive;
        this.isAdmin= isAdmin;
    }


    //getters and setters
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasksOwned() {
        return tasksOwned;
    }
    public List<Comment> getCommentsOwned() {
        return commentsOwned;
    }

    public void setTasksOwned(List<Task> tasksOwned) {
        this.tasksOwned = tasksOwned;
    }
    public void setCommentsOwned(List<Comment> commentsOwned) {
        this.commentsOwned = commentsOwned;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
