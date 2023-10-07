//package com.shufersalOnline.tasksAndUsersApi.mapper;
//
//import com.shufersalOnline.tasksAndUsersApi.dto.CommentDto;
//import com.shufersalOnline.tasksAndUsersApi.dto.TaskDto;
//import com.shufersalOnline.tasksAndUsersApi.entity.Comment;
//import com.shufersalOnline.tasksAndUsersApi.entity.Task;
//
//public class CommentMapper {
//    public static CommentDto mapToCommentDto(Comment comment) {
//        return new CommentDto(
//                comment.getId(),
//                comment.getUser().getId(),//userId
//                comment.getTask().getId(), //taskId
//                comment.getTimeStamp(),
//                comment.getComment()
//        );
//    }
//
//
//    public static Comment mapToComment(CommentDto commentDto) {
//
//        return new Comment(
//                commentDto.getId(),
//                commentDto.getUserId(),
//                commentDto.getTaskId(),
//                commentDto.getTimeStamp(),
//                commentDto.getComment()
//        );
//    }
//}
