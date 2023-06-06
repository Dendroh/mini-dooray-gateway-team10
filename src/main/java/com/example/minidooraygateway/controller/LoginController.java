package com.example.minidooraygateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class LoginController {

  @GetMapping("/login")
  public String loginPageMapping(Model model, Principal principal) {
    model.addAttribute("statusMessage", "서비스 이용을 위해 로그인 해주세요.");
    if(principal != null) {
      return "redirect:/api";
    }
    return "loginPage";
  }

  @GetMapping("/register")
  public String registerPageMapping(Model model, Principal principal) {
    model.addAttribute("statusMessage", "새로운 유저의 회원가입을 축하합니다!");
    if(principal != null) {
      return "redirect:/api";
    }
    return "registerPage";
  }

  @PostMapping("/register")
  public String registerNewAccount(Model model, Principal principal) {
    model.addAttribute("statusMessage", "회원가입에 성공했습니다!");
    if(principal != null) {
      return "redirect:/api";
    }
    return "loginPage";
  }

}
