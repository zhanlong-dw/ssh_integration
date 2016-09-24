package com.dw.ssh.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.dw.ssh.service.EmployeeService;
import com.opensymphony.xwork2.ActionSupport;
public class EmployeeAction extends ActionSupport implements RequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EmployeeService employeeService;
	private Map<String,Object> request;
	private Integer id;
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public String list(){
		request.put("employees", employeeService.getAll());
		return "list";
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	private InputStream inputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}
	
	public String delete(){
		try {
			employeeService.delete(id);
			inputStream = inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				inputStream = inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "delete";
	}
	
	
	

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
		
	}
	
	
}
