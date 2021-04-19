<%@page import="dto.Usertb"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<Usertb> userList = (List<Usertb>) request.getAttribute("userList"); %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<style type="text/css">
table {width: 900px;}
th, td { text-align:center;}
</style>

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
