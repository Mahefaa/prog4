package com.studies.prog4.service;

import com.studies.prog4.model.Employee;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.studies.prog4.controller.EmployeeController.EMPLOYEES;

@Service
@AllArgsConstructor
public class EmployeeService {
  public List<Employee> getEmployees(HttpSession session) {
    Object sessionEmployees = session.getAttribute(EMPLOYEES);
    if (!(sessionEmployees instanceof List<?>)) {
      return List.of();
    }
    return (List<Employee>) sessionEmployees;
  }

  public void saveEmployee(HttpSession session, List<Employee> toSave) {
    List<Employee> actual = getEmployees(session);
    actual.addAll(toSave);
    session.setAttribute(EMPLOYEES, actual);
  }
}