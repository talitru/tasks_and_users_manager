package com.shufersalOnline.tasksAndUsersApi.service;

import com.shufersalOnline.tasksAndUsersApi.entity.Comment;
import java.util.List;

public interface CommentService {

    List<Comment> getAllComments();

    List<Comment> getAllCommentsOfUserTask(Long userId, Long taskId);

    List<Comment> getAllCommentsOfUser(Long userId);

}
