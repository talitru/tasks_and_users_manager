package com.shufersalOnline.tasksAndUsersApi.entity;

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
    private Status status = Status.PENDING; //pending/completed/archived

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // This creates a foreign key column in the Task table
    private User assignee = null;//owner

    @OneToMany(mappedBy = "task")
    private List<Comment> taskCommentsList;

    public Task(String title, String description, Status status, User assignee){
        this.title=title;
        this.description=description;
        this.status=status;
        this.assignee = assignee;
    }

    public Task(Long id, String title, String description, Status status){
        this.id=id;
        this.title=title;
        this.description=description;
        this.status=status;
    }

    public Task(){}

  //getters
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public User getAssignee() {
        return assignee;
    }
    public List<Comment> getTaskCommentsList() {
        return taskCommentsList;
    }


    //setters
    public void setStatus(Status status){
        this.status=status;
    }
    public Status getStatus(){
        return status;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String name) {
        this.title = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }
    public void setTaskCommentsList(List<Comment> taskCommentsList) {
        this.taskCommentsList=taskCommentsList;
    }


}



