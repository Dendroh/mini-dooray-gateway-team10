package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MileStoneRegisterDto {

  private String milestoneName;

  private LocalDateTime startDatetime;

  private LocalDateTime endDatetime;

  private Integer projectId;
}
