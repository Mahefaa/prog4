package com.studies.prog4.repository;

import com.studies.prog4.repository.model.EmployeePhone;
import com.studies.prog4.repository.model.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePhoneRepository extends JpaRepository<EmployeePhone, UUID> {
}
