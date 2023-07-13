package com.studies.prog4.controller.rest.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
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
public class CreateEmployee implements Serializable {
  public String reference;
  private UUID id;
  private String firstName;
  private String lastName;
  private LocalDate birthDate;
  private MultipartFile profilePicture;
}