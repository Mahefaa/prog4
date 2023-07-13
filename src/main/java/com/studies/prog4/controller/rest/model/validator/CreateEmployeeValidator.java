package com.studies.prog4.controller.rest.model.validator;

import com.studies.prog4.controller.rest.model.CreateEmployee;
import java.util.function.Consumer;
import org.springframework.stereotype.Component;

import static java.time.LocalDate.now;

@Component
public class CreateEmployeeValidator implements Consumer<CreateEmployee> {
  @Override
  public void accept(CreateEmployee createEmployee) {
    StringBuilder sb = new StringBuilder();
    if (createEmployee.getFirstName() == null && createEmployee.getLastName() == null) {
      sb.append("FirstName and LastName are mandatory");
    } else if (createEmployee.getFirstName() != null && createEmployee.getFirstName().isBlank() &&
        createEmployee.getLastName() != null &&
        createEmployee.getLastName().isBlank()) {
      sb.append("FirstName and LastName cannot be blank");
    }
    if (createEmployee.getBirthDate().isAfter(now())) {
      sb.append("Employee cannot be born after today");
    }
    if (!sb.isEmpty()) {
      throw new RuntimeException(sb.toString());
    }
  }
}
