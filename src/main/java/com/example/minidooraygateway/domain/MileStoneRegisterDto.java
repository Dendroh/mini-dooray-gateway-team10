package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@Component
public class MileStoneRegisterDto {

  private String milestoneName;

  private LocalDateTime startDatetime;

  private LocalDateTime endDatetime;

  private Integer projectId;
}
