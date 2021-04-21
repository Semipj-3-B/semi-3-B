<%@page import="dto.Usertb"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% List<Usertb> userList = (List<Usertb>) request.getAttribute("userList"); %>

<%@ include file="/WEB-INF/views/admin/common.jsp" %>

	<table class="table">
	<tr>
		<th style="width: 15%">아이디</th>
		<th style="width: 15%">닉네임</th>
		<th style="width: 15%">탈퇴요청</th>
		<th style="width: 15%">DB삭제</th>
		<th style="width: 20%">이메일전송</th>
	</tr>
	<% for(int i = 0; i < userList.size(); i++) { %>
	<tr>
		<td><%= userList.get(i).getUserId() %></td>
		<td><%= userList.get(i).getNick() %></td>
		<td>-</td>
		<td>삭제</td>
		<td><%= userList.get(i).getEmail() %></td>
	</tr>
	<% } %>
	</table>
	<%@ include file="/WEB-INF/views/admin/paging.jsp" %>
	</div>
</div>