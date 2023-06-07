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

  //GET http://localhost:7070/dooray/task/{projectId}
  @Override
  public List<TaskDto> selectAllTaskBy(String projectId) {
    return null;
  }

  //POST http://localhost:7070/dooray/task
  // {taskDto}
  @Override
  public void createTaskBy(TaskDto taskDto) {

  }

  //PUT http://localhost:7070/dooray/task
  // {taskDto}
  @Override
  public void updateTaskBy(TaskDto taskDto) {

  }

  //DELETE http://localhost:7070/dooray/task/{taskId}
  @Override
  public void deleteTaskBy(String taskId) {

  }

  //GET http://localhost:7070/dooray/task/comment/{taskId}
  @Override
  public List<CommentDto> selectAllCommentBy(String taskId) {
    return null;
  }


  //POST http://localhost:7070/dooray/task/comment
  // {commentDto}
  @Override
  public void createCommentBy(CommentDto commentDto) {

  }

  //PUT http://localhost:7070/dooray/task/comment
  // {commentDto}
  @Override
  public void updateCommentBy(CommentDto commentDto) {

  }

  //DELETE http://localhost:7070/dooray/task/comment/{taskId}
  @Override
  public void deleteCommentBy(String commentId) {

  }

  //GET http://localhost:7070/dooray/task/tag/{taskId}
  @Override
  public List<TagDto> selectAllTagBy(String taskId) {
    return null;
  }

  //나중에
  @Override
  public void attachTag() {

  }

  //나중에
  @Override
  public void detachTag() {

  }

  //GET http://localhost:7070/dooray/task/milestone/{taskId}
  @Override
  public List<MileStoneDto> selectAllMileStoneBy(String taskId) {
    return null;
  }

  //나중에
  @Override
  public void attachMileStone() {

  }

  //나중에
  @Override
  public void detachMileStone() {

  }
}
