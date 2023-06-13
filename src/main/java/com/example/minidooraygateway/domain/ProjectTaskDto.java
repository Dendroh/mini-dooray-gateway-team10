package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@Component
public class ProjectTaskDto {
  private Integer taskId;

  private String taskName;

  private String content;

  private LocalDateTime writeTime;

  private String writerName;

  private String writerEmail;

  private Integer projectId;

}
