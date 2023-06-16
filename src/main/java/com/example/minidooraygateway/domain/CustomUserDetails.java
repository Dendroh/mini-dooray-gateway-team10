package com.example.minidooraygateway.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@Slf4j
@JsonDeserialize(as = CustomUserDetails.class)
public class CustomUserDetails extends User implements UserDetails {

  @JsonCreator
  public CustomUserDetails(@JsonProperty("username") String username,
                           @JsonProperty("password") String password,
                           @JsonProperty("authorities") Collection<? extends GrantedAuthority> authorities) {

    super(username, password, authorities);
  }
}

