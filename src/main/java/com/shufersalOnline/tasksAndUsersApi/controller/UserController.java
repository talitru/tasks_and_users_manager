package com.shufersalOnline.tasksAndUsersApi.controller;

import com.shufersalOnline.tasksAndUsersApi.entity.Comment;
import com.shufersalOnline.tasksAndUsersApi.entity.Task;
import com.shufersalOnline.tasksAndUsersApi.entity.User;
import com.shufersalOnline.tasksAndUsersApi.repository.CommentRepository;
import com.shufersalOnline.tasksAndUsersApi.repository.TaskRepository;
import com.shufersalOnline.tasksAndUsersApi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserRepository userRepository;
    private TaskRepository taskRepository;
    private CommentRepository commentRepository;

    public UserController(UserRepository userRepository,TaskRepository taskRepository,CommentRepository commentRepository){
        this.userRepository=userRepository;
        this.taskRepository=taskRepository;
        this.commentRepository=commentRepository;
    }

    @GetMapping("/users")
    public List<User> findAll(){
        return userRepository.findAll();
    }


    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping("/users/{id}/tasks")
    public List<Task> getTasksForUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isEmpty()) {
            return user.get().getTasksOwned();
        }
        return null;
    }
    @GetMapping("/users/{userId}/tasks/{taskId}/comments")
    public ResponseEntity<List<Comment>> getCommentsForUser(@PathVariable Long userId, @PathVariable Long taskId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if (userOptional.isEmpty() || taskOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // If both user and task exist, you can retrieve the comments associated with the specified user and task.
        User user = userOptional.get();
        Task task = taskOptional.get();

        if (!task.getAssignee().equals(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Return a 403 Forbidden response
        }

        // Retrieve the comments associated with the user and task
        List<Comment> comments = commentRepository.findByUserAndTask(user, task);

        // Return the list of comments in the response
        return ResponseEntity.ok(comments);
    }


    //post mappings:
    @PostMapping("/users/{userId}")
    public HttpStatus createUser(@PathVariable Long userId){
        User user;
        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isEmpty()&& (user= userOptional.get()).isAdmin()){

        }
        return HttpStatus.BAD_REQUEST;

    }
}
