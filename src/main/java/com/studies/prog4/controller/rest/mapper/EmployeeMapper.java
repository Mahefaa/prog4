package com.studies.prog4.controller.rest.mapper;

import com.studies.prog4.controller.rest.model.CreateEmployee;
import com.studies.prog4.controller.rest.model.RestEmployee;
import com.studies.prog4.controller.rest.model.validator.CreateEmployeeValidator;
import com.studies.prog4.model.Employee;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeMapper {
  private static final String BASE64_PREFIX = "data:image/png;base64,";
  private final ByteMapper byteMapper;
  private final CreateEmployeeValidator createValidator;

  public Employee toDomain(CreateEmployee createEmployee) {
    createValidator.accept(createEmployee);
    byte[] bytes;
    try {
      bytes = createEmployee.getProfilePicture().getBytes();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return Employee.builder()
        .firstName(createEmployee.getFirstName())
        .lastName(createEmployee.getLastName())
        .birthDate(createEmployee.getBirthDate())
        .profilePicture(byteMapper.toBase64(bytes))
        .build();
  }

  public RestEmployee toRest(Employee employee) {
    return RestEmployee.builder()
        .id(employee.getId())
        .reference(employee.getReference())
        .birthDate(employee.getBirthDate())
        .lastName(employee.getLastName())
        .firstName(employee.getFirstName())
        .profilePicture(toBase64ImageString(employee.getProfilePicture()))
        .build();
  }

  private String toBase64ImageString(String base64String) {
    return BASE64_PREFIX + base64String;
  }
}
