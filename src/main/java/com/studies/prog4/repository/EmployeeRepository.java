package com.studies.prog4.repository;

import com.studies.prog4.repository.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
