package com.studies.prog4.service;

import com.studies.prog4.controller.view.model.exception.NotFoundException;
import com.studies.prog4.model.Employee;
import com.studies.prog4.repository.EmployeeRepository;
import com.studies.prog4.repository.model.EmployeePhone;
import com.studies.prog4.repository.model.Phone;
import com.studies.prog4.repository.model.mapper.EmployeeEntityMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeService {
  public static final LocalDate DEFAULT_START_DATE = LocalDate.ofYearDay(1970, 1);
  public static final LocalDate DEFAULT_END_DATE = LocalDate.ofYearDay(2070, 365);

  private final EmployeeEntityMapper mapper;
  private final EmployeeRepository repository;

  public List<Employee> getEmployeesByCriterias(
      Integer page,
      Integer pageSize,
      String firstName,
      String lastName,
      String role,
      String sex,
      LocalDate hiringDateIntervalBegin,
      LocalDate hiringDateIntervalEnd,
      LocalDate departureDateIntervalBegin,
      LocalDate departureDateIntervalEnd,
      Integer phoneCode,
      String sortAttribute,
      Sort.Direction sortDirection) {
    Pageable pageable =
        PageRequest.of((page == null || page <= 0) ? 0 : page - 1,
            (pageSize == null || pageSize <= 0) ? 10 : pageSize,
            sortDirection, sortAttribute);
    return repository.findAllByCriterias(
            firstName,
            lastName,
            sex,
            role,
            phoneCode,
            hiringDateIntervalBegin != null ? hiringDateIntervalBegin : DEFAULT_START_DATE,
            hiringDateIntervalEnd != null ? hiringDateIntervalEnd : DEFAULT_END_DATE,
            departureDateIntervalBegin != null ?  departureDateIntervalBegin: DEFAULT_START_DATE,
            departureDateIntervalEnd != null ? departureDateIntervalEnd : DEFAULT_END_DATE,
            pageable
        )
        .stream()
        .map(mapper::toDomain)
        .toList();
  }

  public Employee getById(UUID id) {
    return mapper.toDomain(repository.findById(id)
        .orElseThrow(() -> new NotFoundException("Employee.id=" + id + " not found.")));
  }

  public Employee saveEmployee(Employee toSave) {
    return mapper.toDomain(repository.save(mapper.toEntity(toSave)));
  }

  public Employee saveEmployee(UUID id, Employee toSave) {
    Employee persisted = getById(id);
    return mapper.toDomain(
        repository.save(
            mapper.toEntity(
                persisted.toBuilder()
                    .firstName(toSave.getFirstName())
                    .lastName(toSave.getLastName())
                    .birthDate(toSave.getBirthDate())
                    .profilePicture(toSave.getProfilePicture())
                    .sex(toSave.getSex())
                    .csp(toSave.getCsp())
                    .address(toSave.getAddress())
                    .emailPro(toSave.getEmailPro())
                    .emailPerso(toSave.getEmailPerso())
                    .role(toSave.getRole())
                    .childNumber(toSave.getChildNumber())
                    .hiringDate(toSave.getHiringDate())
                    .departureDate(toSave.getDepartureDate())
                    .cnaps(toSave.getCnaps())
                    .phones(toSave.getPhones())
                    .nic(toSave.getNic()).build()
            )
        )
    );
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
            employee.getPhones().stream()
                .map(EmployeePhone::getPhone)
                .map(Phone::getPhoneNumber)
                .collect(Collectors.joining(" ")), employee.getNic().getId()));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}