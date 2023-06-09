package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.AccountDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
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

  //GET http://localhost:9090/accounts/accountId
  @Override
  public Optional<AccountDto> selectUserBy(String accountEmail) {

    HttpEntity<String> httpEntity = createHttpEntity(null);

    ResponseEntity<AccountDto> response = restTemplate.exchange("http://localhost:9090/accounts/email/" + accountEmail,
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    log.info(response.getBody().getEmail() + "");
    log.info(response.getBody().getPassword() + "");
    log.info(response.getBody().getAccountId() + "");
    if(response.getStatusCode().is2xxSuccessful()) {
      log.info("good");
      return Optional.ofNullable(response.getBody());
    } else {
      log.info("not good");
      return Optional.empty();
    }
  }

  //POST http://localhost:9090/accounts
  //accountDto
  @Override
  public void createUserBy(AccountDto accountDto) {

    HttpEntity<String> httpEntity = createHttpEntity(accountDto);

    restTemplate.exchange("http://localhost:9090/accounts/",
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<>() {});
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
