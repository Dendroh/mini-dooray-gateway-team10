package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class AccountDetailsUpdateDto {

  private String accountEmail;

  private String name;

  private Boolean isDormant;

  private String profileImage;

}