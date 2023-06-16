package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskDto {
  private Integer taskId;

  private String taskTitle;

  private String taskContent;

  private LocalDateTime writeTime;

  private String writerName;

  private String writerEmail;

  private Integer projectId;

}
