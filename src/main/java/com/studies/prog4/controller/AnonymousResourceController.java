package com.studies.prog4.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.HandlerInterceptor;

public class AnonymousResourceController implements HandlerInterceptor {
  @ModelAttribute
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
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
    if (validToken){
      response.sendRedirect("/employees");
      return false;
    }
    return true;
  }
}
