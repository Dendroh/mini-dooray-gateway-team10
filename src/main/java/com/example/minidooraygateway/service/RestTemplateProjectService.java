package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.*;

import java.util.List;
import java.util.Optional;

public interface RestTemplateProjectService {

  List<ProjectDto> selectAllProjectBy(String accountEmail);

  Optional<ProjectDto> createProjectBy(ProjectRegisterDto projectRegisterDto);

  ProjectDto selectProjectBy (String projectId);

  List<MemberDto> selectAllMemberBy(String projectId);




//  void updateProjectBy(ProjectDto projectDto);
//
//
//  List<TagDto> selectAllTagBy(String projectId);
//
//  void createTagBy(TagDto tagDto);
//
//  void updateTagBy(TagDto tagDto);
//
//  void deleteTagBy(String tagId);
//
//
//  List<MileStoneDto> selectAllMileStoneBy(String projectId);
//
//  void createMileStoneBy(MileStoneDto mileStoneDto);
//
//  void updateMileStoneBy(MileStoneDto mileStoneDto);
//
//  void deleteMileStoneBy(String mileStoneId);
//
//
//  List<MemberDto> selectAllMemberBy(String projectId);
//
//  void attachMember();
//
//  void detachMember();

}
