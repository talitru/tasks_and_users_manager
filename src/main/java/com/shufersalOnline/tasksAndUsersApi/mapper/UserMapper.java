package com.shufersalOnline.tasksAndUsersApi.mapper;

import com.shufersalOnline.tasksAndUsersApi.dto.UserDto;
import com.shufersalOnline.tasksAndUsersApi.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user){
            return new UserDto(
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.isActive(),
                user.isAdmin()
            );
    }

    public static User mapToUser(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.isActive(),
                userDto.isAdmin()
        );

    }
}
