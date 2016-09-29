<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jQuery1.9.1.js"></script>
<script type="text/javascript">
	$(function(){
		//1、点击delete时，弹出确定要删除xx的信息吗？不确定则取消
		$(".delete").click(function(){
			var lastName = $(this).next(":hidden").val();			
			var flag = confirm("确定要删除"+lastName+"的信息吗？");
			if(flag){
				//删除，使用ajax
				var $tr = $(this).parent().parent();
				var url = this.href;
				var args = {"time":new Date()};
				$.post(url,args,function(data){
					//若data的返回值为1，则提示删除成功，且把当前行删除
					if(data=="1"){
						alert("删除成功！");
						$tr.remove();
					}else{
						//若data的返回值不是1 ，提示删除失败
						alert("删除失败！");
					}
				});
			}
			
			//取消超链接的默认行为
			return false;
			
		});
	})
</script>
</head>
<body>
	<h4>Employee List Page</h4>
	<s:if test="#request.employees == null || #request.employees.size()== 0">
		没有任何员工信息
	</s:if>
	<s:else>
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<td>ID</td>
				<td>LASTNAME</td>
				<td>EMAIL</td>
				<td>BIRTH</td>
				<td>CREATETIME</td>
				<td>DEPT</td>
				<td>DELETE</td>
				<td>EDIT</td>
			</tr>
			<s:iterator value="#request.employees">
				<tr>
				<td>${id}</td>
				<td>${lastName}</td>
				<td>${email}</td>
				<td>
					<s:date name="birth" format="yyyy-MM-dd"/>
				</td>
				<td>
					<s:date name="birth" format="yyyy-MM-dd hh:mm:ss"/>
				</td>
				<td>${department.departmentName}</td>
				<td><a href="emp-delete?id=${id}" class="delete">Delete</a>
					<input type="hidden" value="${lastName}"/>
				</td>
				<td><a href="emp-input?id=${id}">Edit</a>
				</td>
			</tr>
			</s:iterator>
		</table>
	</s:else>
</body>
</html>