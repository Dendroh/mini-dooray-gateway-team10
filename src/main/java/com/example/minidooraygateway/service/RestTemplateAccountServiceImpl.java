package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.AccountDto;
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

  private final PasswordEncoder passwordEncoder;

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
  public Optional<AccountDto> selectUserBy(String accountEmail) {

    HttpEntity<String> httpEntity = createHttpEntity(null);
    log.info(accountEmail);

    ResponseEntity<AccountDto> response = restTemplate.exchange("http://localhost:8081/accounts/email/"+accountEmail,
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
  public Optional<AccountDto> createUserBy(AccountDto accountDto) {

    AccountDto temp = new AccountDto();
    temp.setAccountId(0);
    temp.setEmail(accountDto.getEmail());
    temp.setPassword(passwordEncoder.encode(accountDto.getPassword()));

    HttpEntity<String> httpEntity = createHttpEntity(temp);

    ResponseEntity<AccountDto> response = restTemplate.exchange("http://localhost:8081/accounts/",
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }









  //PUT http://localhost:9090/accounts
  //accountDto
  @Override
  public void updateUserBy(AccountDto accountDto) {

    HttpEntity<String> httpEntity = createHttpEntity(accountDto);

    restTemplate.exchange("http://localhost:9090/accounts/",
            HttpMethod.PUT,
            httpEntity,
            new ParameterizedTypeReference<>() {});
  }

  //DELETE http://localhost:9090/accounts/accountId
  @Override
  public void deleteUserBy(String accountId) {

    HttpEntity<String> httpEntity = createHttpEntity(null);

    restTemplate.exchange("http://localhost:9090/accounts/" + accountId,
            HttpMethod.DELETE,
            httpEntity,
            new ParameterizedTypeReference<>() {});
  }
}
