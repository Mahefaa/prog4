package com.studies.prog4.controller.view.mapper;

import com.studies.prog4.controller.view.model.CreateEmployee;
import com.studies.prog4.controller.view.model.ViewEmployee;
import com.studies.prog4.controller.view.model.validator.CreateEmployeeValidator;
import com.studies.prog4.model.Employee;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class EmployeeMapper {
  private static final String BASE64_PREFIX = "data:image/png;base64,";
  private final ByteMapper byteMapper;
  private final CreateEmployeeValidator createValidator;

  public Employee toDomain(CreateEmployee createEmployee) {
    log.info("phones = {}", createEmployee.getPhones());
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
        .sex(createEmployee.getSex())
        .csp(createEmployee.getCsp())
        .address(createEmployee.getAddress())
        .emailPro(createEmployee.getEmailPro())
        .emailPerso(createEmployee.getEmailPerso())
        .role(createEmployee.getRole())
        .childNumber(createEmployee.getChildNumber())
        .hiringDate(createEmployee.getHiringDate())
        .departureDate(createEmployee.getDepartureDate())
        .cnaps(createEmployee.getCnaps())
        .phones(List.of())
        .nic(createEmployee.getNic())
        .build();
  }

  public ViewEmployee toView(Employee employee) {
    return ViewEmployee.builder()
        .id(employee.getId())
        .reference(employee.getReference())
        .birthDate(employee.getBirthDate())
        .lastName(employee.getLastName())
        .firstName(employee.getFirstName())
        .profilePicture(toBase64ImageString(employee.getProfilePicture()))
        .sex(employee.getSex())
        .csp(employee.getCsp())
        .address(employee.getAddress())
        .emailPro(employee.getEmailPro())
        .emailPerso(employee.getEmailPerso())
        .role(employee.getRole())
        .childNumber(employee.getChildNumber())
        .hiringDate(employee.getHiringDate())
        .departureDate(employee.getDepartureDate())
        .cnaps(employee.getCnaps())
        .phones(employee.getPhones())
        .nic(employee.getNic())
        .build();
  }

  private String toBase64ImageString(String base64String) {
    return BASE64_PREFIX + base64String;
  }
}
