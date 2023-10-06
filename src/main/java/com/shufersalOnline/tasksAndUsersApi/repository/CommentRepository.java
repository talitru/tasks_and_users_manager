package com.shufersalOnline.tasksAndUsersApi.repository;

import com.shufersalOnline.tasksAndUsersApi.entity.Comment;
import com.shufersalOnline.tasksAndUsersApi.entity.Task;
import com.shufersalOnline.tasksAndUsersApi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long>{
    List<Comment> findByUserAndTask(User user, Task task);
    List<Comment> findByTaskAssignee(User user);

}