package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.*;

import java.util.List;
import java.util.Optional;

public interface RestTemplateTaskService {

  List<ProjectTaskDto> selectAllTaskBy(String accountEmail);

  Optional<TaskDto> createTaskBy(TaskRegisterDto taskRegisterDto);

  ProjectTaskDto selectTaskBy(String taskId);

}
