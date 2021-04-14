<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>메인페이지</h1>

<% if( session.getAttribute("login") == null ) { %>
	<a href="/login/login"><button type="button">login</button></a>
	<a href="/login/signup"><button type="button">signup</button></a>
<% } else { %>
	<%=session.getAttribute("nick") %>님 안녕하세요
	<a href="/login/logout"><button type="button">logout</button></a>

<% } %>

</body>
</html>