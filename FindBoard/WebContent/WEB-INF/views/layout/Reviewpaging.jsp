<%@page import="util.Paging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	Paging paging = (Paging) request.getAttribute("paging"); %>

<div class="text-center">
	<ul class="pagination pagination-sm">

		<!-- 이전 페이지로 가기 -->
		<%	if(paging.getCurPage() != 1) { %>
		<li><a href="/review/list?curPage=<%=paging.getCurPage() - 1 %>">&lt;</a></li>
		<%	} %>
		
		<!-- 페이징 리스트 -->
		<%	for(int i=paging.getStartPage(); i<=paging.getEndPage(); i++) { %>
		<%		if( i == paging.getCurPage() ) { %>
		<li class="active"><a href="/review/list?curPage=<%=i %>"><%=i %></a></li>
		<%		} else { %>
		<li><a href="/review/list?curPage=<%=i %>"><%=i %></a></li>
		<%		} %>
		<%	} %>
		
		<!-- 다음 페이지로 가기 -->
		<%	if(paging.getCurPage() != paging.getTotalPage()) { %>
		<li><a href="/review/list?curPage=<%=paging.getCurPage() + 1 %>">&gt;</a></li>
		<%	} %>

		
	</ul>
</div>