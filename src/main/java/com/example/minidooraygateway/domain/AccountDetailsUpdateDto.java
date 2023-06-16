package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDetailsUpdateDto {

  private String accountEmail;

  private String name;

  private Boolean isDormant;

  private String profileImage;

}
