package com.shufersalOnline.tasksAndUsersApi.service;

import com.shufersalOnline.tasksAndUsersApi.entity.Comment;
import com.shufersalOnline.tasksAndUsersApi.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImp implements CommentService{

    private CommentRepository commentRepository;

    public CommentServiceImp(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getAllCommentsOfUserTask(Long userId, Long taskId) {
        return commentRepository.findByUserIdAndTaskId(userId, taskId);
    }

    @Override
    public List<Comment> getAllCommentsOfUser(Long userId) {
        return commentRepository.findByUserId(userId);
    }
}
