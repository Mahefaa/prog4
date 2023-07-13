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
        .build();
  }
}
