package com.example.minidooraygateway.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class MemberRegisterDto {

  private String memberName;

  private String memberEmail;

}
