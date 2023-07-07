package com.studies.prog4.service;

import com.studies.prog4.model.Employee;
import com.studies.prog4.repository.EmployeeRepository;
import com.studies.prog4.repository.model.mapper.EmployeeEntityMapper;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {
  private final EmployeeEntityMapper mapper;
  private final EmployeeRepository repository;

  public List<Employee> getEmployees() {
    return repository.findAll()
        .stream()
        .map(mapper::toDomain)
        .toList();
  }

  public Employee getById(UUID id) {
    return mapper.toDomain(repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Employee.id=" + id + " not found.")));
  }

  public void saveEmployee(List<Employee> toSave) {
    repository.saveAll(toSave.stream()
        .map(mapper::toEntity)
        .toList());
  }
}