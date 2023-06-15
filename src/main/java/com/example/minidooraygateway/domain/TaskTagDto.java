package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class TaskTagDto {

  private int taskId;

  private int tagId;

  private String taskName;

  private String tagName;
}
