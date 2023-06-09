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

  @GetMapping("/api")
  public String webPageMapping(Model model, Principal principal) {
    model.addAttribute("statusMessage", "로그인에 성공했습니다.");
    return "mainPage";
  }
}
