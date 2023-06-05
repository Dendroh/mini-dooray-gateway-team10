package com.example.minidooraygateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

  @GetMapping("/login")
  public String loginGetMapping(Model model) {
    model.addAttribute("statusMessage", "서비스 이용을 위해 로그인 해주세요.");
    return "loginPage";
  }

  @GetMapping("/register")
  public String registerGetMapping(Model model) {
    model.addAttribute("statusMessage", "새로운 유저의 회원가입을 축하합니다!");
    return "registerPage";
  }

  @PostMapping("/register")
  public String registerPostMapping(Model model) {
    model.addAttribute("statusMessage", "회원가입에 성공했습니다!");
    return "loginPage";
  }
}
