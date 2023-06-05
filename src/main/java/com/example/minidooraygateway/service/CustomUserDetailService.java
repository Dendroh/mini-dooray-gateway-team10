package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.LoginRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailService implements UserDetailsService {

  private final RestTemplateService restTemplateService;

  public CustomUserDetailService(RestTemplateService restTemplateService) {
    this.restTemplateService = restTemplateService;
  }

  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    LoginRequest loginRequest = restTemplateService.findUserById(userId)
            .orElseThrow(() -> new UsernameNotFoundException(userId + " not found"));

    return new User(loginRequest.getEmail(), loginRequest.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority(loginRequest.getEmail())));
  }
}
