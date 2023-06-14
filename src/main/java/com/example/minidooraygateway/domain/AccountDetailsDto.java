package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@Component
public class AccountDetailsDto {

  private Integer accountDetailsId;

  private String name;

  private String imageFileName;

  private Boolean isDormant;

  private LocalDateTime registerDate;

}
