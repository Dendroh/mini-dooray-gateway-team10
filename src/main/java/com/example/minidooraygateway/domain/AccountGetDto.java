package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountGetDto {

  private Integer accountId;

  private String email;

  private String password;

}
