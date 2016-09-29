package com.dw.ssh.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.RequestAware;
import com.dw.ssh.entities.Employee;
import com.dw.ssh.service.DepartmentService;
import com.dw.ssh.service.EmployeeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
public class EmployeeAction extends ActionSupport implements RequestAware,
ModelDriven<Employee>,Preparable{

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
	
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
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
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "ajax-success";
	}
	
	
	public String input(){
		request.put("departments",departmentService.getAll());
		return INPUT;
	}
	
	public void prepareInput(){
		if(id!=null){
			model = employeeService.get(id);
		}
	}
	public String save(){
		if(id == null){
			model.setCreateTime(new Date());
		}
		employeeService.saveOrUpdate(model);
		return SUCCESS;
	}
	
	public void prepareSave(){
		if(id==null){
			model = new Employee();
		}else{
			model = employeeService.get(id);
		}
	}
	
	private String lastName;
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String validateLastName() throws UnsupportedEncodingException{
		if(employeeService.LastNameIsValid(lastName)){
			inputStream  = new ByteArrayInputStream("1".getBytes("UTF-8"));
		}else{
			inputStream  = new ByteArrayInputStream("0".getBytes("UTF-8"));
		}
		return "ajax-success";
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}

	@Override
	public void prepare() throws Exception {}

	private Employee model;
	
	@Override
	public Employee getModel() {
		return model;
	}
	
	
}
