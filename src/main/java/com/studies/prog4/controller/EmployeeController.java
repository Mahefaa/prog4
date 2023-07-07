package com.studies.prog4.controller;

import com.studies.prog4.model.Employee;
import com.studies.prog4.service.EmployeeService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
  public static final String EMPLOYEES = "employees";
  private final EmployeeService service;

  @GetMapping
  public String employeeList(Model model) {
    List<Employee> employees = service.getEmployees();
    model.addAttribute(EMPLOYEES, employees);
    return "employees/index";
  }

  @GetMapping("/form")
  public String form(Model model) {
    model.addAttribute("employee", new Employee());
    return "employees/form";
  }

  @PostMapping
  public String createEmployee(@ModelAttribute Employee employee) {
    service.saveEmployee(List.of(employee));
    return "redirect:/employees";
  }
}