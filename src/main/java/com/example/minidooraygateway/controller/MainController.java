package com.example.minidooraygateway.controller;

import com.example.minidooraygateway.domain.AccountDto;
import com.example.minidooraygateway.domain.ProjectDto;
import com.example.minidooraygateway.domain.ProjectRegisterDto;
import com.example.minidooraygateway.service.RestTemplateProjectService;
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

  public MainController(RestTemplateProjectService restTemplateProjectService) {
    this.restTemplateProjectService = restTemplateProjectService;
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
    return "mainPage";
  }

  @GetMapping("/api/projectRegister")
  public String projectRegisterMapping(Model model, Principal principal) {
    model.addAttribute("statusMessage", "새로운 프로젝트를 등록합니다.");
    model.addAttribute("adminEmail", principal.getName());
    model.addAttribute("projectList", restTemplateProjectService.selectAllProjectBy(principal.getName()));
    return "registerProject";
  }

  @PostMapping("/api/projectRegister")
  public String projectRegisterPosting(Model model, Principal principal, @ModelAttribute("projectRegisterDto") ProjectRegisterDto projectRegisterDto,
                                       BindingResult bindingResult) {
    model.addAttribute("statusMessage", "새로운 프로젝트를 등록했습니다.");
    if(restTemplateProjectService.createProjectBy(projectRegisterDto).isPresent()) {
      model.addAttribute("statusMessage", "회원가입에 성공했습니다!");
    } else {
      model.addAttribute("statusMessage", "회원가입에 실패했습니다!");
    }
    return "redirect:/api";
  }

  @GetMapping("/api/taskRegister")
  public String taskRegisterMapping(Model model, Principal principal) {
    model.addAttribute("statusMessage", "새로운 테스크를 등록합니다.");
    model.addAttribute("projectList", restTemplateProjectService.selectAllProjectBy(principal.getName()));
    return "registerTask";
  }

  @GetMapping("/api/projectView")
  public String projectViewMapping(Model model, Principal principal, @RequestParam("projectId") String projectId) {
    model.addAttribute("statusMessage", "Dooray 프로젝트에 등록된 프로젝트입니다.");
    model.addAttribute("projectView", restTemplateProjectService.selectProjectBy(projectId));
    model.addAttribute("memberView", restTemplateProjectService.selectAllMemberBy(projectId));
    model.addAttribute("projectList", restTemplateProjectService.selectAllProjectBy(principal.getName()));
    return "viewProject";
  }

  @GetMapping("/api/taskView")
  public String taskViewMapping(Model model, Principal principal) {
    model.addAttribute("statusMessage", "Dooray 프로젝트에 등록된 테스크입니다.");
    model.addAttribute("projectList", restTemplateProjectService.selectAllProjectBy(principal.getName()));
    return "viewTask";
  }

  @GetMapping("/api/projectEdit")
  public String projectEditMapping(Model model, Principal principal, @RequestParam("projectId") String projectId) {
    model.addAttribute("statusMessage", "Dooray 프로젝트에 등록된 프로젝트를 수정합니다.");
    model.addAttribute("projectView", restTemplateProjectService.selectProjectBy(projectId));
    model.addAttribute("memberView", restTemplateProjectService.selectAllMemberBy(projectId));
    model.addAttribute("projectList", restTemplateProjectService.selectAllProjectBy(principal.getName()));
    return "editProject";
  }

  @GetMapping("/api/taskEdit")
  public String taskEditMapping(Model model, Principal principal) {
    model.addAttribute("statusMessage", "Dooray 프로젝트에 등록된 테스크를 수정합니다.");
    model.addAttribute("projectList", restTemplateProjectService.selectAllProjectBy(principal.getName()));
    return "editTask";
  }
}
