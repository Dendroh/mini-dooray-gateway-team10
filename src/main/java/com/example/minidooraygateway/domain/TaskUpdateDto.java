package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskUpdateDto {

  private Integer taskId;

  private String newTaskTitle;

  private String newTaskContent;

}
