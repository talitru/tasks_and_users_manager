package com.shufersalOnline.tasksAndUsersApi.dto;

import com.shufersalOnline.tasksAndUsersApi.entity.Status;
import com.shufersalOnline.tasksAndUsersApi.entity.User;

public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private Status status; //pending/completed/archived
    private User assignee = null;  // Assuming you want to include the user's ID in the DTO

    public TaskDto(Long id, String title, String description, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public TaskDto(Long id, String title, String description, Status status, User assignee) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.assignee = assignee;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }


    //setters
    public User getAssignee() {
        return assignee;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

}
