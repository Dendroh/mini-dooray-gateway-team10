package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.AccountDto;

import java.util.Optional;

public interface RestTemplateAccountService {

  Optional<AccountDto> selectUserBy(String userId);

  void createUserBy(AccountDto accountDto);

  void updateUserBy(AccountDto accountDto);

  void deleteUserBy(String accountId);

}
