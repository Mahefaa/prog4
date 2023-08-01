package com.studies.prog4.controller;

import com.studies.prog4.service.CompanyService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.bind.annotation.ModelAttribute;

public class AnonymousResourceController extends GlobalResourceProvider {
  public AnonymousResourceController(CompanyService companyService) {
    super(companyService);
  }

  @ModelAttribute
  public void preHandle(HttpServletRequest request, HttpServletResponse response,
                        Object handler) throws IOException {
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
    if (validToken) {
      response.sendRedirect("/employees");
    }
  }
}
