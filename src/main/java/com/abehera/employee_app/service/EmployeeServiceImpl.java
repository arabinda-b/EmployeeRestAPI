package com.abehera.employee_app.service;

import com.abehera.employee_app.data.models.Employee;
import com.abehera.employee_app.data.payloads.request.EmployeeRequest;
import com.abehera.employee_app.data.payloads.response.MessageResponse;
import com.abehera.employee_app.data.repository.EmployeeRepository;
import com.abehera.employee_app.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public MessageResponse createEmployee(EmployeeRequest employeeRequest) {
		Employee newEmployee = new Employee();
		newEmployee.setFirstName(employeeRequest.getFirstName());
		newEmployee.setLastName(employeeRequest.getLastName());
		newEmployee.setPhoneNumber(employeeRequest.getPhoneNumber());
		newEmployee.setEmail(employeeRequest.getEmail());
		newEmployee.setSalary(employeeRequest.getSalary());
		newEmployee.setDepartment(employeeRequest.getDepartment());
		employeeRepository.save(newEmployee);
		return new MessageResponse("New employee created successfully.");
	}
	
	@Override
	public Optional<Employee> updateEmployee(Integer employeeId, EmployeeRequest employeeRequest) 
			throws ResourceNotFoundException {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()){
        	throw new ResourceNotFoundException("Employee", "id", employeeId);
        }
		else {
			employee.get().setFirstName(employeeRequest.getFirstName());
			employee.get().setLastName(employeeRequest.getLastName());
			employee.get().setPhoneNumber(employeeRequest.getPhoneNumber());
			employee.get().setEmail(employeeRequest.getEmail());
			employee.get().setSalary(employeeRequest.getSalary());
			employee.get().setDepartment(employeeRequest.getDepartment());
			employeeRepository.save(employee.get());
			return employee;
		}
	}
	
	@Override
	public Employee getASingleEmployee(Integer employeeId)
			throws ResourceNotFoundException {
		return employeeRepository.findById(employeeId).
				orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employeeId));
	}
	
	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}
	
	@Override
	public void deleteEmployee(Integer employeeId)
			throws ResourceNotFoundException {
		if (employeeRepository.getById(employeeId).getId().equals(employeeId)) {
			employeeRepository.deleteById(employeeId);
		}
		else {
			throw new ResourceNotFoundException("Employee", "Id", employeeId);
		}
	}
	
	
}
