package com.shufersalOnline.tasksAndUsersApi.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    private LocalDate timeStamp;
    private String comment;

    public Comment(){}


    public Comment(User user, Task task, LocalDate timeStamp, String comment) {
        this.user = user;
        this.task = task;
        this.timeStamp = timeStamp;
        this.comment = comment;
    }

    //getters

    public Long getId(){
        return id;
    }

    public User getUser() {
        return user;
    }

    public Task getTask() {
        return task;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public String getComment() {
        return comment;
    }

    //setters
    public void setUser(User user) {
        this.user = user;
    }

    public void setTask(Task task) {
        this.task=task;
    }

    public void setTimeStamp(LocalDate timeStamp){
        this.timeStamp = timeStamp;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

}
