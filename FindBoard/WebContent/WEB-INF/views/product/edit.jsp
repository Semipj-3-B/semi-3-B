<%@page import="dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<% Product p = (Product) request.getAttribute("product"); %>

상품ID: <%= p.getProductId() %>
</body>
</html>