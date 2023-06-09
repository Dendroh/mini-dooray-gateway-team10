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
  public List<MemberDto> selectMemberAllBy() {
    HttpEntity<String> httpEntity = createHttpEntity(null);

    ResponseEntity<List<MemberDto>> response = restTemplate.exchange("http://localhost:8082/members/",
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
  public List<MemberDto> selectMembersBy(String projectId) {
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
  public Optional<ProjectDto> updateProjectBy(ProjectUpdateDto projectUpdateDto) {

    HttpEntity<String> httpEntity = createHttpEntity(projectUpdateDto);

    ResponseEntity<ProjectDto> response = restTemplate.exchange("http://localhost:8082/projects/",
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
  public void deleteProjectBy(String projectId) {
    HttpEntity<String> httpEntity = createHttpEntity(null);

    restTemplate.exchange("http://localhost:8082/projects/" + projectId,
            HttpMethod.DELETE,
            httpEntity,
            new ParameterizedTypeReference<>() {});
  }


  @Override
  public Optional<MemberDto> createMemberBy(MemberRegisterDto memberRegisterDto) {

    HttpEntity<String> httpEntity = createHttpEntity(memberRegisterDto);

    ResponseEntity<MemberDto> response = restTemplate.exchange("http://localhost:8082/members/",
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }


  @Override
  public Optional<MemberDto> updateMemberBy(MemberUpdateDto memberUpdateDto) {

    HttpEntity<String> httpEntity = createHttpEntity(memberUpdateDto);

    ResponseEntity<MemberDto> response = restTemplate.exchange("http://localhost:8082/members/email/",
            HttpMethod.PUT,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }

  @Override
  public Optional<MemberDto> updateMemberDetailsBy(MemberDetailsUpdateDto memberDetailsUpdateDto) {

    HttpEntity<String> httpEntity = createHttpEntity(memberDetailsUpdateDto);

    ResponseEntity<MemberDto> response = restTemplate.exchange("http://localhost:8082/members/name/",
            HttpMethod.PUT,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }

  @Override
  public void deleteMemberBy(String accountEmail) {

    HttpEntity<String> httpEntity = createHttpEntity(null);

    restTemplate.exchange("http://localhost:8082/members/email/" + accountEmail,
            HttpMethod.DELETE,
            httpEntity,
            new ParameterizedTypeReference<>() {});
  }

  @Override
  public Optional<ProjectDto> addProjectMemberBy(ProjectMemberDto projectMemberDto) {

    HttpEntity<String> httpEntity = createHttpEntity(projectMemberDto);

    ResponseEntity<ProjectDto> response = restTemplate.exchange("http://localhost:8082/projects/members",
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }

  @Override
  public void delProjectMemberBy(ProjectMemberDto projectMemberDto) {

    HttpEntity<String> httpEntity = createHttpEntity(null);

    restTemplate.exchange("http://localhost:8082/projects/members"
                    + "/"
                    + projectMemberDto.getProjectTitle()
                    + "/"
                    + projectMemberDto.getMemberEmail(),
            HttpMethod.DELETE,
            httpEntity,
            new ParameterizedTypeReference<>() {});
  }


  @Override
  public Optional<MileStoneDto> addProjectMileStonesBy(MileStoneRegisterDto mileStoneRegisterDto) {

    HttpEntity<String> httpEntity = createHttpEntity(mileStoneRegisterDto);

    ResponseEntity<MileStoneDto> response = restTemplate.exchange("http://localhost:8082/milestones/",
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }

  @Override
  public List<MileStoneDto> selectProjectMileStonesBy(String projectId) {
    HttpEntity<String> httpEntity = createHttpEntity(null);

    ResponseEntity<List<MileStoneDto>> response = restTemplate.exchange("http://localhost:8082/milestones/project/" + projectId,
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
  public void delProjectMileStonesBy(String milestoneId) {
    HttpEntity<String> httpEntity = createHttpEntity(null);
    restTemplate.exchange("http://localhost:8082/milestones/" + milestoneId,
            HttpMethod.DELETE,
            httpEntity,
            new ParameterizedTypeReference<>() {});
  }


  @Override
  public Optional<TagDto> addProjectTagsBy(TagRegisterDto tagRegisterDto) {

    HttpEntity<String> httpEntity = createHttpEntity(tagRegisterDto);

    ResponseEntity<TagDto> response = restTemplate.exchange("http://localhost:8082/tags/",
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<>() {});

    return Optional.ofNullable(response.getBody());
  }

  @Override
  public List<TagDto> selectProjectTagsBy(String projectId) {
    HttpEntity<String> httpEntity = createHttpEntity(null);

    ResponseEntity<List<TagDto>> response = restTemplate.exchange("http://localhost:8082/tags/" + projectId,
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
  public void delProjectTagsBy(String tagId) {
    HttpEntity<String> httpEntity = createHttpEntity(null);
    restTemplate.exchange("http://localhost:8082/tags/" + tagId,
            HttpMethod.DELETE,
            httpEntity,
            new ParameterizedTypeReference<>() {});
  }
}
