package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.*;

import java.util.List;

public interface RestTemplateTaskService {

  List<TaskDto> selectAllTaskBy(String projectId);

  void createTaskBy(TaskDto taskDto);

  void updateTaskBy(TaskDto taskDto);

  void deleteTaskBy(String taskId);


  List<CommentDto> selectAllCommentBy(String taskId);

  void createCommentBy(CommentDto commentDto);

  void updateCommentBy(CommentDto commentDto);

  void deleteCommentBy(String commentId);


  List<TagDto> selectAllTagBy(String taskId);

  void attachTag();

  void detachTag();


  List<MileStoneDto> selectAllMileStoneBy(String taskId);

  void attachMileStone();

  void detachMileStone();

}
