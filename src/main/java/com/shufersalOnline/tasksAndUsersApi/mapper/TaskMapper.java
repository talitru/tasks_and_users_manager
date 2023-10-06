package com.shufersalOnline.tasksAndUsersApi.mapper;

import com.shufersalOnline.tasksAndUsersApi.dto.TaskDto;
import com.shufersalOnline.tasksAndUsersApi.entity.Task;

public class TaskMapper {

    public static TaskDto mapToTaskDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus()
        );
    }

    public static Task mapToTask(TaskDto taskDto) {
        return new Task(
                taskDto.getId(),
                taskDto.getTitle(),
                taskDto.getDescription(),
                taskDto.getStatus()
        );
    }
}
