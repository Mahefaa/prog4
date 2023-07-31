package com.studies.prog4.controller.view.model;

import com.studies.prog4.repository.model.Employee;
import com.studies.prog4.repository.model.EmployeePhone;
import com.studies.prog4.repository.model.NIC;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Builder(toBuilder = true)
@ToString
public class CrupdateEmployee implements Serializable {
  private String firstName;
  private String lastName;
  private LocalDate birthDate;
  private MultipartFile profilePicture;
  private Employee.Sex sex;

  private Employee.Csp csp;

  private String address;

  private String emailPro;

  private String emailPerso;

  private String role;

  private Integer childNumber;

  private LocalDate hiringDate;

  private LocalDate departureDate;

  private String cnaps;

  private List<EmployeePhone> phones;

  private NIC nic;
}