package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {

  private Integer accountId;

  private String email;

  private String password;

  private String name;

}
