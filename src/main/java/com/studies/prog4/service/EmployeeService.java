package com.studies.prog4.service;

import com.studies.prog4.model.Employee;
import com.studies.prog4.repository.EmployeeRepository;
import com.studies.prog4.repository.model.EmployeePhone;
import com.studies.prog4.repository.model.mapper.EmployeeEntityMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
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

  public void exportToCsv(List<Employee> employees, HttpServletResponse response) {
    String cellTitles =
        "First Name,Last Name,Birth Date,Reference,Gender,Csp,Address,Professional Email,Personal Email,Role,Child Number,Hiring Date,Departure Date,Cnaps Number,Phone Numbers, Identity Card Id\n";
    try (OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream())) {
      writer.write(cellTitles);
      for (Employee employee : employees) {
        writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
            employee.getFirstName(), employee.getLastName(), employee.getBirthDate(),
            employee.getReference(), employee.getSex(), employee.getCsp(), employee.getAddress(),
            employee.getEmailPro(), employee.getEmailPerso(), employee.getRole(),
            employee.getChildNumber(), employee.getHiringDate(), employee.getDepartureDate(),
            employee.getCnaps(),
            employee.getPhones().stream().map(EmployeePhone::getPhoneNumber).collect(
                Collectors.joining(" ")), employee.getNic().getId()));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}