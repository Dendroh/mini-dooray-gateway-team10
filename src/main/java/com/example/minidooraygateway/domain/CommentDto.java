package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@Component
public class CommentDto {

  private Integer commentId;

  private String content;

  private LocalDateTime writeTime;

  private Integer taskId;

  private String writerName;

  private String writerEmail;
}
