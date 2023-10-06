package com.shufersalOnline.tasksAndUsersApi.service;

import com.shufersalOnline.tasksAndUsersApi.dto.UserDto;
import com.shufersalOnline.tasksAndUsersApi.entity.User;
import com.shufersalOnline.tasksAndUsersApi.exception.AuthorizationException;
import com.shufersalOnline.tasksAndUsersApi.exception.ResourceNotFoundException;
import com.shufersalOnline.tasksAndUsersApi.mapper.UserMapper;
import com.shufersalOnline.tasksAndUsersApi.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp  implements UserService{

    private UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> usersList = userRepository.findAll();
        return usersList.stream().map((user)->UserMapper.mapToUserDto((user)))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(Long userId, UserDto userDto) {

        User creatorUser = userRepository.findById(userId).orElseThrow(()-> new
                ResourceNotFoundException("the creator user not exists with" +
                " the given id "+userId));
        if(!creatorUser.isAdmin()){
            throw new AuthorizationException("You are not admin user! Only admin" +
                    " users are allowed to create new users");
        }

        User user= UserMapper.mapToUser(userDto);
        User savedUser= userRepository.save(user);

        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new
                ResourceNotFoundException("user not exists with the given id "+userId));

        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto updatedUser) {
        User user = userRepository.findById(userId).orElseThrow(()->new
                ResourceNotFoundException("user not exists with the given id "+userId));

        user.setName(updatedUser.getName());
        user.setPassword(updatedUser.getPassword());
        user.setEmail(updatedUser.getEmail());
        user.setActive(updatedUser.isActive());
        user.setAdmin(user.isAdmin());

        User updatedUserObj = userRepository.save(user);
        return UserMapper.mapToUserDto(updatedUserObj);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new
                ResourceNotFoundException("user not exists with the given id "+userId));
        userRepository.deleteById(userId);
    }

}
