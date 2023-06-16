package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {

  private Integer commentId;

  private String content;

  private LocalDateTime writeTime;

  private Integer taskId;

  private String writerName;

  private String writerEmail;
}
