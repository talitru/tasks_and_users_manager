package com.shufersalOnline.tasksAndUsersApi.service;

import com.shufersalOnline.tasksAndUsersApi.dto.TaskDto;
import java.util.List;

public interface TaskService {

    List<TaskDto> getAllTasks();
    List<TaskDto> getAllUserTasks(Long userId);
    TaskDto getSpecificUserTask(Long userId, Long taskId);
    TaskDto getTaskById(Long taskId);
    TaskDto createTask(Long userId, TaskDto newTaskData);
    TaskDto updateTask(Long userId, Long taskId, TaskDto updatedTask);
    void markTaskAsCompleted(Long userId, Long taskId);
    void deleteTask(Long userId, Long taskId);
    void assignTask(Long userId, Long taskId,Long assigneeId);
    void markCompletedTasksAsArchived(Long userId, Long taskId);

}
