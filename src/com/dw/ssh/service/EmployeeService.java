package com.dw.ssh.service;

import java.util.List;

import com.dw.ssh.dao.EmployeeDao;
import com.dw.ssh.entities.Employee;

public class EmployeeService {

	private EmployeeDao employeeDao;
	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	public void delete(Integer id){
		employeeDao.delete(id);
	}
	
	public List<Employee> getAll(){
		return employeeDao.getAll();
		
	}
}
