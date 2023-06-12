package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
  public Optional<ProjectDto> createProjectBy(ProjectDto projectDto) {

    HttpEntity<String> httpEntity = createHttpEntity(projectDto);

    ResponseEntity<ProjectDto> response = restTemplate.exchange("http://localhost:8082/projects/",
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }

  @Override
  public ProjectDto selectAllProjectBy(String accountEmail) {
    HttpEntity<String> httpEntity = createHttpEntity(null);

    ResponseEntity<ProjectDto> response = restTemplate.exchange("http://localhost:8082/projects/" + accountEmail,
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    if(response.getStatusCode().is2xxSuccessful()) {
      return response.getBody();
    } else {
      return null;
    }
  }


  //PUT http://localhost:7070/dooray/project
  //{projectDto}
  @Override
  public void updateProjectBy(ProjectDto projectDto) {

  }

  //GET http://localhost:7070/dooray/project/tag/{projectId}
  @Override
  public List<TagDto> selectAllTagBy(String projectId) {
    return null;
  }

  //POST http://localhost:7070/dooray/project/tag
  //{tagDto}
  @Override
  public void createTagBy(TagDto tagDto) {

  }

  //PUT http://localhost:7070/dooray/project/tag
  //{tagDto}
  @Override
  public void updateTagBy(TagDto tagDto) {

  }

  //DELETE http://localhost:7070/dooray/project/tag/{tagId}
  @Override
  public void deleteTagBy(String tagId) {

  }

  //GET http://localhost:7070/dooray/project/milestone/{projectId}
  @Override
  public List<MileStoneDto> selectAllMileStoneBy(String projectId) {
    return null;
  }

  //POST http://localhost:7070/dooray/project/milestone
  // {mileStoneDto}
  @Override
  public void createMileStoneBy(MileStoneDto mileStoneDto) {

  }

  //PUT http://localhost:7070/dooray/project/milestone
  // {mileStoneDto}
  @Override
  public void updateMileStoneBy(MileStoneDto mileStoneDto) {

  }

  //DELETE http://localhost:7070/dooray/project/milestone/{mileStoneId}
  @Override
  public void deleteMileStoneBy(String mileStoneId) {

  }

  //GET http://localhost:7070/dooray/project/member/{projectId}
  @Override
  public List<MemberDto> selectAllMemberBy(String projectId) {
    return null;
  }

  //나중에
  @Override
  public void attachMember() {

  }

  //나중에
  @Override
  public void detachMember() {

  }
}
