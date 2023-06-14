package com.example.minidooraygateway.controller;

import com.example.minidooraygateway.domain.*;
import com.example.minidooraygateway.service.RestTemplateProjectService;
import com.example.minidooraygateway.service.RestTemplateTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@Controller
public class MainController {

  RestTemplateProjectService restTemplateProjectService;

  RestTemplateTaskService restTemplateTaskService;

  public MainController(RestTemplateProjectService restTemplateProjectService, RestTemplateTaskService restTemplateTaskService) {

    this.restTemplateProjectService = restTemplateProjectService;
    this.restTemplateTaskService = restTemplateTaskService;
  }

  @GetMapping("/")
  public String initialPageMapping() {
    return "redirect:/api";
  }

  @GetMapping("/userInfoPage")
  public String userInfoPageMapping(Model model) {
    model.addAttribute("statusMessage", "회원정보 변경 페이지입니다.");
    return "userInfoPage";
  }

  @GetMapping("/api/**")
  public String webPageMapping(Model model, Principal principal) {
    model.addAttribute("statusMessage", "로그인에 성공했습니다.");
    model.addAttribute("projectList", restTemplateProjectService.selectAllProjectBy(principal.getName()));
    model.addAttribute("taskList", restTemplateTaskService.selectAllTaskBy(principal.getName()));
    return "mainPage";
  }

  @GetMapping("/api/projectRegister")
  public String projectRegisterMapping(Model model, Principal principal) {
    model.addAttribute("statusMessage", "새로운 프로젝트를 등록합니다.");
    model.addAttribute("adminEmail", principal.getName());
    model.addAttribute("projectList", restTemplateProjectService.selectAllProjectBy(principal.getName()));
    model.addAttribute("taskList", restTemplateTaskService.selectAllTaskBy(principal.getName()));
    return "registerProject";
  }

  @PostMapping("/api/projectRegister")
  public String projectRegisterPosting(Model model, Principal principal, @ModelAttribute("projectRegisterDto") ProjectRegisterDto projectRegisterDto,
                                       BindingResult bindingResult) {
    if(restTemplateProjectService.createProjectBy(projectRegisterDto).isPresent()) {
      ProjectMemberDto projectMemberDto = new ProjectMemberDto();
      projectMemberDto.setProjectTitle(projectRegisterDto.getProjectTitle());
      projectMemberDto.setMemberEmail(principal.getName());
      restTemplateProjectService.addProjectMemberBy(projectMemberDto);
      model.addAttribute("statusMessage", "프로젝트 생성에 성공했습니다!");
    } else {
      model.addAttribute("statusMessage", "프로젝트 생성에 실패했습니다!");
    }
    return "redirect:/api";
  }

  @GetMapping("/api/projectView")
  public String projectViewMapping(Model model, Principal principal, @RequestParam("projectId") String projectId) {
    model.addAttribute("statusMessage", "Dooray 프로젝트에 등록된 프로젝트입니다.");
    model.addAttribute("projectView", restTemplateProjectService.selectProjectBy(projectId));
    model.addAttribute("memberView", restTemplateProjectService.selectMembersBy(projectId));
    model.addAttribute("projectList", restTemplateProjectService.selectAllProjectBy(principal.getName()));
    model.addAttribute("taskList", restTemplateTaskService.selectAllTaskBy(principal.getName()));
    model.addAttribute("mileStoneView", restTemplateProjectService.selectMileStonesBy(projectId));
    model.addAttribute("tagView", restTemplateProjectService.selectTagsBy(projectId));
    return "viewProject";
  }

  @GetMapping("/api/taskRegister")
  public String taskRegisterMapping(Model model, Principal principal, @RequestParam("projectId") String projectId) {
    model.addAttribute("statusMessage", "새로운 테스크를 등록합니다.");
    model.addAttribute("projectId", projectId);
    model.addAttribute("writerEmail", principal.getName());
    model.addAttribute("projectList", restTemplateProjectService.selectAllProjectBy(principal.getName()));
    model.addAttribute("taskList", restTemplateTaskService.selectAllTaskBy(principal.getName()));
    return "registerTask";
  }

  @PostMapping("/api/taskRegister")
  public String taskRegisterPosting(Model model, Principal principal, @ModelAttribute("taskRegisterDto") TaskRegisterDto taskRegisterDto,
                                       BindingResult bindingResult) {
    if(restTemplateTaskService.createTaskBy(taskRegisterDto).isPresent()) {
      model.addAttribute("statusMessage", "프로젝트 생성에 성공했습니다!");
    } else {
      model.addAttribute("statusMessage", "프로젝트 생성에 실패했습니다!");
    }
    return "redirect:/api";
  }

  @GetMapping("/api/taskView")
  public String taskViewMapping(Model model, Principal principal, @RequestParam("taskId") String taskId) {
    model.addAttribute("statusMessage", "Dooray 프로젝트에 등록된 테스크입니다.");
    model.addAttribute("taskView", restTemplateTaskService.selectTaskBy(taskId));
    model.addAttribute("projectList", restTemplateProjectService.selectAllProjectBy(principal.getName()));
    model.addAttribute("taskList", restTemplateTaskService.selectAllTaskBy(principal.getName()));
    return "viewTask";
  }

