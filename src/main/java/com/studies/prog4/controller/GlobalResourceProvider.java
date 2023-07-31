package com.studies.prog4.controller;

import com.studies.prog4.repository.model.Company;
import com.studies.prog4.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;

@AllArgsConstructor
public class GlobalResourceProvider {
  private final CompanyService companyService;

  @ModelAttribute
  public Company getCompanyInfo() {
    return companyService.getCurrentCompany();
  }
}
