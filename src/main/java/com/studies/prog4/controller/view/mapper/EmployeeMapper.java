package com.studies.prog4.controller.view.mapper;

import com.studies.prog4.controller.view.model.CrupdateEmployee;
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
public class EmployeeMapper {
  private static final String BASE64_PREFIX = "data:image/png;base64,";
  private final ByteMapper byteMapper;
  private final CreateEmployeeValidator createValidator;

  public Employee toDomain(CrupdateEmployee crupdateEmployee) {
    createValidator.accept(crupdateEmployee);
    byte[] bytes;
    try {
      bytes = crupdateEmployee.getProfilePicture().getBytes();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return Employee.builder()
        .firstName(crupdateEmployee.getFirstName())
        .lastName(crupdateEmployee.getLastName())
        .birthDate(crupdateEmployee.getBirthDate())
        .profilePicture(byteMapper.toBase64(bytes))
        .sex(crupdateEmployee.getSex())
        .csp(crupdateEmployee.getCsp())
        .address(crupdateEmployee.getAddress())
        .emailPro(crupdateEmployee.getEmailPro())
        .emailPerso(crupdateEmployee.getEmailPerso())
        .role(crupdateEmployee.getRole())
        .childNumber(crupdateEmployee.getChildNumber())
        .hiringDate(crupdateEmployee.getHiringDate())
        .departureDate(crupdateEmployee.getDepartureDate())
        .cnaps(crupdateEmployee.getCnaps())
        .phones(List.of())
        .nic(crupdateEmployee.getNic())
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
