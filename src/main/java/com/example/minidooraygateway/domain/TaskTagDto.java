package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskTagDto {

  private int taskId;

  private int tagId;

  private String taskName;

  private String tagName;
}