  @GetMapping("/api/projectEdit")
  public String projectEditMapping(Model model, Principal principal, @RequestParam("projectId") String projectId) {
    model.addAttribute("statusMessage", "Dooray 프로젝트에 등록된 프로젝트를 수정합니다.");
    model.addAttribute("projectView", restTemplateProjectService.selectProjectBy(projectId));
    model.addAttribute("memberAllView", restTemplateProjectService.selectMemberAllBy());
    model.addAttribute("memberView", restTemplateProjectService.selectMembersBy(projectId));
    model.addAttribute("projectList", restTemplateProjectService.selectAllProjectBy(principal.getName()));
    model.addAttribute("taskList", restTemplateTaskService.selectAllTaskBy(principal.getName()));
    model.addAttribute("mileStoneView", restTemplateProjectService.selectMileStonesBy(projectId));
    model.addAttribute("tagView", restTemplateProjectService.selectTagsBy(projectId));
    return "editProject";
  }

  @PutMapping("/api/projectEdit")
  public String projectEditPutting(Model model, Principal principal, @ModelAttribute("projectUpdateDto") ProjectUpdateDto projectUpdateDto,
                                   BindingResult bindingResult) {
    if(restTemplateProjectService.updateProjectBy(projectUpdateDto).isPresent()) {
      model.addAttribute("statusMessage", "프로젝트 생성에 성공했습니다!");
    } else {
      model.addAttribute("statusMessage", "프로젝트 생성에 실패했습니다!");
    }
    return "redirect:/api";
  }

  @DeleteMapping("/api/projectEdit")
  public String projectEditDeleting(Model model, Principal principal, @RequestParam("projectId") String projectId) {
    restTemplateProjectService.deleteProjectBy(projectId);
    return "redirect:/api";
  }

  @GetMapping("/api/taskEdit")
  public String taskEditMapping(Model model, Principal principal, @RequestParam("taskId") String taskId) {
    model.addAttribute("statusMessage", "Dooray 프로젝트에 등록된 테스크를 수정합니다.");
    model.addAttribute("taskView", restTemplateTaskService.selectTaskBy(taskId));
    model.addAttribute("projectList", restTemplateProjectService.selectAllProjectBy(principal.getName()));
    model.addAttribute("taskList", restTemplateTaskService.selectAllTaskBy(principal.getName()));
    return "editTask";
  }

  @PutMapping("/api/taskEdit")
  public String taskEditPutting(Model model, Principal principal, @ModelAttribute("taskUpdateDto") TaskUpdateDto taskUpdateDto,
                                   BindingResult bindingResult) {
    if(restTemplateTaskService.updateTaskBy(taskUpdateDto).isPresent()) {

      model.addAttribute("statusMessage", "프로젝트 생성에 성공했습니다!");
    } else {
      model.addAttribute("statusMessage", "프로젝트 생성에 실패했습니다!");
    }
    return "redirect:/api";
  }

  @DeleteMapping("/api/taskEdit")
  public String taskEditDeleting(Model model, Principal principal, @RequestParam("taskId") String taskId) {
    restTemplateTaskService.deleteTaskBy(taskId);
    return "redirect:/api";
  }

  @PostMapping("/api/member")
  public String projectMemberPosting(Model model, Principal principal, @ModelAttribute("projectMemberDto") ProjectMemberDto projectMemberDto,
                                     BindingResult bindingResult) {
    log.info(projectMemberDto.getMemberEmail());
    log.info(projectMemberDto.getProjectTitle());
    restTemplateProjectService.addProjectMemberBy(projectMemberDto);
    return "redirect:/api";
  }

  @DeleteMapping("/api/member")
  public String projectMemberDeleting(Model model, Principal principal, @ModelAttribute("projectMemberDto") ProjectMemberDto projectMemberDto,
                                     BindingResult bindingResult) {
    log.info(projectMemberDto.getMemberEmail());
    log.info(projectMemberDto.getProjectTitle());
    restTemplateProjectService.delProjectMemberBy(projectMemberDto);
    return "redirect:/api";
  }

  @PostMapping("/api/milestone")
  public String projectMileStonePosting(Model model, Principal principal, @RequestParam("mileStoneName") String mileStoneName, @RequestParam("projectId") Integer projectId) {
    MileStoneRegisterDto mileStoneRegisterDto = new MileStoneRegisterDto();
    mileStoneRegisterDto.setMilestoneName(mileStoneName);
    mileStoneRegisterDto.setProjectId(projectId);
    restTemplateProjectService.addProjectMileStoneBy(mileStoneRegisterDto);
    return "redirect:/api";
  }

  @DeleteMapping("/api/milestone")
  public String projectMileStoneDeleting(Model model, Principal principal, @RequestParam("mileStoneId") String mileStoneId) {
    restTemplateProjectService.delProjectMileStoneBy(mileStoneId);
    return "redirect:/api";
  }


  @PostMapping("/api/tag")
  public String projectTagPosting(Model model, Principal principal, @RequestParam("tagName") String tagName, @RequestParam("projectId") Integer projectId) {
    TagRegisterDto tagRegisterDto = new TagRegisterDto();
    tagRegisterDto.setTagName(tagName);
    tagRegisterDto.setTagColor("#000000");
    tagRegisterDto.setProjectId(projectId);
    restTemplateProjectService.addProjectTagBy(tagRegisterDto);
    return "redirect:/api";
  }

  @DeleteMapping("/api/tag")
  public String projectTagDeleting(Model model, Principal principal, @RequestParam("tagId") String tagId) {
    restTemplateProjectService.delProjectTagBy(tagId);
    return "redirect:/api";
  }
}
