package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.CommentDto;
import com.example.minidooraygateway.domain.MileStoneDto;
import com.example.minidooraygateway.domain.TagDto;
import com.example.minidooraygateway.domain.TaskDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
  public List<TaskDto> selectAllTaskBy(String projectId) {
    return null;
  }

  @Override
  public void createTaskBy(TaskDto taskDto) {

  }

  @Override
  public void updateTaskBy(TaskDto taskDto) {

  }

  @Override
  public void deleteTaskBy(String taskId) {

  }

  @Override
  public List<CommentDto> selectAllCommentBy(String projectId) {
    return null;
  }

  @Override
  public void createCommentBy(CommentDto commentDto) {

  }

  @Override
  public void updateCommentBy(CommentDto commentDto) {

  }

  @Override
  public void deleteCommentBy(String commentId) {

  }

  @Override
  public List<TagDto> selectAllTagBy(String taskId) {
    return null;
  }

  @Override
  public void attachTag() {

  }

  @Override
  public void detachTag() {

  }

  @Override
  public List<MileStoneDto> selectAllMileStoneBy(String taskId) {
    return null;
  }

  @Override
  public void attachMileStone() {

  }

  @Override
  public void detachMileStone() {

  }
}
