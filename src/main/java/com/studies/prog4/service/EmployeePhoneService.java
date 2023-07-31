package com.studies.prog4.service;

import com.studies.prog4.model.Employee;
import com.studies.prog4.repository.EmployeePhoneRepository;
import com.studies.prog4.repository.model.EmployeePhone;
import com.studies.prog4.repository.model.mapper.EmployeeEntityMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeePhoneService {
  private final EmployeePhoneRepository repository;
  private final EmployeeEntityMapper employeeEntityMapper;

  public List<EmployeePhone> saveAllForEmployee(Employee employee,
                                                List<EmployeePhone> employeePhones) {
    var employeeEntity = employeeEntityMapper.toEntity(employee);
    return repository.saveAll(employeePhones.stream().map((e) -> {
      e.setEmployee(employeeEntity);
      return e;
    }).toList());
  }
}