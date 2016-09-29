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
		$(":input[name=lastName]").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			var $this = $(this);
			
			if(val != ""){
				//把当前节点后面的所有font 兄弟节点删除
				$this.nextAll("font").remove();
				
				var url = "emp-validateLastName";
				var args = {"lastName":val,"time":new Date()};
				$.post(url,args,function(data){
					if(data=="1"){ //表示可用
						$this.after("<font color='green'>LastName可用</font>");
					}else if(data=="0"){ //表示不可用
						$this.after("<font color='red'>LastName不可用</font>");
					}else{  //服务器错误
						alert("服务器错误");
					}
				});
			}else{
				alert("lastName 不能为空");
				$(this).val("");
			}
		})
	})
</script>
</head>
<body>
	<h4>Employee Input Page</h4>
	
	<s:form action="emp-save" method="post">
		<s:if test="id!=null">
			<s:textfield name="lastName" label="LastName" disabled="true"></s:textfield>
			<s:hidden name="id"></s:hidden>
			
			<%--通过添加隐藏域的方法把未显示提交的字段值提交到服务器 
				<s:hidden name="lastName"></s:hidden>
				<s:hidden name="createTime"></s:hidden> 
			--%>
			
		</s:if>
		<s:else>
			<s:textfield name="lastName" label="LastName"></s:textfield>
		</s:else>
		<s:textfield name="email" label="Email"></s:textfield>
		<s:textfield name="birth" label="Birth"></s:textfield>

		<s:select list="#request.departments" listKey="id"
		 listValue="departmentName" name="department.id"
		 label="Department">
		</s:select>
		<s:submit></s:submit>
	</s:form>
</body>
</html>