package com.shufersalOnline.tasksAndUsersApi.controller;

import com.shufersalOnline.tasksAndUsersApi.dto.UserDto;
import com.shufersalOnline.tasksAndUsersApi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
//@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
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
                                              @RequestBody UserDto updatedUser){

        UserDto updatedUserDto=userService.updateUser(userId,updatedUser);
        return ResponseEntity.ok(updatedUserDto);
    }

    //delete specific user
    @DeleteMapping("/users/{userId}/delete/{userToDeleteId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId,
                                             @PathVariable Long userToDeleteId){

        userService.deleteUser(userId,userToDeleteId);
        return ResponseEntity.ok("user deleted successfully.");
    }
}
