package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountUpdateDto {

  private String beforeEmail;

  private String afterEmail;

  private String password;

}
