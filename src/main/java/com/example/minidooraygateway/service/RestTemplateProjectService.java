package com.example.minidooraygateway.service;

import com.example.minidooraygateway.domain.*;

import java.util.List;
import java.util.Optional;

public interface RestTemplateProjectService {

  List<ProjectDto> selectAllProjectBy(String accountEmail);

  Optional<ProjectDto> createProjectBy(ProjectRegisterDto projectRegisterDto);

  ProjectDto selectProjectBy (String projectId);

  List<MemberDto> selectMembersBy(String projectId);

  List<MemberDto> selectMemberAllBy();

  Optional<ProjectDto> updateProjectBy (ProjectUpdateDto projectUpdateDto);

  void deleteProjectBy (String projectId);


  Optional<MemberDto> createMemberBy(MemberRegisterDto memberRegisterDto);
  Optional<MemberDto> updateMemberBy(MemberUpdateDto memberUpdateDto);
  Optional<MemberDto> updateMemberDetailsBy(MemberDetailsUpdateDto memberDetailsUpdateDto);
  Optional<ProjectDto> addProjectMemberBy (ProjectMemberDto projectMemberDto);
  void delProjectMemberBy (ProjectMemberDto projectMemberDto);


  Optional<MileStoneDto> addProjectMileStonesBy(MileStoneRegisterDto mileStoneRegisterDto);

  List<MileStoneDto> selectProjectMileStonesBy(String projectId);
  void delProjectMileStonesBy(String milestoneId);



  Optional<TagDto> addProjectTagsBy(TagRegisterDto tagRegisterDto);

  List<TagDto> selectProjectTagsBy(String projectId);
  void delProjectTagsBy(String milestoneId);





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
//  void createMileStoneBy(MileStoneDto tagDto);
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
