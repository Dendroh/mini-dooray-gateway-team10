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

}
