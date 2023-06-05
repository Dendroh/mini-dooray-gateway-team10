package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.LoginRequest;

import java.util.Optional;

public interface RestTemplateService {
  Optional<LoginRequest> findUserById(String userId);
}
