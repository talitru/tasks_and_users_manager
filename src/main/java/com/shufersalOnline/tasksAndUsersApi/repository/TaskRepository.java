package com.shufersalOnline.tasksAndUsersApi.repository;

import com.shufersalOnline.tasksAndUsersApi.dto.TaskDto;
import com.shufersalOnline.tasksAndUsersApi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByAssigneeId(Long userId);
    Task findByAssigneeIdAndId(Long userId, Long taskId);
}
