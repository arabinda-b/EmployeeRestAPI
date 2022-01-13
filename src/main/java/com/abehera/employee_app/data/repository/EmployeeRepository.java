package com.abehera.employee_app.data.repository;

import com.abehera.employee_app.data.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
