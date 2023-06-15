package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class AccountUpdateDto {

  private String beforeEmail;

  private String afterEmail;

  private String password;

}
