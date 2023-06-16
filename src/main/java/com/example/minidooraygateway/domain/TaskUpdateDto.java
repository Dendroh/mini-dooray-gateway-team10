package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class TaskUpdateDto {

  private Integer taskId;

  private String newTaskTitle;

  private String newTaskContent;

}
