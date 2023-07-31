package com.studies.prog4.controller.view.model.validator;

import com.studies.prog4.controller.view.model.CrupdateEmployee;
import java.util.function.Consumer;
import org.springframework.stereotype.Component;

import static java.time.LocalDate.now;

@Component
public class CreateEmployeeValidator implements Consumer<CrupdateEmployee> {
  @Override
  public void accept(CrupdateEmployee crupdateEmployee) {
    StringBuilder sb = new StringBuilder();
    if (crupdateEmployee.getFirstName() == null && crupdateEmployee.getLastName() == null) {
      sb.append("FirstName and LastName are mandatory");
    } else if (crupdateEmployee.getFirstName() != null && crupdateEmployee.getFirstName().isBlank() &&
        crupdateEmployee.getLastName() != null &&
        crupdateEmployee.getLastName().isBlank()) {
      sb.append("FirstName and LastName cannot be blank");
    }
    if (crupdateEmployee.getBirthDate().isAfter(now())) {
      sb.append("Employee cannot be born after today");
    }
    if (!sb.isEmpty()) {
      throw new RuntimeException(sb.toString());
    }
  }
}
