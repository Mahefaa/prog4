package com.studies.prog4.controller;

import com.studies.prog4.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static java.util.UUID.randomUUID;

@Controller
@AllArgsConstructor
public class SecurityController extends AnonymousResourceController {
  private final UserService service;

  @GetMapping("*")
  public String handleUnknownRoutes() {
    return "redirect:/login";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @PostMapping("/")
  public String login(
      @RequestParam("username") String username,
      @RequestParam("password") String password,
      HttpServletRequest request,
      HttpServletResponse response) {
    Cookie[] cookies = request.getCookies();
    if (Arrays.stream(cookies)
        .anyMatch(cookie -> Objects.equals(cookie.getName(), "token"))) {
      return "redirect:/employees";
    }
    if (service.existsByUsernameAndPassword(username, password)) {
      int tokenValidityInSeconds = Math.toIntExact(TimeUnit.HOURS.toSeconds(1));
      Cookie cookie = new Cookie("token", randomUUID().toString());
      cookie.setMaxAge(tokenValidityInSeconds);
      cookie.setPath("/");
      response.addCookie(cookie);
      return "redirect:/employees";
    } else {
      return "redirect:/login";
    }
  }
}
