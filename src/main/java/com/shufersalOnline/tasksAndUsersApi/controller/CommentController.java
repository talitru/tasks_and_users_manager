package com.shufersalOnline.tasksAndUsersApi.controller;

import com.shufersalOnline.tasksAndUsersApi.entity.Comment;
import com.shufersalOnline.tasksAndUsersApi.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService=commentService;
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllCommentsO(){

        List<Comment> comments= commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/users/{userId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsOfUser(@PathVariable Long userId){

        List<Comment> comments= commentService.getAllCommentsOfUser(userId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/users/{userId}/tasks/{taskId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsOfUserTask(@PathVariable Long userId,
                                                                   @PathVariable Long taskId){

        List<Comment> comments= commentService.getAllCommentsOfUserTask(userId,taskId);
        return ResponseEntity.ok(comments);
    }
}
