package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.AccountDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

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
  public Optional<AccountDto> selectUserBy(String accountId) {

    HttpEntity<String> httpEntity = createHttpEntity(null);

    ResponseEntity<AccountDto> response = restTemplate.exchange("http://localhost:9090/acounts/" + accountId,
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
  public void createUserBy(AccountDto accountDto) {

    HttpEntity<String> httpEntity = createHttpEntity(accountDto);

    restTemplate.exchange("http://localhost:9090/acounts/",
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<>() {});
  }

  @Override
  public void updateUserBy(AccountDto accountDto) {

    HttpEntity<String> httpEntity = createHttpEntity(accountDto);

    restTemplate.exchange("http://localhost:9090/acounts/",
            HttpMethod.PUT,
            httpEntity,
            new ParameterizedTypeReference<>() {});
  }

  @Override
  public void deleteUserBy(String accountId) {

    HttpEntity<String> httpEntity = createHttpEntity(null);

    restTemplate.exchange("http://localhost:9090/acounts/" + accountId,
            HttpMethod.DELETE,
            httpEntity,
            new ParameterizedTypeReference<>() {});
  }
}
