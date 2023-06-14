package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.AccountDetailsDto;
import com.example.minidooraygateway.domain.AccountDetailsPostDto;
import com.example.minidooraygateway.domain.AccountDto;

import java.util.Optional;

public interface RestTemplateAccountService {

  Optional<AccountDto> selectUserBy(String accountEmail);

  Optional<AccountDto> createUserBy(AccountDto accountDto);

  Optional<AccountDetailsDto> selectUserDetailBy(String accountEmail);

  Optional<AccountDetailsDto> createUserDetailBy(AccountDetailsPostDto accountDetailsPostDto);
}
