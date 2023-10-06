package com.shufersalOnline.tasksAndUsersApi.service;

import com.shufersalOnline.tasksAndUsersApi.dto.TaskDto;
import com.shufersalOnline.tasksAndUsersApi.dto.UserDto;


import java.util.List;

public interface UserService {

     List<UserDto> getAllUsers();
     List<TaskDto> getAllUserTasks(Long userId);
     UserDto createUser(Long userId, UserDto userDto);
     UserDto getUserById(Long userId);
     UserDto updateUser(Long userId, UserDto updatedUser);
     void deleteUser(Long userId);


}
