package com.studies.prog4.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Employee implements Serializable {
  public String reference;
  private UUID id;
  private String firstName;
  private String lastName;
  private LocalDate birthDate;
}