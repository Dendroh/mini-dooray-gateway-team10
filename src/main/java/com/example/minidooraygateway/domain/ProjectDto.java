package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ProjectDto {
  private Integer projectId;

  private String projectTitle;

  private String projectStatus;

  private Integer adminId;
}
