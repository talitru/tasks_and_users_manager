package com.shufersalOnline.tasksAndUsersApi.dto;

import java.time.LocalDate;

public class CommentDto {

    private Long id;
    private Long userId;
    private Long taskId;
    private LocalDate timeStamp;
    private String comment;

    public CommentDto(Long id, Long userId, Long taskId, LocalDate timeStamp, String comment){
        this.id=id;
        this.userId=userId;
        this.taskId=taskId;
        this.timeStamp=timeStamp;
        this.comment=comment;
    }

    public Long getId(){
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public String getComment() {
        return comment;
    }

    //setters
    public void setUser(Long userId) {
        this.userId = userId;
    }

    public void setTask(Long taskId) {
        this.taskId = taskId;
    }

    public void setTimeStamp(LocalDate timeStamp){
        this.timeStamp = timeStamp;
    }

    public void setComment(String comment){
        this.comment = comment;
    }
}
