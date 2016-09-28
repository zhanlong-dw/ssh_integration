package com.dw.ssh.service;

import java.util.List;

import com.dw.ssh.dao.DepartmentDao;
import com.dw.ssh.entities.Department;

public class DepartmentService {

	private DepartmentDao  departmentDao;
	
	public void setDepartmentDao(DepartmentDao departmentDao){
		this.departmentDao=departmentDao;
	}
	
	public List<Department> getAll(){
		return departmentDao.getAll();
	}
}
