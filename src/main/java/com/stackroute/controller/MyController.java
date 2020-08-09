package com.stackroute.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.stackroute.model.Employee;
import com.stackroute.service.EmpService;

@Controller
public class MyController {

	@Autowired
	private EmpService empService;
	

	@GetMapping(value = "/hellotohome")
	public String helloWorld(ModelMap map) {
		
		List<Employee> emps;
		
		emps = empService.getAllEmp();
		
		for(Employee emp: emps) {
			System.out.println(emp);
		}
		
//		System.out.println(empService.getByID());
		return "home";
	}
}
