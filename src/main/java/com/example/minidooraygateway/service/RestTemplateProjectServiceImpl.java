package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.MemberDto;
import com.example.minidooraygateway.domain.MileStoneDto;
import com.example.minidooraygateway.domain.ProjectDto;
import com.example.minidooraygateway.domain.TagDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
  public List<ProjectDto> selectAllProjectBy(String accountId) {
    return null;
  }

  @Override
  public void createProjectBy(ProjectDto projectDto) {

  }

  @Override
  public void updateProjectBy(ProjectDto projectDto) {

  }

  @Override
  public List<TagDto> selectAllTagBy(String projectId) {
    return null;
  }

  @Override
  public void createTagBy(TagDto tagDto) {

  }

  @Override
  public void updateTagBy(TagDto tagDto) {

  }

  @Override
  public void deleteTagBy(String tagId) {

  }

  @Override
  public List<MileStoneDto> selectAllMileStoneBy(String projectId) {
    return null;
  }

  @Override
  public void createMileStoneBy(MileStoneDto mileStoneDto) {

  }

  @Override
  public void updateMileStoneBy(MileStoneDto mileStoneDto) {

  }

  @Override
  public void deleteMileStoneBy(String mileStoneId) {

  }

  @Override
  public List<MemberDto> selectAllMemberBy(String projectId) {
    return null;
  }

  @Override
  public void attachMember() {

  }

  @Override
  public void detachMember() {

  }
}
