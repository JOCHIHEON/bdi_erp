<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<%
List<Map<String,String>> userList = 
(List<Map<String,String>>) request.getAttribute("userList");
%>
<body>
<div class="container">
	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>아이디</th>
				<th>이메일</th>
			</tr>
		</thead>
		<tbody>
<%
for(Map<String,String> user:userList){
%>	
			<tr>
				<td><%=user.get("uiNum") %></td>
				<td><%=user.get("uiName") %></td>
				<td><%=user.get("uiId") %></td>
				<td><%=user.get("uiEmail") %></td>
			</tr>
<%
}
%>	
		</tbody>
	</table>
</div>
</body>
</html>