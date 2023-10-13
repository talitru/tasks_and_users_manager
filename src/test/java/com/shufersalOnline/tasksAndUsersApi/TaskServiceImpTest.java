package com.shufersalOnline.tasksAndUsersApi;

import com.shufersalOnline.tasksAndUsersApi.dto.TaskDto;
import com.shufersalOnline.tasksAndUsersApi.dto.UserDto;
import com.shufersalOnline.tasksAndUsersApi.entity.Status;
import com.shufersalOnline.tasksAndUsersApi.entity.Task;
import com.shufersalOnline.tasksAndUsersApi.entity.User;
import com.shufersalOnline.tasksAndUsersApi.repository.TaskRepository;
import com.shufersalOnline.tasksAndUsersApi.repository.UserRepository;
import com.shufersalOnline.tasksAndUsersApi.service.TaskServiceImp;
import com.shufersalOnline.tasksAndUsersApi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskServiceImpTest {

    @InjectMocks
    private TaskServiceImp taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTasks() {

        List<Task> tasks = Collections.singletonList(new Task());

        when(taskRepository.findAll()).thenReturn(tasks);

        List<TaskDto> taskDtos = taskService.getAllTasks();

        assertEquals(1, taskDtos.size());

    }

    @Test
    public void testGetAllUserTasks() {

        UserDto userDto = new UserDto();
        when(userService.getUserById(ArgumentMatchers.anyLong())).thenReturn(userDto);


        List<Task> tasks = Collections.singletonList(new Task());
        when(taskRepository.findByAssigneeId(ArgumentMatchers.anyLong())).thenReturn(tasks);

        List<TaskDto> taskDtos = taskService.getAllUserTasks(1L);

        assertEquals(1, taskDtos.size());

    }

    @Test
    public void testGetSpecificUserTask_ExistingTask() {
        // Mock the taskRepository to return a task when called with userId and taskId
        Task task = new Task();
        when(taskRepository.findByAssigneeIdAndId(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(task); // Return the Task object directly, not wrapped in an Optional

        TaskDto taskDto = taskService.getSpecificUserTask(1L, 2L);

        assertNotNull(taskDto);
    }

    @Test
    public void testGetTaskById_ExistingTask() {

        Task task = new Task();
        when(taskRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(task));

        TaskDto taskDto = taskService.getTaskById(1L);

        assertNotNull(taskDto);

    }

    @Test
    public void testCreateTask_AdminUser() {

        User adminUser = new User();
        adminUser.setAdmin(true);
        when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(adminUser));


        Task task = new Task();
        when(taskRepository.save(ArgumentMatchers.any(Task.class))).thenReturn(task);

        TaskDto taskDto = new TaskDto();

        TaskDto createdTask = taskService.createTask(1L, taskDto);

        assertNotNull(createdTask);
    }

    @Test
    public void testUpdateTask() {

        Task task = new Task();
        when(taskRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(task));

        when(taskRepository.save(ArgumentMatchers.any(Task.class))).thenReturn(task);

        TaskDto updatedTask = new TaskDto();

        TaskDto updatedTaskDto = taskService.updateTask(1L, 2L, updatedTask);

        assertNotNull(updatedTaskDto);
    }

    @Test
    public void testMarkTaskAsCompleted() {

        User user= new User(1L, "User", "password", "user@example.com", true, false);

        when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(user));

        Task task = new Task();
        task.setAssignee(user);
        when(taskRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(task));

        taskService.markTaskAsCompleted(1L, 2L);

        assertEquals(Status.COMPLETED, task.getStatus());
    }

    @Test
    public void testDeleteTask_AdminUser() {

        User adminUser = new User(1L, "admin", "admin password",
                "admin email", true, true);

        when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(adminUser));


        Task task = new Task();
        task.setId(2L);
        when(taskRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(task));

        taskService.deleteTask(1L, 2L);

        Mockito.verify(taskRepository).deleteById(2L);
    }

    @Test
    public void testAssignTask_AdminUser() {

        User adminUser = new User();
        adminUser.setAdmin(true);
        when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(adminUser));


        Task task = new Task();
        when(taskRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(task));


        User assigneeUser = new User();
        when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(assigneeUser));

        taskService.assignTask(1L, 2L, 3L);

        assertEquals(assigneeUser, task.getAssignee());
    }

    @Test
    public void testMarkCompletedTasksAsArchived_AdminUser() {

        User adminUser = new User();
        adminUser.setId(1L);
        adminUser.setAdmin(true);

        when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(adminUser));

        Task task = new Task();
        when(taskRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(task));

        taskService.markCompletedTasksAsArchived(1L, 2L);

        assertEquals(Status.ARCHIVED, task.getStatus());
    }

}
