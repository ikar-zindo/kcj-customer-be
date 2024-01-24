package com.kcurryjibcustomer.repo;

import com.kcurryjibcustomer.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
