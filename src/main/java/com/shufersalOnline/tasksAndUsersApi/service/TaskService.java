package com.shufersalOnline.tasksAndUsersApi.service;


import com.shufersalOnline.tasksAndUsersApi.dto.TaskDto;
import com.shufersalOnline.tasksAndUsersApi.entity.Task;

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
    void reassignOrAssignTask(Long userId, Long taskId);
    void markCompletedTasksAsArchived(Long userId, Long taskId);

}
