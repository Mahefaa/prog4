package com.studies.prog4.model;

import com.studies.prog4.repository.model.EmployeePhone;
import com.studies.prog4.repository.model.NIC;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Builder(toBuilder = true)
@ToString
public class Employee implements Serializable {
  private String reference;
  private UUID id;
  private String firstName;
  private String lastName;
  private LocalDate birthDate;
  private String profilePicture;
  private com.studies.prog4.repository.model.Employee.Sex sex;

  private com.studies.prog4.repository.model.Employee.Csp csp;

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