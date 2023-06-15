package com.example.minidooraygateway.auth;

import com.example.minidooraygateway.domain.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@Slf4j
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  private final RedisTemplate<String, Object> redisTemplate;

  public LoginSuccessHandler(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                      Authentication authentication)
          throws IOException, ServletException {
    String sessionId = UUID.randomUUID().toString();
    Cookie cookie = new Cookie("SESSION", sessionId);
    cookie.setMaxAge(259200);

    response.addCookie(cookie);

    CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
    String username = customUserDetails.getUsername();
    String authority = new ArrayList<>(customUserDetails.getAuthorities()).get(0).getAuthority();

    redisTemplate.opsForHash().put(sessionId, "username", username);
    redisTemplate.opsForHash().put(sessionId, "authority", authority);

    super.onAuthenticationSuccess(request, response, authentication);
  }
}
