package com.shufersalOnline.tasksAndUsersApi;

import com.shufersalOnline.tasksAndUsersApi.dto.UserDto;
import com.shufersalOnline.tasksAndUsersApi.entity.User;
import com.shufersalOnline.tasksAndUsersApi.repository.UserRepository;
import com.shufersalOnline.tasksAndUsersApi.service.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserServiceImpTest {

    @InjectMocks
    private UserServiceImp userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
       MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        // Mock data
        User user1 = new User(1L,"User 1","user1password",
                "user1mail", true, false);



        User user2 = new User(2L,"User 2","user2password",
                "user2mail", true, true);

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // Test
        List<UserDto> userDtos = userService.getAllUsers();
        assertEquals(2, userDtos.size());
    }

    @Test
    public void testGetUserById() {

        User user = new User();
        user.setId(1L);
        user.setName("Test User");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Test
        UserDto userDto = userService.getUserById(1L);
        assertNotNull(userDto);
        assertEquals("Test User", userDto.getName());
    }

    @Test
    public void testCreateUser() {

        User creatorUser = new User(1L, "admin", "admin password", "admin email", true, true);

        UserDto userDto = new UserDto(2L, "New User", "password", "newuser@example.com", true, false);

        // Ensure the admin flag is set to true for creatorUser
        creatorUser.setAdmin(true);

        when(userRepository.findById(1L)).thenReturn(Optional.of(creatorUser));
        when(userRepository.save(Mockito.any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setId(2L);
            return savedUser;
        });
        // Test
        UserDto createdUserDto = userService.createUser(1L, userDto);

        assertNotNull(createdUserDto);
        assertEquals("New User", createdUserDto.getName());


        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testUpdateUser() {
        // Mock data
        User user = new User(1L,"Old User","oldpassword","olduser@example.com",
                true,false);

        UserDto updatedUserDto = new UserDto(1l, "Updated User",
                "newpassword","updateduser@example.com",false,false);


        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);


        UserDto updatedUser = userService.updateUser(1L, updatedUserDto);
        assertNotNull(updatedUser);
        assertEquals("Updated User", updatedUser.getName());
        assertFalse(updatedUser.isActive());
    }

    @Test
    public void testDeleteUser() {
        // Mock data
        User adminUser = new User();
        adminUser.setId(1L);
        adminUser.setAdmin(true);

        User userToDelete = new User();
        userToDelete.setId(2L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(adminUser));
        when(userRepository.findById(2L)).thenReturn(Optional.of(userToDelete));

        // Test
        assertDoesNotThrow(() -> userService.deleteUser(1L, 2L));
    }
}
