package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.*;

import java.util.List;
import java.util.Optional;

public interface RestTemplateTaskService {

  List<TaskDto> selectAllTaskBy(String accountEmail);

  Optional<TaskDto> createTaskBy(TaskRegisterDto taskRegisterDto);

  TaskDto selectTaskBy(String taskId);

  Optional<TaskDto> updateTaskBy(TaskUpdateDto taskUpdateDto);

  void deleteTaskBy(String projectId);


  Optional<TaskMileStoneDto> addTaskMileStonesBy(TaskMileStonePostDto taskMileStonePostDto);
  List<MileStoneDto> selectTaskMileStonesBy(String taskName);
  void delTaskMileStonesBy(String taskName, String mileStoneName);



  Optional<TaskTagDto> addTaskTagsBy(TaskTagPostDto taskTagPostDto);
  List<TagDto> selectTaskTagsBy(String taskName);
  void delTaskTagsBy(String taskName, String tagName);

  Optional<CommentDto> addTaskCommentsBy(CommentRegisterDto commentRegisterDto);
  List<CommentDto> selectTaskCommentsBy(String taskId);
  Optional<CommentDto> updateTaskCommentBy(CommentUpdateDto commentUpdateDto);
  void delTaskCommentsBy(String commentId);
}
