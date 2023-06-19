package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateDto {
  private Integer commentId;
  private String content;
}
