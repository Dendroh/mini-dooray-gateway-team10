package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccountDetailsDto {

  private Integer accountDetailsId;

  private String name;

  private String imageFileName;

  private Boolean isDormant;

  private LocalDateTime registerDate;

}
