package com.example.minidooraygateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @GetMapping("/")
  public String initialMapping() {
    return "redirect:/web";
  }

  @GetMapping("/web")
  public String homeMapping(Model model) {
    model.addAttribute("statusMessage", "로그인에 성공했습니다.");
    return "mainPage";
  }
}
