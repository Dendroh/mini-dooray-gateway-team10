package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class TagDto {
  private Integer tagId;

  private String tagName;

  private String tagColor;

  private Integer projectId;
}
