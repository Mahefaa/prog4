package com.studies.prog4.service;

import com.studies.prog4.model.Employee;
import com.studies.prog4.repository.EmployeeRepository;
import com.studies.prog4.repository.model.mapper.EmployeeEntityMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {
  public static final LocalDate DEFAULT_START_DATE = LocalDate.ofYearDay(1970, 1);

  private final EmployeeEntityMapper mapper;
  private final EmployeeRepository repository;

  public List<Employee> getEmployeesByCriterias(
      String firstName,
      String lastName,
      String role,
      String sex,
      LocalDate hiringDateIntervalBegin,
      LocalDate hiringDateIntervalEnd,
      LocalDate departureDateIntervalBegin,
      LocalDate departureDateIntervalEnd,
      String sortAttribute,
      Sort.Direction sortDirection) {
    Pageable pageable = PageRequest.of(0, 10, sortDirection, sortAttribute);
    var now = LocalDate.now();
    return repository.findAllByCriterias(
            firstName,
            lastName,
            sex,
            role,
            hiringDateIntervalBegin != null ? hiringDateIntervalBegin : DEFAULT_START_DATE,
            hiringDateIntervalEnd != null ? hiringDateIntervalEnd : now,
            departureDateIntervalBegin != null ? hiringDateIntervalBegin : DEFAULT_START_DATE,
            departureDateIntervalEnd != null ? hiringDateIntervalEnd : now,
            pageable
        )
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