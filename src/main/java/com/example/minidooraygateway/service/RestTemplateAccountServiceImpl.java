package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestTemplateAccountServiceImpl implements RestTemplateAccountService {

  private final RestTemplate restTemplate;

  private HttpHeaders createHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    return headers;
  }

  private <T> HttpEntity<String> createHttpEntity(T body) {
    HttpHeaders headers = createHeaders();
    try {
      return new HttpEntity<>(new ObjectMapper().writeValueAsString(body), headers);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return new HttpEntity<>(headers);
    }
  }

  @Override
  public Optional<AccountGetDto> createUserBy(AccountDto accountDto) {

    HttpEntity<String> httpEntity = createHttpEntity(accountDto);

    ResponseEntity<AccountGetDto> response = restTemplate.exchange("http://localhost:8081/accounts/",
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }


  @Override
  public Optional<AccountGetDto> selectUserBy(String accountEmail) {

    HttpEntity<String> httpEntity = createHttpEntity(null);

    ResponseEntity<AccountGetDto> response = restTemplate.exchange("http://localhost:8081/accounts/email/"+accountEmail,
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    if(response.getStatusCode().is2xxSuccessful()) {
      return Optional.ofNullable(response.getBody());
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<AccountDetailsDto> createUserDetailBy(AccountDetailsPostDto accountDetailsPostDto) {

    HttpEntity<String> httpEntity = createHttpEntity(accountDetailsPostDto);

    ResponseEntity<AccountDetailsDto> response = restTemplate.exchange("http://localhost:8081/accountDetails/",
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }

  @Override
  public Optional<AccountDetailsDto> selectUserDetailBy(String accountEmail) {

    HttpEntity<String> httpEntity = createHttpEntity(null);

    ResponseEntity<AccountDetailsDto> response = restTemplate.exchange("http://localhost:8081/accountDetails/email/"+accountEmail,
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    if(response.getStatusCode().is2xxSuccessful()) {
      return Optional.ofNullable(response.getBody());
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<AccountUpdateDto> updateUserBy(AccountUpdateDto accountUpdateDto) {

    HttpEntity<String> httpEntity = createHttpEntity(accountUpdateDto);

    ResponseEntity<AccountUpdateDto> response = restTemplate.exchange("http://localhost:8081/accounts/",
            HttpMethod.PUT,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }

  @Override
  public Optional<AccountDetailsUpdateDto> updateUserDetailsBy(AccountDetailsUpdateDto accountDetailsUpdateDto) {

    HttpEntity<String> httpEntity = createHttpEntity(accountDetailsUpdateDto);

    ResponseEntity<AccountDetailsUpdateDto> response = restTemplate.exchange("http://localhost:8081/accountDetails/",
            HttpMethod.PUT,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }
}
