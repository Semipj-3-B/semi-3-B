<%@page import="util.AdminPaging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="util.Paging"%>
<% AdminPaging apaging = (AdminPaging) request.getAttribute("apaging"); %>

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

<div class="page text-center" style="height: 40px;">
	<ul class ="pagination pagination-sm">
	
		<!-- 첫 페이지로 이동 ← -->
		<% if(apaging.getCurPage() != 1) { %>	<!-- 첫 페이지가 아닐 때 보인다 -->
		<li><a href="/admin">&larr;</a></li>
		<% } %>
		<!-- 조건에 만족하지 않으면 아예 안보임 -->
		
		
		
		<!-- 이전 페이징 리스트로 가기 << -->
		<% if(apaging.getStartPage() > apaging.getPageCount() ){ %>
		<li>
		<a href="/admin/user?curPage=<%=apaging.getStartPage() - apaging.getPageCount() %>"> &laquo; </a>
		</li>
		<% } else { %>
		<li class="disabled"><a>&laquo;</a></li>
		<% } %>
		<!-- 조건에 만족하지 않으면 금지 -->
		
		
		<!-- 이전 페이지로 가기 < -->
		<% if(apaging.getCurPage() != 1) { %>
		<li><a href="/admin/user?curPage=<%=apaging.getCurPage() - 1 %>"> &lt; </a></li>
		<% } %>
		<!-- 조건에 만족하지 않으면 아예 안보임 -->
		
		
		<!-- 페이징 리스트 -->
		<% for(int i=apaging.getStartPage(); i<=apaging.getEndPage(); i++ ) { %>
			<% if( i == apaging.getCurPage() ) { %>
		<li class="active"><a href="/admin/user?curPage=<%=i %>"><%=i %></a></li>
			<% }  else {%>
		<li><a href="/admin/user?curPage=<%=i %>"><%=i %></a></li>
			<% } %>
		<% } %>
		
		
		<!-- 다음 페이지로 가기 > -->		
		<% if(apaging.getCurPage() != apaging.getTotalPage() ) { %>
		<li><a href="/admin/user?curPage=<%=apaging.getCurPage() + 1%>">&gt;</a></li>
		<% } %>
		
		
		
		<!-- 다음 페이징 리스트로 가기 >> -->
		<% if( apaging.getEndPage() != apaging.getTotalPage() ) { %>
		<li><a href="/admin/user?curPage=<%=apaging.getStartPage() + apaging.getPageCount()%>">&raquo;</a></li>
		<% } else {%>
		<li class="disabled"><a>&raquo;</a></li>
		<% } %>
		
		
		
		<!-- 마지막 페이지로 가기 → -->
		<% if(apaging.getCurPage() != apaging.getTotalPage() ) { %>	<!-- 마지막 페이지가 아닐 때 보인다 -->
		<li><a href="/admin/user?curPage=<%=apaging.getTotalPage()%>">&rarr;</a></li>
		<% } %>
		<!-- 조건에 만족하지 않으면 아예 안보임 -->
		
	</ul>
</div>
