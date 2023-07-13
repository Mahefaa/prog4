package com.studies.prog4.repository.model.validator;

import com.studies.prog4.model.Employee;
import java.util.function.Consumer;
import org.springframework.stereotype.Component;

import static java.time.LocalDate.now;

@Component
public class EmployeeValidator implements Consumer<Employee> {
  @Override
  public void accept(Employee employee) {
    StringBuilder sb = new StringBuilder();
    if (employee.getFirstName() == null && employee.getLastName() == null) {
      sb.append("FirstName and LastName are mandatory");
    } else if (employee.getFirstName() != null && employee.getFirstName().isBlank() &&
        employee.getLastName() != null &&
        employee.getLastName().isBlank()) {
      sb.append("FirstName and LastName cannot be blank");
    }
    if (employee.getBirthDate().isAfter(now())) {
      sb.append("Employee cannot be born after today");
    }
    if (!sb.isEmpty()) {
      throw new RuntimeException(sb.toString());
    }
  }
}
