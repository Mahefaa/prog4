package com.studies.prog4.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class NIC {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private UUID uuid;
  private String id;
  private LocalDate issuingDate;
  private String issuingPlace;
  @OneToOne(mappedBy = "nic")
  private Employee employee;
}
