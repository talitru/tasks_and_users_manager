package com.shufersalOnline.tasksAndUsersApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "task_id")
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status; //pending/completed/archived

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID") // This creates a foreign key column in the Task table
    private User assignee;//owner

    @OneToMany(mappedBy = "task")
    private List<Comment> taskCommentsList;

    public Task(String title, String description, Status status, User assignee){
        this.title=title;
        this.description=description;
        this.status=status;
        this.assignee = assignee;
    }

    public List<Comment> getTaskCommentsList() {
        return taskCommentsList;
    }
    public void setTaskCommentsList(List<Comment> taskCommentsList) {
        this.taskCommentsList=taskCommentsList;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public void setStatus(Status status){
        this.status=status;
    }
    public Status getStatus(){
        return status;
    }

}

enum Status {
    PENDING,
    COMPLETED,
    ARCHIVED
}

