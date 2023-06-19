package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectUpdateDto {

  private Integer projectId;

  private String newProjectTitle;

  private String newStatusName;

}
