package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRegisterDto {

  private String taskTitle;

  private String taskContent;

  private Integer projectId;

  private String writerEmail;

}
