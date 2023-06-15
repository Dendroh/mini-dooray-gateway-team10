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

    ResponseEntity<TaskDto> response = restTemplate.exchange("http://localhost:8082/task/",
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }

  @Override
  public List<TaskDto> selectAllTaskBy(String accountEmail) {
    HttpEntity<String> httpEntity = createHttpEntity(null);

    ResponseEntity<List<TaskDto>> response = restTemplate.exchange("http://localhost:8082/task/memberEmail/" + accountEmail,
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
  public Optional<TaskDto> updateTaskBy(TaskUpdateDto taskUpdateDto) {

    HttpEntity<String> httpEntity = createHttpEntity(taskUpdateDto);

    ResponseEntity<TaskDto> response = restTemplate.exchange("http://localhost:8082/task/",
            HttpMethod.PUT,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    if(response.getStatusCode().is2xxSuccessful()) {
      return Optional.ofNullable(response.getBody());
    } else {
      return null;
    }
  }

  @Override
  public void deleteTaskBy(String taskId) {
    HttpEntity<String> httpEntity = createHttpEntity(null);

    restTemplate.exchange("http://localhost:8082/task/" + taskId,
            HttpMethod.DELETE,
            httpEntity,
            new ParameterizedTypeReference<>() {});
  }


  @Override
  public TaskDto selectTaskBy(String taskId) {
    HttpEntity<String> httpEntity = createHttpEntity(null);

    ResponseEntity<TaskDto> response = restTemplate.exchange("http://localhost:8082/task/id/" + taskId,
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
  public Optional<TaskMileStoneDto> addTaskMileStonesBy(TaskMileStonePostDto taskMileStonePostDto) {

    HttpEntity<String> httpEntity = createHttpEntity(taskMileStonePostDto);

    ResponseEntity<TaskMileStoneDto> response = restTemplate.exchange("http://localhost:8082/task/milestone/",
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }

  @Override
  public List<MileStoneDto> selectTaskMileStonesBy(String taskName) {
    HttpEntity<String> httpEntity = createHttpEntity(null);

    ResponseEntity<List<MileStoneDto>> response = restTemplate.exchange("http://localhost:8082/task/milestone/" + taskName,
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
  public void delTaskMileStonesBy(String taskName, String mileStoneName) {
    HttpEntity<String> httpEntity = createHttpEntity(null);
    restTemplate.exchange("http://localhost:8082/task/milestone/" + taskName + "/" + mileStoneName,
            HttpMethod.DELETE,
            httpEntity,
            new ParameterizedTypeReference<>() {});

  }

  @Override
  public Optional<TaskTagDto> addTaskTagsBy(TaskTagPostDto taskTagPostDto) {

    HttpEntity<String> httpEntity = createHttpEntity(taskTagPostDto);

    ResponseEntity<TaskTagDto> response = restTemplate.exchange("http://localhost:8082/task/tag/",
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }

  @Override
  public List<TagDto> selectTaskTagsBy(String taskName) {
    HttpEntity<String> httpEntity = createHttpEntity(null);

    ResponseEntity<List<TagDto>> response = restTemplate.exchange("http://localhost:8082/task/tag/" + taskName,
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
  public void delTaskTagsBy(String taskName, String tagName) {
    HttpEntity<String> httpEntity = createHttpEntity(null);
    restTemplate.exchange("http://localhost:8082/task/tag/" + taskName + "/" + tagName,
            HttpMethod.DELETE,
            httpEntity,
            new ParameterizedTypeReference<>() {});
  }

  @Override
  public Optional<CommentDto> addTaskCommentsBy(CommentRegisterDto commentRegisterDto) {

    HttpEntity<String> httpEntity = createHttpEntity(commentRegisterDto);

    ResponseEntity<CommentDto> response = restTemplate.exchange("http://localhost:8082/comments/",
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }

  @Override
  public List<CommentDto> selectTaskCommentsBy(String taskId) {
    HttpEntity<String> httpEntity = createHttpEntity(null);

    ResponseEntity<List<CommentDto>> response = restTemplate.exchange("http://localhost:8082/comments/" + taskId,
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
  public Optional<CommentDto> updateTaskCommentBy(CommentUpdateDto commentUpdateDto) {

    HttpEntity<String> httpEntity = createHttpEntity(commentUpdateDto);

    ResponseEntity<CommentDto> response = restTemplate.exchange("http://localhost:8082/comments/",
            HttpMethod.PUT,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    if(response.getStatusCode().is2xxSuccessful()) {
      return Optional.ofNullable(response.getBody());
    } else {
      return null;
    }
  }

  @Override
  public void delTaskCommentsBy(String commentId) {
    HttpEntity<String> httpEntity = createHttpEntity(null);
    restTemplate.exchange("http://localhost:8082/comments/" + commentId,
            HttpMethod.DELETE,
            httpEntity,
            new ParameterizedTypeReference<>() {});
  }

}
