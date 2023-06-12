package com.example.minidooraygateway.controller;

import com.example.minidooraygateway.service.RestTemplateProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

  @GetMapping("/api/**")
  public String webPageMapping(Model model, Principal principal) {
    model.addAttribute("statusMessage", "로그인에 성공했습니다.");
    model.addAttribute("projectList", restTemplateProjectService.selectAllProjectBy(principal.getName()));
    return "mainPage";
  }

  @GetMapping("/api/projectRegister")
  public String projectRegisterMapping(Model model, Principal principal) {
    model.addAttribute("statusMessage", "새로운 프로젝트를 등록합니다.");
    model.addAttribute("projectList", restTemplateProjectService.selectAllProjectBy(principal.getName()));
    return "registerProject";
  }

  @GetMapping("/api/taskRegister")
  public String taskRegisterMapping(Model model, Principal principal) {
    model.addAttribute("statusMessage", "새로운 테스크를 등록합니다.");
    model.addAttribute("projectList", restTemplateProjectService.selectAllProjectBy(principal.getName()));
    return "registerTask";
  }

  @GetMapping("/api/projectView")
  public String projectViewMapping(Model model, Principal principal) {
    model.addAttribute("statusMessage", "Dooray 프로젝트에 등록된 프로젝트입니다.");
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
  public String projectEditMapping(Model model, Principal principal) {
    model.addAttribute("statusMessage", "Dooray 프로젝트에 등록된 프로젝트를 수정합니다.");
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
