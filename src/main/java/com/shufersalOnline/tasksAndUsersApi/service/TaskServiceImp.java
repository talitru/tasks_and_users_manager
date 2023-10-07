package com.shufersalOnline.tasksAndUsersApi.service;

import com.shufersalOnline.tasksAndUsersApi.dto.TaskDto;
import com.shufersalOnline.tasksAndUsersApi.dto.UserDto;
import com.shufersalOnline.tasksAndUsersApi.entity.Status;
import com.shufersalOnline.tasksAndUsersApi.entity.Task;
import com.shufersalOnline.tasksAndUsersApi.entity.User;
import com.shufersalOnline.tasksAndUsersApi.exception.AuthorizationException;
import com.shufersalOnline.tasksAndUsersApi.exception.ResourceNotFoundException;
import com.shufersalOnline.tasksAndUsersApi.mapper.TaskMapper;
import com.shufersalOnline.tasksAndUsersApi.mapper.UserMapper;
import com.shufersalOnline.tasksAndUsersApi.repository.TaskRepository;
import com.shufersalOnline.tasksAndUsersApi.repository.UserRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImp implements TaskService{

    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private UserService userService;

    public TaskServiceImp(TaskRepository taskRepository, UserService userService,UserRepository userRepository){
        this.taskRepository=taskRepository;
        this.userService = userService;
        this.userRepository= userRepository;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasksList = taskRepository.findAll();
        return tasksList.stream().map((task)-> TaskMapper.mapToTaskDto((task)))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getAllUserTasks(Long userId) {

        UserDto userDto = userService.getUserById(userId);//throw an exception if not exists
        List<TaskDto> tasksList = taskRepository.findByAssigneeId(userId).stream()
                .map((task)->TaskMapper.mapToTaskDto(task)).collect(Collectors.toList());

        return tasksList;
    }

    @Override
    public TaskDto getSpecificUserTask(Long userId, Long taskId) {

        Task task = taskRepository.findByAssigneeIdAndId(userId,taskId);
        if(task==null){
            throw new ResourceNotFoundException("user or task not exists with the given id");
        }
        return TaskMapper.mapToTaskDto(task);

    }

    @Override
    public TaskDto getTaskById(Long taskId) {

        Task task= taskRepository.findById(taskId).orElseThrow(() ->
                new ResourceNotFoundException("task not exists with the given id "+taskId));

        return TaskMapper.mapToTaskDto(task);
    }

    @Override
    public TaskDto createTask(Long userId, TaskDto taskDto) {

        User creatorUser = userRepository.findById(userId).orElseThrow(()-> new
                ResourceNotFoundException("the creator user not exists with" +
                " the given id "+userId));
        if(!creatorUser.isAdmin()){
            throw new AuthorizationException("You are not admin user! Only admin" +
                    " users are allowed to create new users");
        }

        Task task= TaskMapper.mapToTask(taskDto);
        Task savedTask= taskRepository.save(task);

        return TaskMapper.mapToTaskDto(savedTask);
    }

    @Override
    public TaskDto updateTask(Long userId, Long taskId, TaskDto updatedTask) {

        Task task = taskRepository.findById(userId).orElseThrow(()-> new
                ResourceNotFoundException("user not exists with the given id "+userId));

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());

        Task updatedTaskObj = taskRepository.save(task);
        return TaskMapper.mapToTaskDto(updatedTaskObj);
    }

    @Override
    public void markTaskAsCompleted(Long userId, Long taskId) {
        User user = userRepository.findById(userId).orElseThrow(()->new
                ResourceNotFoundException("user not exists with the given id "+userId));
        Task task = taskRepository.findById(taskId).orElseThrow(()->new
                ResourceNotFoundException("task not exists with the given id "+taskId));
        if(task.getAssignee().getId().equals( userId)){
            task.setStatus(Status.COMPLETED);
        }

    }

    @Override
    public void deleteTask(Long userId, Long taskId) {

        User user = userRepository.findById(userId).orElseThrow(()->new
                ResourceNotFoundException("user not exists with the given id "+userId));
        if(user.isAdmin()){
            throw new AuthorizationException("You are not admin user! Only admin" +
                    " users are allowed to delete tasks");
        }

        taskRepository.findById(taskId).orElseThrow(()->new
                ResourceNotFoundException("task not exists with the given id "+taskId));

        taskRepository.deleteById(taskId);
    }


    @Override
    public void assignTask(Long userId, Long taskId, Long assigneeId) {
        User user = userRepository.findById(userId).orElseThrow(()->new
                ResourceNotFoundException("user not exists with the given id "+userId));
        if(user.isAdmin()){
            throw new AuthorizationException("You are not admin user! Only admin" +
                    " users are allowed to delete tasks");
        }
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new
                ResourceNotFoundException("user not exists with the given id " + userId));

        User assignee = userRepository.findById(assigneeId).orElseThrow(()->new
                ResourceNotFoundException("assignee not exists with the given id "+ assigneeId));
        task.setAssignee(assignee);

    }

    @Override
    public void markCompletedTasksAsArchived(Long userId, Long taskId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new
                ResourceNotFoundException("user not exists with the given id " + userId));
        if (user.isAdmin()) {
            throw new AuthorizationException("You are not admin user! Only admin" +
                    " users are allowed to delete tasks");
        }
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new
                ResourceNotFoundException("user not exists with the given id " + userId));
        task.setStatus(Status.ARCHIVED);
    }
}
