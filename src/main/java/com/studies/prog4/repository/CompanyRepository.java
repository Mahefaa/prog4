package com.studies.prog4.repository;

import com.studies.prog4.repository.model.Company;
import com.studies.prog4.repository.model.Employee;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
