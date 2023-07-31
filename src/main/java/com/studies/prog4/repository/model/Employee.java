package com.studies.prog4.repository.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
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
@Entity
public class Employee implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @GeneratedValue
  private String reference;
  private String firstName;
  private String lastName;
  private LocalDate birthdate;
  private String profilePicture;
  private String sex;
  private String csp;

  @Column(nullable = false)
  private String address;

  @Column(nullable = false, unique = true)
  private String emailPro;

  @Column(nullable = false, unique = true)
  private String emailPerso;

  @Column(nullable = false)
  private String role;

  @Column(nullable = false)
  private Integer childNumber;

  @Column(nullable = false)
  private LocalDate hiringDate;

  private LocalDate departureDate;

  @Column(nullable = false)
  private String cnaps;

  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
  private List<EmployeePhone> phones;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "nic_id", referencedColumnName = "uuid")
  private NIC nic;

  public enum Sex {
    M, F
  }

  public enum Csp {
    M1, M2, OS1, OS2, OS3, OP1

  }
}