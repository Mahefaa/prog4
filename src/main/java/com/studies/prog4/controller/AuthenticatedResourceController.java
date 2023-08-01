package com.studies.prog4.controller;

import com.studies.prog4.service.CompanyService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AuthenticatedResourceController extends GlobalResourceProvider {
  public AuthenticatedResourceController(CompanyService companyService) {
    super(companyService);
  }

  @ModelAttribute
  public void preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    Cookie[] cookies = request.getCookies();
    boolean validToken = false;
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("token")) {
          String token = cookie.getValue();
          if (!token.isEmpty()) {
            validToken = true;
            break;
          }
        }
      }
    }
    if (!validToken) {
      response.sendRedirect("/login");
    }
  }

  @GetMapping("/logout")
  private String logout(HttpServletRequest request, HttpServletResponse response) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("token")) {
          cookie.setMaxAge(0);
          response.addCookie(cookie);
        }
      }
    }
    return "redirect:/login";
  }
}
