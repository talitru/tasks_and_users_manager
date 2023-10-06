package com.shufersalOnline.tasksAndUsersApi.service;

import com.shufersalOnline.tasksAndUsersApi.dto.TaskDto;
import com.shufersalOnline.tasksAndUsersApi.repository.TaskRepository;

import java.util.List;

public class TaskServiceImp implements TaskService{

    private TaskRepository taskRepository;

    public TaskServiceImp(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }


    @Override
    public List<TaskDto> getAllTasks() {
        return null;
    }

    @Override
    public List<TaskDto> getAllUserTasks(Long userId) {
        return null;
    }

    @Override
    public TaskDto getSpecificUserTask(Long userId, Long taskId) {
        return null;
    }

    @Override
    public TaskDto getTaskById(Long taskId) {
        return null;
    }

    @Override
    public TaskDto createTask(Long userId, TaskDto newTaskData) {
        return null;
    }

    @Override
    public TaskDto updateTask(Long userId, Long taskId, TaskDto updatedTask) {
        return null;
    }

    @Override
    public void markTaskAsCompleted(Long userId, Long taskId) {

    }

    @Override
    public void deleteTask(Long userId, Long taskId) {

    }

    @Override
    public void reassignOrAssignTask(Long userId, Long taskId) {

    }

    @Override
    public void markCompletedTasksAsArchived(Long userId, Long taskId) {

    }
}
