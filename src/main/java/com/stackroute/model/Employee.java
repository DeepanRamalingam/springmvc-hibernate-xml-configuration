package com.stackroute.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	private int id;
	
	@Column
	private String fname;
	
	@Column
	private String company;
	
	@Column
	private String city;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Employee(int id, String fname, String company, String city) {

		this.id = id;
		this.fname = fname;
		this.company = company;
		this.city = city;
	}

	public Employee() {
	
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", fname=" + fname + ", company=" + company + ", city=" + city + "]";
	}
	
	
}
