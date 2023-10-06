package com.shufersalOnline.tasksAndUsersApi.controller;

import com.shufersalOnline.tasksAndUsersApi.dto.UserDto;
import com.shufersalOnline.tasksAndUsersApi.entity.Comment;
import com.shufersalOnline.tasksAndUsersApi.entity.Task;
import com.shufersalOnline.tasksAndUsersApi.entity.User;
import com.shufersalOnline.tasksAndUsersApi.repository.CommentRepository;
import com.shufersalOnline.tasksAndUsersApi.repository.TaskRepository;
import com.shufersalOnline.tasksAndUsersApi.repository.UserRepository;
import com.shufersalOnline.tasksAndUsersApi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private TaskRepository taskRepository;
    private CommentRepository commentRepository;

    private UserService userService;

    public UserController(UserService userService,UserRepository userRepository,TaskRepository taskRepository,CommentRepository commentRepository){
        this.userRepository=userRepository;
        this.taskRepository=taskRepository;
        this.commentRepository=commentRepository;
        this.userService=userService;
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


    //get all users
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> usersList = userService.getAllUsers();
        return ResponseEntity.ok(usersList);
    }

    //get specific user
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId){

        UserDto userDto = userService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }

    //create new user
    @PostMapping("/users/{userId}")
    public ResponseEntity<UserDto> createUser(@PathVariable Long userId,
                                           @RequestBody UserDto userDto){
        UserDto savedUser = userService.createUser(userId,userDto);
        return ResponseEntity.ok(savedUser);
    }

    //update user details
    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId,
                                              @RequestBody UserDto updatedUser) {
        UserDto updatedUserDto=userService.updateUser(userId,updatedUser);
        return ResponseEntity.ok(updatedUserDto);
    }

    //delete specific user
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("user deleted successfully.");
    }
}
