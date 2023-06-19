package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.*;

import java.util.Optional;

public interface RestTemplateAccountService {

  Optional<AccountGetDto> selectUserBy(String accountEmail);

  Optional<AccountGetDto> createUserBy(AccountDto accountDto);

  Optional<AccountDetailsDto> selectUserDetailBy(String accountEmail);

  Optional<AccountDetailsDto> createUserDetailBy(AccountDetailsPostDto accountDetailsPostDto);

  Optional<AccountGetDto> updateUserBy(AccountUpdateDto accountUpdateDto);

  Optional<AccountDetailsDto> updateUserDetailsBy(AccountDetailsUpdateDto accountDetailsUpdateDto);

  void deleteUserBy(String accountId);
}
