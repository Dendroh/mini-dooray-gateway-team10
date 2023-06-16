package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCommitDto {
  private String content;

  private Integer taskId;
}
