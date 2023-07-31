package com.studies.prog4.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Company {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String slogan;

  @Column(nullable = false)
  private String address;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String nif;

  @Column(nullable = false)
  private String stat;

  @Column(nullable = false)
  private String rcs;

  private String logo;

  @OneToMany(mappedBy = "company")
  private List<CompanyPhone> phones;
}
