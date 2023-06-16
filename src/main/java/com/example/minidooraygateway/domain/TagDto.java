package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagDto {
  private Integer tagId;

  private String tagName;

  private String tagColor;

  private Integer projectId;
}
