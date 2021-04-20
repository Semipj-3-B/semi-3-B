<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<%@page import="util.Paging"%>
<% Paging paging = (Paging) request.getAttribute("paging"); %>

<div class="text-center">
	<ul class ="pagination">
	
		<!-- 첫 페이지로 이동 -->
		<% if(paging.getCurPage() != 1) { %>	<!-- 첫 페이지가 아닐 때 보인다 -->
		<li><a href="/find/list">&larr;</a></li>
		<% } %>
		<!-- 조건에 만족하지 않으면 아예 안보임 -->
		
		
		
		<!-- 이전 페이징 리스트로 가기 -->
		<% if(paging.getStartPage() > paging.getPageCount() ){ %>
		<li>
		<a href="/find/list?curPage=<%=paging.getStartPage() - paging.getPageCount() %>"> &laquo; </a>
		</li>
		<% } else { %>
		<li class="disabled"><a>&laquo;</a></li>
		<% } %>
		<!-- 조건에 만족하지 않으면 금지 -->
		
		
		<!-- 이전 페이지로 가기 -->
		<% if(paging.getCurPage() != 1) { %>
		<li><a href="/find/list?curPage=<%=paging.getCurPage() - 1 %>"> &lt; </a></li>
		<% } %>
		<!-- 조건에 만족하지 않으면 아예 안보임 -->
		
		
		<!-- 페이징 리스트 -->
		<% for(int i=paging.getStartPage(); i<=paging.getEndPage(); i++ ) { %>
			<% if( i == paging.getCurPage() ) { %>
		<li class="active"><a href="/find/list?curPage=<%=i %>"><%=i %></a></li>
			<% }  else {%>
		<li><a href="/find/list?curPage=<%=i %>"><%=i %></a></li>
			<% } %>
		<% } %>
		
		
		<!-- 다음 페이지로 가기 -->		
		<% if(paging.getCurPage() != paging.getTotalPage() ) { %>
		<li><a href="/find/list?curPage=<%=paging.getCurPage() + 1%>">&gt;</a></li>
		<% } %>
		
		
		
		<!-- 다음 페이징 리스트로 가기 -->
		<% if( paging.getEndPage() != paging.getTotalPage() ) { %>
		<li><a href="/find/list?curPage=<%=paging.getStartPage() + paging.getPageCount()%>">&raquo;</a></li>
		<% } else {%>
		<li class="disabled"><a>&raquo;</a></li>
		<% } %>
		
		
		
		<!-- 마지막 페이지로 가기 -->
		<% if(paging.getCurPage() != paging.getTotalPage() ) { %>	<!-- 마지막 페이지가 아닐 때 보인다 -->
		<li><a href="/find/list?curPage=<%=paging.getTotalPage()%>">&rarr;</a></li>
		<% } %>
		<!-- 조건에 만족하지 않으면 아예 안보임 -->
		
	</ul>
</div>
