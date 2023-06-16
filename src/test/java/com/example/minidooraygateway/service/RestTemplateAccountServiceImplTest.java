package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.AccountDto;
import com.example.minidooraygateway.domain.AccountGetDto;
import com.example.minidooraygateway.domain.AccountUpdateDto;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class RestTemplateAccountServiceImplTest {


  private final RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

  private final RestTemplateAccountService restTemplateAccountService = new RestTemplateAccountServiceImpl(restTemplate);

  @Test
  void createUserBy() {
    AccountDto inputAccountDto = new AccountDto();
    inputAccountDto.setEmail("test@mail.com");

    AccountGetDto expectedResponse = new AccountGetDto();
    expectedResponse.setEmail("test@mail.com");

    ResponseEntity<AccountGetDto> mockResponseEntity = new ResponseEntity<>(expectedResponse, HttpStatus.OK);

    when(restTemplate.exchange(
            eq("http://localhost:8081/accounts/"),
            eq(HttpMethod.POST),
            any(HttpEntity.class),
            eq(new ParameterizedTypeReference<AccountGetDto>() {})
    )).thenReturn(mockResponseEntity);

    Optional<AccountGetDto> actualResponse = restTemplateAccountService.createUserBy(inputAccountDto);

    assertTrue(actualResponse.isPresent());
    assertEquals(expectedResponse, actualResponse.get());
  }

  @Test
  void selectUserBy() {
    AccountGetDto expectedBody = new AccountGetDto();
    expectedBody.setEmail("test@mail.com");

    ResponseEntity<AccountGetDto> mockResponseEntity = new ResponseEntity<>(expectedBody, HttpStatus.OK);

    when(restTemplate.exchange(
            eq("http://localhost:8081/accounts/email/" + "test@mail.com"),
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            any(ParameterizedTypeReference.class)
    )).thenReturn(mockResponseEntity);

    Optional<AccountGetDto> actualResponse = restTemplateAccountService.selectUserBy("test@mail.com");

    assertEquals(Optional.of(expectedBody), actualResponse);
  }


  @Test
  void updateUserBy() {
    AccountUpdateDto inputAccountDto = new AccountUpdateDto();
    inputAccountDto.setAfterEmail("test@mail.com");

    AccountGetDto expectedResponse = new AccountGetDto();
    expectedResponse.setEmail("test@mail.com");

    ResponseEntity<AccountGetDto> mockResponseEntity = new ResponseEntity<>(expectedResponse, HttpStatus.OK);

    when(restTemplate.exchange(
            eq("http://localhost:8081/accounts/"),
            eq(HttpMethod.PUT),
            any(HttpEntity.class),
            eq(new ParameterizedTypeReference<AccountGetDto>() {})
    )).thenReturn(mockResponseEntity);

    Optional<AccountGetDto> actualResponse = restTemplateAccountService.updateUserBy(inputAccountDto);

    assertTrue(actualResponse.isPresent());
    assertEquals(expectedResponse, actualResponse.get());
  }

  @Test
  void deleteUserBy() {
    String accountEmail = "test@mail.com";

    when(restTemplate.exchange(
            eq("http://localhost:8081/accounts/" + accountEmail),
            eq(HttpMethod.DELETE),
            any(HttpEntity.class),
            eq(new ParameterizedTypeReference<>() {})
    )).thenReturn(null);

    // When
    restTemplateAccountService.deleteUserBy(accountEmail);

    // Then
    verify(restTemplate).exchange(
            eq("http://localhost:8081/accounts/" + accountEmail),
            eq(HttpMethod.DELETE),
            any(HttpEntity.class),
            eq(new ParameterizedTypeReference<>() {})
    );
  }
}