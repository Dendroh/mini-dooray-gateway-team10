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
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    AccountDto accountDto = restTemplateService.selectUserBy(userId)
            .orElseThrow(() -> new UsernameNotFoundException(userId + " not found"));

    return new User(accountDto.getEmail(), accountDto.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
  }
}
