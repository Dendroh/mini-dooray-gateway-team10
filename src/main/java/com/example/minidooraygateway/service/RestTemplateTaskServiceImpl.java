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
public class RestTemplateTaskServiceImpl implements RestTemplateTaskService {

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
  public Optional<TaskDto> createTaskBy(TaskRegisterDto taskRegisterDto) {

    HttpEntity<String> httpEntity = createHttpEntity(taskRegisterDto);

    ResponseEntity<TaskDto> response = restTemplate.exchange("http://localhost:8082/projects/",
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }

  @Override
  public List<ProjectTaskDto> selectAllTaskBy(String accountEmail) {
    HttpEntity<String> httpEntity = createHttpEntity(null);

    ResponseEntity<List<ProjectTaskDto>> response = restTemplate.exchange("http://localhost:8082/task/memberEmail/" + accountEmail,
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
  public ProjectTaskDto selectTaskBy(String taskId) {
    HttpEntity<String> httpEntity = createHttpEntity(null);

    ResponseEntity<ProjectTaskDto> response = restTemplate.exchange("http://localhost:8082/task/id/" + taskId,
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
