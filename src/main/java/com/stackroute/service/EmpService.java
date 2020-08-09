package com.stackroute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.dao.EmpDao;
import com.stackroute.model.Employee;

@Service
public class EmpService {

	@Autowired
	private EmpDao empdao;
	
	@Transactional
	public List<Employee> getAllEmp(){
		return empdao.getAllEmp();
	}
	
	@Transactional
	public Employee getByID(){
		return empdao.getByID();
	}
}
