package com.studies.prog4.repository.model.mapper;

import com.studies.prog4.repository.model.Employee;
import com.studies.prog4.repository.model.validator.EmployeeValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeEntityMapper {
  private final EmployeeValidator validator;
  public Employee toEntity(com.studies.prog4.model.Employee domain) {
    validator.accept(domain);
    return Employee.builder()
        .id(null) // will be set later
        .reference(null) //will be set later
        .firstName(domain.getFirstName())
        .lastName(domain.getLastName())
        .birthdate(domain.getBirthDate())
        .profilePicture(domain.getProfilePicture())
        .sex(String.valueOf(domain.getSex()))
        .csp(String.valueOf(domain.getCsp()))
        .address(domain.getAddress())
        .emailPro(domain.getEmailPro())
        .emailPerso(domain.getEmailPerso())
        .role(domain.getRole())
        .childNumber(domain.getChildNumber())
        .hiringDate(domain.getHiringDate())
        .departureDate(domain.getDepartureDate())
        .cnaps(domain.getCnaps())
        .phones(domain.getPhones())
        .nic(domain.getNic())
        .build();
  }

  public com.studies.prog4.model.Employee toDomain(Employee entity) {
    return com.studies.prog4.model.Employee.builder()
        .id(entity.getId())
        .reference(entity.getReference())
        .firstName(entity.getFirstName())
        .lastName(entity.getLastName())
        .birthDate(entity.getBirthdate())
        .profilePicture(entity.getProfilePicture())
        .sex(Employee.Sex.valueOf(entity.getSex()))
        .csp(Employee.Csp.valueOf(entity.getCsp()))
        .address(entity.getAddress())
        .emailPro(entity.getEmailPro())
        .emailPerso(entity.getEmailPerso())
        .role(entity.getRole())
        .childNumber(entity.getChildNumber())
        .hiringDate(entity.getHiringDate())
        .departureDate(entity.getDepartureDate())
        .cnaps(entity.getCnaps())
        .phones(entity.getPhones())
        .nic(entity.getNic())
        .build();
  }
}
