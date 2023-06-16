package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class TaskRegisterDto {

  private String taskTitle;

  private String taskContent;

  private Integer projectId;

  private String writerEmail;

}
