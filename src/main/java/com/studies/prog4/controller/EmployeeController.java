package com.studies.prog4.controller;

import com.studies.prog4.controller.view.mapper.EmployeeMapper;
import com.studies.prog4.controller.view.model.CreateEmployee;
import com.studies.prog4.controller.view.model.ViewEmployee;
import com.studies.prog4.model.Employee;
import com.studies.prog4.service.EmployeeService;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
  public static final String EMPLOYEES = "employees";
  public static final String EMPLOYEE = "employee";
  public static final String CREATE_EMPLOYEE = "createEmployee";
  private final EmployeeService service;
  private final EmployeeMapper mapper;

  @GetMapping
  public String getEmployees(Model model) {
    List<Employee> employees = service.getEmployees();
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

  @GetMapping("/form")
  public String form(Model model) {
    model.addAttribute(CREATE_EMPLOYEE, new CreateEmployee());
    return "employees/form";
  }

  @PostMapping
  public String createEmployee(@ModelAttribute CreateEmployee createEmployee) {
    service.saveEmployee(List.of(mapper.toDomain(createEmployee)));
    return "redirect:/employees";
  }
}