package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@Component
public class TagRegisterDto {

  private String tagName;

  private String tagColor;

  private Integer projectId;
}
