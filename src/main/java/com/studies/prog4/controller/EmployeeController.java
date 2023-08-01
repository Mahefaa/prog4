package com.studies.prog4.controller;

import com.studies.prog4.controller.view.mapper.EmployeeMapper;
import com.studies.prog4.controller.view.model.CrupdateEmployee;
import com.studies.prog4.controller.view.model.ViewEmployee;
import com.studies.prog4.model.Employee;
import com.studies.prog4.service.CompanyService;
import com.studies.prog4.service.EmployeePhoneService;
import com.studies.prog4.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static java.util.UUID.randomUUID;

@Controller
@RequestMapping("/employees")
public class EmployeeController extends AuthenticatedResourceController {
  public static final String EMPLOYEES = "employees";
  public static final String EMPLOYEE = "employee";
  public static final String CRUPDATE_EMPLOYEE = "crupdateEmployee";
  private final EmployeeService service;
  private final EmployeeMapper mapper;
  private final EmployeePhoneService phoneService;

  public EmployeeController(CompanyService companyService, EmployeeService service,
                            EmployeeMapper mapper, EmployeePhoneService phoneService) {
    super(companyService);
    this.service = service;
    this.mapper = mapper;
    this.phoneService = phoneService;
  }

  @GetMapping
  public String getEmployees(
      @RequestParam(value = "firstName", required = false, defaultValue = "") String firstName,
      @RequestParam(value = "lastName", required = false, defaultValue = "") String lastName,
      @RequestParam(value = "gender", required = false, defaultValue = "M") String sex,
      @RequestParam(value = "role", required = false, defaultValue = "") String role,
      @RequestParam(value = "hiringDateIntervalBegin", required = false)
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      LocalDate hiringDateIntervalBegin,
      @RequestParam(value = "hiringDateIntervalEnd", required = false)
      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate hiringDateIntervalEnd,
      @RequestParam(value = "departureDateIntervalBegin", required = false)
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      LocalDate departureDateIntervalBegin,
      @RequestParam(value = "departureDateIntervalEnd", required = false)
      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDateIntervalEnd,
      @RequestParam(value = "sortAttribute", required = false, defaultValue = "firstName")
      String sortAttribute,
      @RequestParam(value = "sortOrder", required = false, defaultValue = "ASC")
      Sort.Direction sortDirection,
      @RequestParam(value = "phoneCode", required = false)
      String phoneCode,
      Model model) {
    List<Employee> employees = service.getEmployeesByCriterias(
        firstName,
        lastName,
        role,
        sex,
        hiringDateIntervalBegin,
        hiringDateIntervalEnd,
        departureDateIntervalBegin,
        departureDateIntervalEnd,
        phoneCode,
        sortAttribute,
        sortDirection
    );
    List<ViewEmployee> viewEmployees = employees.stream().map(mapper::toView).toList();
    model.addAttribute(EMPLOYEES, viewEmployees);
    return "employees/index";
  }

  @GetMapping("/{id}/profile")
  public String getEmployee(@PathVariable UUID id, Model model) {
    Employee employee = service.getById(id);
    model.addAttribute(EMPLOYEE, mapper.toView(employee));
    return "employees/profile";
  }

  @GetMapping("/{id}/update")
  public String updateEmployee(@PathVariable UUID id, Model model) {
    Employee employee = service.getById(id);
    model.addAttribute(EMPLOYEE, mapper.toView(employee));
    return "employees/update";
  }

  @PutMapping("/{id}")
  public String updateEmployee(@PathVariable UUID id,
                               @ModelAttribute CrupdateEmployee crupdateEmployee) {
    service.saveEmployee(id, mapper.toDomain(crupdateEmployee));
    return "redirect:/employees";
  }

  @GetMapping("/form")
  public String form(Model model) {
    model.addAttribute(CRUPDATE_EMPLOYEE, new CrupdateEmployee());
    return "employees/form";
  }

  @PostMapping
  public String createEmployee(@ModelAttribute CrupdateEmployee crupdateEmployee) {
    Employee employee = service.saveEmployee(mapper.toDomain(crupdateEmployee));
    var phones = phoneService.saveAllForEmployee(employee, crupdateEmployee.getPhones());
    return "redirect:/employees";
  }

  @GetMapping("/toCsv")
  public void getCsv
      (
          @RequestParam(value = "firstName", required = false, defaultValue = "") String firstName,
          @RequestParam(value = "lastName", required = false, defaultValue = "") String lastName,
          @RequestParam(value = "gender", required = false, defaultValue = "M") String sex,
          @RequestParam(value = "role", required = false, defaultValue = "") String role,
          @RequestParam(value = "hiringDateIntervalBegin", required = false)
          @DateTimeFormat(pattern = "yyyy-MM-dd")
          LocalDate hiringDateIntervalBegin,
          @RequestParam(value = "hiringDateIntervalEnd", required = false)
          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate hiringDateIntervalEnd,
          @RequestParam(value = "departureDateIntervalBegin", required = false)
          @DateTimeFormat(pattern = "yyyy-MM-dd")
          LocalDate departureDateIntervalBegin,
          @RequestParam(value = "departureDateIntervalEnd", required = false)
          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDateIntervalEnd,
          @RequestParam(value = "sortAttribute", required = false, defaultValue = "firstName")
          String sortAttribute,
          @RequestParam(value = "sortOrder", required = false, defaultValue = "ASC")
          Sort.Direction sortDirection,
          @RequestParam(value = "phoneCode", required = false)
          String phoneCode,
          HttpServletResponse response) {
    List<Employee> employees = service.getEmployeesByCriterias(
        firstName,
        lastName,
        role,
        sex,
        hiringDateIntervalBegin,
        hiringDateIntervalEnd,
        departureDateIntervalBegin,
        departureDateIntervalEnd,
        phoneCode,
        sortAttribute,
        sortDirection
    );
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition",
        "attachment; filename\"list_employees_" + randomUUID() + ".csv\"");
    service.exportToCsv(employees, response);
  }
}