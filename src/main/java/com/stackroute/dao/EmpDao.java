package com.stackroute.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.stackroute.model.Employee;

@Repository
@EnableTransactionManagement
public class EmpDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Employee> getAllEmp() {
		return sessionFactory.getCurrentSession().createQuery("from Employee").list();
	}

	public Employee getByID() {
		return sessionFactory.getCurrentSession().get(Employee.class, 100);
	}
}
