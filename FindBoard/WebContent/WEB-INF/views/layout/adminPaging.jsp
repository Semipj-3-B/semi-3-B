<%@page import="util.AdminPaging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="util.Paging"%>
<% AdminPaging paging = (AdminPaging) request.getAttribute("apaging"); %>

<style type="text/css">
.pagination>.active>a,
.pagination>.active>a:focus,
.pagination>.active>a:hover,
.pagination>.active>span,
.pagination>.active>span:focus,
.pagination>.active>span:hover {
	background-color: #EA9A56;
	border-color: #EA9A56;
}

.pagination>li>a {color: #EA9A56;}
</style>

<div class="text-center" >
	<ul class ="pagination pagination-sm">
	
		<!-- 이전 페이지로 가기 -->
		<%	if(paging.getCurPage() != 1) { %>
		<li><a href="<%= request.getContextPath()%>?curPage=<%=paging.getCurPage() - 1 %>">&lt;</a></li>
		<%	} %>
		
		<!-- 페이징 리스트 -->
		<%	for(int i=paging.getStartPage(); i<=paging.getEndPage(); i++) { %>
		<%		if( i == paging.getCurPage() ) { %>
		<li class="active"><a href="<%= request.getContextPath()%>?curPage=<%=i %>"><%=i %></a></li>
		<%		} else { %>
		<li><a href="<%= request.getContextPath()%>?curPage=<%=i %>"><%=i %></a></li>
		<%		} %>
		<%	} %>
		
		<!-- 다음 페이지로 가기 -->
		<%	if(paging.getCurPage() != paging.getTotalPage()) { %>
		<li><a href="<%= request.getContextPath()%>?curPage=<%=paging.getCurPage() + 1 %>">&gt;</a></li>
		<%	} %>
		
	</ul>
</div>
