package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RestTemplateServiceImpl implements RestTemplateService{

  private final RestTemplate restTemplate;

  @Override
  public Optional<LoginRequest> findUserById(String userId) {
    return null;
  }
}
