package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
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
  public Optional<AccountGetDto> updateUserBy(AccountUpdateDto accountUpdateDto) {

    HttpEntity<String> httpEntity = createHttpEntity(accountUpdateDto);

    ResponseEntity<AccountGetDto> response = restTemplate.exchange("http://localhost:8081/accounts/",
            HttpMethod.PUT,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }

  @Override
  public Optional<AccountDetailsDto> updateUserDetailsBy(AccountDetailsUpdateDto accountDetailsUpdateDto) {

    HttpEntity<String> httpEntity = createHttpEntity(accountDetailsUpdateDto);

    ResponseEntity<AccountDetailsDto> response = restTemplate.exchange("http://localhost:8081/accountDetails/",
            HttpMethod.PUT,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }

  @Override
  public void deleteUserBy(String accountEmail) {

    HttpEntity<String> httpEntity = createHttpEntity(null);

    restTemplate.exchange("http://localhost:8081/accounts/" + accountEmail,
            HttpMethod.DELETE,
            httpEntity,
            new ParameterizedTypeReference<>() {});
  }
}
