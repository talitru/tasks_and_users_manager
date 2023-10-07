package com.shufersalOnline.tasksAndUsersApi.controller;

import com.shufersalOnline.tasksAndUsersApi.dto.TaskDto;
import com.shufersalOnline.tasksAndUsersApi.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService=taskService;
    }

    //get all tasks
    @GetMapping("/users/{userId}/admin/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks(){

        List<TaskDto> allTasks = taskService.getAllTasks();
        return ResponseEntity.ok(allTasks);
    }

    //get all specific user tasks
    @GetMapping("/users/{userId}/tasks")
    public ResponseEntity<List<TaskDto>> getAllSpecificUserTasks(@PathVariable Long userId){

        List<TaskDto> userTasks = taskService.getAllUserTasks(userId);
        return ResponseEntity.ok(userTasks);
    }

    //get specific task
    @GetMapping("/users/{userId}/admin/tasks/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long taskId){
        TaskDto taskDto = taskService.getTaskById(taskId);

        return ResponseEntity.ok(taskDto);
    }

    //create task
    @PostMapping("/users/{userId}/admin/tasks")
    public ResponseEntity<TaskDto> createTask(@PathVariable Long taskId,
                                              @RequestBody TaskDto taskDto){

        TaskDto newTaskDto = taskService.createTask(taskId,taskDto);
        return ResponseEntity.ok(newTaskDto);
    }

    @PutMapping("/users/{userId}/admin/tasks/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long userId,
                                              @PathVariable Long taskId,
                                              @RequestBody TaskDto updatedTaskData){

        TaskDto updatedTask = taskService.updateTask(userId,taskId,updatedTaskData);
        return ResponseEntity.ok(updatedTask);
    }
    @PutMapping("/users/{userId}/tasks/{taskId}/complete")
    public ResponseEntity<String> markAsCompletedTask(@PathVariable Long userId,
                                                      @PathVariable Long taskId){

        taskService.markTaskAsCompleted(userId,taskId);
        return ResponseEntity.ok("task marked as completed successfully");
    }

    @DeleteMapping("/users/{userId}/admin/tasks/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long userId,
                                             @PathVariable Long taskId){

        taskService.deleteTask(userId,taskId);
        return ResponseEntity.ok("task deleted successfully");
    }

    @DeleteMapping("/users/{userId}/admin/tasks/{taskId}/assign")
    public ResponseEntity<String> assignTask(@PathVariable Long userId,
                                             @PathVariable Long taskId,
                                             @PathVariable Long assigneeId){

        taskService.assignTask(userId,taskId,assigneeId);
        return ResponseEntity.ok("task assigned successfully");
    }
    @DeleteMapping("/users/{userId}/admin/tasks/{taskId}/archive")
    public ResponseEntity<String> markCompletedTasksAsArchived(@PathVariable Long userId,
                                                               @PathVariable Long taskId){

        taskService.markCompletedTasksAsArchived(userId,taskId);
        return ResponseEntity.ok("completed task marked as archive successfully");
    }


}
