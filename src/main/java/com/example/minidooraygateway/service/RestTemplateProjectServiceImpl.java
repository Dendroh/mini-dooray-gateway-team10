package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.*;
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
public class RestTemplateProjectServiceImpl implements RestTemplateProjectService {

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
  public Optional<ProjectDto> createProjectBy(ProjectRegisterDto projectRegisterDto) {

    HttpEntity<String> httpEntity = createHttpEntity(projectRegisterDto);

    ResponseEntity<ProjectDto> response = restTemplate.exchange("http://localhost:8082/projects/",
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }

  @Override
  public List<ProjectDto> selectAllProjectBy(String accountEmail) {
    HttpEntity<String> httpEntity = createHttpEntity(null);

    ResponseEntity<List<ProjectDto>> response = restTemplate.exchange("http://localhost:8082/projects/account/email/" + accountEmail,
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    if(response.getStatusCode().is2xxSuccessful()) {
      return response.getBody();
    } else {
      return null;
    }
  }

  @Override
  public ProjectDto selectProjectBy(String projectId) {
    HttpEntity<String> httpEntity = createHttpEntity(null);

    ResponseEntity<ProjectDto> response = restTemplate.exchange("http://localhost:8082/projects/id/" + projectId,
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    if(response.getStatusCode().is2xxSuccessful()) {
      return response.getBody();
    } else {
      return null;
    }
  }

  @Override
  public List<MemberDto> selectAllMemberBy(String projectId) {
    HttpEntity<String> httpEntity = createHttpEntity(null);

    ResponseEntity<List<MemberDto>> response = restTemplate.exchange("http://localhost:8082/projects/members/id/" + projectId,
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    if(response.getStatusCode().is2xxSuccessful()) {
      return response.getBody();
    } else {
      return null;
    }
  }


  @Override
  public List<ProjectTaskDto> selectAllTaskBy(String projectId) {
    HttpEntity<String> httpEntity = createHttpEntity(null);

    ResponseEntity<List<ProjectTaskDto>> response = restTemplate.exchange("http://localhost:8082/projects/members/id/" + projectId,
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    if(response.getStatusCode().is2xxSuccessful()) {
      return response.getBody();
    } else {
      return null;
    }
  }
}
