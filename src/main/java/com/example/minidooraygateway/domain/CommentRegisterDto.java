package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRegisterDto {
  private String content;

  private Integer taskId;

  private String writerEmail;
}
