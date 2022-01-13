package com.abehera.employee_app.web;

import com.abehera.employee_app.data.models.Employee;
import com.abehera.employee_app.data.payloads.request.EmployeeRequest;
import com.abehera.employee_app.data.payloads.response.MessageResponse;
import com.abehera.employee_app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployee();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) {
		Employee employee = employeeService.getASingleEmployee(id);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	@PostMapping("/add")
	public ResponseEntity<MessageResponse> addEmployee(@RequestBody EmployeeRequest employee) {
		MessageResponse newEmployee = employeeService.createEmployee(employee);
		return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Optional<Employee>> updateEmployee(@PathVariable("id") Integer id, @RequestBody EmployeeRequest employee) {
		Optional<Employee> updateEmployee = employeeService.updateEmployee(id, employee);
		return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
