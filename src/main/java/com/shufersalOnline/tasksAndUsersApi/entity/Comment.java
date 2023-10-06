package com.shufersalOnline.tasksAndUsersApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

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

    public Comment(User user, Task task, LocalDate timeStamp, String comment){
        this.user=user;
        this.task=task;
        this.timeStamp=timeStamp;
        this.comment=comment;
    }

}
