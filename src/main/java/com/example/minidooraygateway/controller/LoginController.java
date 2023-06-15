package com.example.minidooraygateway.controller;

import com.example.minidooraygateway.domain.*;
import com.example.minidooraygateway.service.RestTemplateAccountService;
import com.example.minidooraygateway.service.RestTemplateProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

  private final RestTemplateAccountService restTemplateAccountService;

  private final RestTemplateProjectService restTemplateProjectService;

  private final PasswordEncoder passwordEncoder;

  @GetMapping("/login")
  public String loginPageMapping(Model model, Principal principal) {
    model.addAttribute("statusMessage", "서비스 이용을 위해 로그인 해주세요.");
    if (principal != null) {
      return "redirect:/api";
    }
    return "loginPage";
  }

  @GetMapping("/register")
  public String registerPageMapping(Model model, Principal principal) {
    model.addAttribute("statusMessage", "새로운 유저의 회원가입을 축하합니다!");
    if (principal != null) {
      return "redirect:/api";
    }
    return "registerPage";
  }

  @GetMapping("/userInfoPage")
  public String userInfoPageMapping(Model model, Principal principal) {
    model.addAttribute("statusMessage", "회원정보 변경 페이지입니다.");

    if (restTemplateAccountService.selectUserBy(principal.getName()).isPresent()) {
      model.addAttribute("accountDto", restTemplateAccountService.selectUserBy(principal.getName()).get());
    }
    if (restTemplateAccountService.selectUserDetailBy(principal.getName()).isPresent()) {
      model.addAttribute("accountDetails", restTemplateAccountService.selectUserDetailBy(principal.getName()).get());
    }
    return "userInfoPage";
  }

  @PostMapping("/register")
  public String registerNewAccount(Model model, Principal principal, @ModelAttribute("RegisterRequest") AccountDto accountDto,
                                   BindingResult bindingResult) {

    AccountDto tempAccountDto = new AccountDto();
    tempAccountDto.setAccountId(0);
    tempAccountDto.setEmail(accountDto.getEmail());
    tempAccountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));

    if (restTemplateAccountService.createUserBy(tempAccountDto).isPresent()) {

      AccountDetailsPostDto accountDetailsPostDto = new AccountDetailsPostDto();
      accountDetailsPostDto.setName(accountDto.getName());
      accountDetailsPostDto.setAccountEmail(accountDto.getEmail());
      restTemplateAccountService.createUserDetailBy(accountDetailsPostDto);

      MemberRegisterDto memberRegisterDto = new MemberRegisterDto();
      memberRegisterDto.setMemberName(accountDto.getName());
      memberRegisterDto.setMemberEmail(accountDto.getEmail());
      restTemplateProjectService.createMemberBy(memberRegisterDto);

      model.addAttribute("statusMessage", "회원가입에 성공했습니다!");
    } else {
      model.addAttribute("statusMessage", "회원가입에 실패했습니다!");
    }
    if (principal != null) {
      return "redirect:/api";
    }
    return "loginPage";
  }

  @PostMapping("/register/update")
  public String updateAccount(Model model, Principal principal, @ModelAttribute("AccountUpdateDto") AccountUpdateDto accountUpdateDto,
                              BindingResult bindingResult) {
    model.addAttribute("statusMessage", "유저 정보가 변경되었습니다.");
    AccountUpdateDto tempAccountUpdateDto = new AccountUpdateDto();
    tempAccountUpdateDto.setBeforeEmail(accountUpdateDto.getBeforeEmail());
    tempAccountUpdateDto.setAfterEmail(accountUpdateDto.getAfterEmail());
    tempAccountUpdateDto.setPassword(passwordEncoder.encode(accountUpdateDto.getPassword()));
    restTemplateAccountService.updateUserBy(tempAccountUpdateDto);

    MemberUpdateDto tempMemberUpdateDto = new MemberUpdateDto();
    tempMemberUpdateDto.setEmail(accountUpdateDto.getBeforeEmail());
    tempMemberUpdateDto.setNewEmail(accountUpdateDto.getAfterEmail());

    restTemplateProjectService.updateMemberBy(tempMemberUpdateDto);

    if (principal != null) {
      return "redirect:/logout";
    }
    return "loginPage";
  }

  @PostMapping("/register/updateDetails")
  public String updateAccountDetail(Model model, Principal principal, @ModelAttribute("AccountDetailsUpdateDto") AccountDetailsUpdateDto accountDetailsUpdateDto,
                                    BindingResult bindingResult) {
    model.addAttribute("statusMessage", "유저 정보가 변경되었습니다.");
    restTemplateAccountService.updateUserDetailsBy(accountDetailsUpdateDto);

    MemberDetailsUpdateDto memberDetailsUpdateDto = new MemberDetailsUpdateDto();
    memberDetailsUpdateDto.setEmail(accountDetailsUpdateDto.getAccountEmail());
    memberDetailsUpdateDto.setNewName(accountDetailsUpdateDto.getName());

    restTemplateProjectService.updateMemberDetailsBy(memberDetailsUpdateDto);
    if (principal != null) {
      return "redirect:/userInfoPage";
    }
    return "loginPage";
  }

  @DeleteMapping("/api/accountDelete")
  public String projectEditDeleting(Model model, Principal principal) {
    restTemplateProjectService.deleteMemberBy(principal.getName());
    restTemplateAccountService.deleteUserBy(principal.getName());

    if (principal != null) {
      return "redirect:/logout";
    }
    return "loginPage";
  }
}
