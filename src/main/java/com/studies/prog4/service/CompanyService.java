package com.studies.prog4.service;

import com.studies.prog4.repository.CompanyRepository;
import com.studies.prog4.repository.model.Company;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyService {
  private final CompanyRepository companyRepository;

  public Company getCurrentCompany() {
    return companyRepository.findAll().get(0);
  }
}
