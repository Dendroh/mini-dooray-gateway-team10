package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.AccountDto;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailService implements UserDetailsService {

  private final RestTemplateAccountService restTemplateService;

  public CustomUserDetailService(RestTemplateAccountService restTemplateService) {
    this.restTemplateService = restTemplateService;
  }

  @Override
  public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
    AccountDto accountDto = restTemplateService.selectUserBy(userEmail)
            .orElseThrow(() -> new UsernameNotFoundException(userEmail + " not found"));

    return new User(accountDto.getEmail(), accountDto.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
  }
}
