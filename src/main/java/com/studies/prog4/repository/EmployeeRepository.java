package com.studies.prog4.repository;

import com.studies.prog4.repository.model.Employee;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
  @Query("select e from Employee e " +
      "where (e.firstName is null or upper(e.firstName) like  concat('%', upper( ?1),'%')) "
      + "and (e.lastName is null or upper(e.lastName) like concat('%', upper(?2), '%')) "
      + "and (e.sex is null or cast(e.sex as string) = ?3 ) "
      + "and( e.role is null or upper(e.role) like concat('%',upper(?4),'%') )"
      + "and (e.id in (select a.employee.id from e.phones a where a.phone.code is null or a.phone.code = ?5))"
      + "and( e.hiringDate between ?6 and ?7 )"
      + "and (e.departureDate between ?8 and ?9)")
  List<Employee> findAllByCriterias(
      String firstName,
      String lastName,
      String sex,
      String role,
      Integer phoneCode,
      LocalDate hiringDateIntervalBegin,
      LocalDate hiringDateIntervalEnd,
      LocalDate departureDateIntervalBegin,
      LocalDate departureDateIntervalEnd,
      Pageable pageable
  );
}
