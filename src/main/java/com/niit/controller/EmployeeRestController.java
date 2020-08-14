package com.niit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.EmployeeDao;
import com.niit.model.Employee;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private Employee emp1;

	@GetMapping("/employee/hr")
	public ResponseEntity<?> sayHello() {
		List<Employee> employee = employeeDao.findAll();

		return new ResponseEntity<List<Employee>>(employee, HttpStatus.OK);

	}

	@PostMapping("/employee/add")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
		return new ResponseEntity(employeeDao.addEmployee(employee), HttpStatus.OK);

	}

	@PutMapping("/employee/hr")
	public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee) {

		employee.setEmpId(employee.getEmpId());
		employeeDao.updateEmployee(employee);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Employee> login(@RequestBody Employee emp) {
		boolean flag = employeeDao.employeeAuthentication(emp);

		if (flag) {

			emp.setErrorCode("200");

			emp.setMessage(" Enter Valid Credentials");
			System.out.println("in if rest controller" + emp.getErrorCode() + emp.getMessage());
		} else {

			System.out.println("not run");
			emp.setMessage(" Enter Valid Credentials");
		}
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}

	@PostMapping("/employee/empSession")
	public ResponseEntity<Employee> empSession(@RequestBody Employee emp) {

		// emp.setPassword(emp.getPassword());
		int flag = employeeDao.updateEmpSkills(emp);

		if (flag == 1) {

			emp.setErrorCode("200");

			emp.setMessage(" Enter Valid Credentials");
			System.out.println("in if rest controller" + emp.getErrorCode() + emp.getMessage());
		} else {
			// emp=new Employee();
			System.out.println("not run");
			emp.setMessage(" Enter Valid Credentials");
		}
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}

	@PostMapping("/employee/hr/delete")
	public ResponseEntity<Employee> deleteEmp(@RequestBody Employee emp) {

		boolean flag = employeeDao.deleteEmployee(emp);

		if (flag) {

			emp.setErrorCode("200");

			emp.setMessage("  Valid Credentials");
			System.out.println("in if rest controller" + emp.getErrorCode() + emp.getMessage());
		} else {

			System.out.println("not run");
			emp.setMessage(" Enter Valid Credentials");
		}
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}

	@PostMapping("/employee/rating")
	public ResponseEntity<Employee> employeeRating(@RequestBody Employee emp) {
		boolean flag = employeeDao.rating(emp);

		if (flag) {

			emp.setErrorCode("200");

			emp.setMessage(" Enter Valid Credentials");
			System.out.println("in if rest controller" + emp.getErrorCode() + emp.getMessage());
		} else {

			System.out.println("not run");
			emp.setMessage(" Enter Valid Credentials");
		}
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}

	@GetMapping("/employee/hr/employeer")
	public ResponseEntity<?> listApprovedEmployee() {
		List<Employee> employee = employeeDao.getApprovedEmp();

		return new ResponseEntity<List<Employee>>(employee, HttpStatus.OK);

	}
}