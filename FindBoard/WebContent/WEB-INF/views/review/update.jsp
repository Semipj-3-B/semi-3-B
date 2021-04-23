<%@page import="dto.ReviewBoard"%>
<%@page import="dto.ReviewImgFile"%>
<%@page import="java.util.List"%>
<%@page import="dto.ReviewDetailView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% ReviewDetailView view = (ReviewDetailView) request.getAttribute("reviewDetail"); %>
<% List<ReviewImgFile> imgs = (List<ReviewImgFile>) request.getAttribute("reviewImgs"); %>
<% ReviewBoard reviewNo = (ReviewBoard) request.getAttribute("reviewNo"); %>
    
<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<!-- <form>태그의 submit을 수행하면 editor에 작성한 내용을 <textarea>에 반영 -->
<script type="text/javascript">
function submitContents( elClickedObj ) {
	
	//에디터의 내용을 #content에 반영한다
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	try {
		// <form>태그의 submit 수행
		elClickedObj.form.submit();
	} catch(e) {}
	
}
</script>

<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼
	$("#btnWrite").click(function() {
		submitContents($("#btnWrite"))
		$("form").submit();
	});
	
	//취소버튼
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
	const input = document.querySelector("#upfile")
	//사진 첨부 버튼 클릭
	$(".browse").click(function() {
		input.click()
	});
	
	$("#upfile").on('change', uploadImg)
	
});

function uploadImg(e) {
	
	changeimg = 1;
	
	$("img").replaceWith("<img>");
	
	var files = null
	if(e.target.files != null) {
		files = e.target.files
	}
	
	//파일 개수 검사
	if(files.length > 4 ) {
		alert('최대 4장까지 업로드 할 수 있습니다.')
		e.target.value = null /* 파일 업로드 초기화 */
		return false
	}
	
	for(var i = 0; i < files.length; i++) {
		//확장자 검사
		if(!files[i].type.match("image/jpeg")) {
			alert('jpg 또는 jpeg 확장자만 업로드 가능합니다.')
			e.target.value = null
			return false;
		}
		
		//크기 검사
		var fileSize = files[i].size
		var sizeKb = fileSize / 1024
		var sizeMb = sizeKb / 1024
		var totalMaxSize = 10 * 1024 * 1024
		var totalFileSize = 0
		
		//파일이 1KB 미만일 때
		if(fileSize < 1024) {
			alert('1KB 이상의 사진을 업로드 할 수 있습니다.')
			return false;
		}
		
		//파일이 10MB 이하일 때
		if(fileSize < totalMaxSize) {
			totalFileSize += fileSize
		}
		
		//모든 파일 합의 크기가 10MB 초과일 때
		if(totalFileSize > totalMaxSize) {
			alert('10MB까지 업로드 할 수 있습니다.')
			return false;
		}
		
		//이미지 미리보기
		var reader = new FileReader()
		switch(i) {
			case 0:
				reader.onload = function(ev){
					$("#img1").children().attr({
						"src" : ev.target.result
						, "width" : "250px"
						, "height" : "200px"
					})
					//이미지 영역 초기화
// 					$("#mainimg").empty()	
// 					$("#subimg1").empty()	
// 					$("#subimg2").empty()	
// 					$("#subimg3").empty()	
				}
				break
			case 1:
				reader.onload = function(ev){
					$("#img2").children().attr({
						"src" : ev.target.result
						, "width" : "250px"
						, "height" : "200px"
					})
					//이미지 영역 초기화
// 					$("#mainimg").empty()	
// 					$("#subimg1").empty()	
// 					$("#subimg2").empty()	
// 					$("#subimg3").empty()	
				}
				break
			case 2:
				reader.onload = function(ev){
					$("#img3").children().attr({
						"src" : ev.target.result
						, "width" : "250px"
						, "height" : "200px"
					})
					//이미지 영역 초기화
// 					$("#mainimg").empty()	
// 					$("#subimg1").empty()	
// 					$("#subimg2").empty()	
// 					$("#subimg3").empty()	
				}
				break
			case 3:
				reader.onload = function(ev){
					$("#img4").children().attr({
						"src" : ev.target.result
						, "width" : "250px"
						, "height" : "200px"
					})
				}
				break
			default: return false
			
			} //switch END
			
		reader.readAsDataURL(files[i])
	}
}
</script>

<style type="text/css">

.table {
	width: 1100px;
}

#content {
/* 	width: 100%; */
	width: 98%;
}

#imgs {
	width: 240px;
	height: 200px;
	float: left;
	margin: 5px;
/*  	border: 1px solid #EBC680;  */
}


</style>


<!-- 스마트 에디터 2 -->
<script type="text/javascript"
	src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<div class="container">
<h3 style="text-align: center">후기 게시판 수정</h3>
<hr>

<div>
<form action="/review/update" method="post" enctype="multipart/form-data">

<input type="hidden" name="reviewNo" value="<%=request.getParameter("reviewNo") %>">

<table class="table table-bordered">

<tr>
	<td>분류</td><td colspan="3"><%=view.getReviewSortDetail() %></td>
</tr>

<tr>
	<td>제목</td>
	<td colspan="6"><input type="text" name="title" style="width:90%" value="<%=view.getTitle() %>"/></td>
</tr>

<tr>
	<td>작성자</td><td colspan="2"><%=view.getNick() %></td>
	<td>작성일</td><td colspan="2"><%=view.getCreateDate() %></td>
</tr>

<tr>
	<td colspan="4">내용</td>
</tr>
<tr>
	<td colspan="8"><textarea class="form-control" 
			style="width: 95%; height: 300px;" name="content" id="content"><%=view.getContent() %></textarea></td>
</tr>

</table>

<% int i = imgs.size(); %>	
	<% if(i <= 0) { %>
<!-- 		<tr> -->
<!-- 			<!-- 이미지 띄워줄 자리 -->
<!-- 			<td colspan="2"><img src=".." alt="img1" id="imgs" /></td> -->
<!-- 			<td colspan="2"><img src=".." alt="img2" id="imgs" /></td> -->
<!-- 			<td colspan="2"><img src=".." alt="img3" id="imgs" /></td> -->
<!-- 			<td colspan="2"><img src=".." alt="img4" id="imgs"></td> -->
<!-- 		</tr> -->
			<div class="img_box">
				<div id="img1" class="img" style="float: left;"><img src=".." alt="img1" id="imgs"/></div>
				<div id="img2" class="img" style="float: left;"><img src=".." alt="img2" id="imgs"/></div>
				<div id="img3" class="img" style="float: left;"><img src=".." alt="img3" id="imgs"/></div>
				<div id="img4" class="img" style="float: left;"><img src=".." alt="img4" id="imgs"/></div>
				<div style="clear: both;"></div>
			</div>
	<% } else if(i < 2 && i > 0) { %>
<!-- 		<tr> -->
<!-- 			<!-- 이미지 띄워줄 자리 --> 
<%-- 			<td colspan="2"><img src="/reviewImgFile/<%=imgs.get(0).getStoredImg() %>" alt="img1" id="imgs" /></td> --%>
<!-- 			<td colspan="2"><img src=".." alt="img2" id="imgs" /></td> -->
<!-- 			<td colspan="2"><img src=".." alt="img3" id="imgs" /></td> -->
<!-- 			<td colspan="2"><img src=".." alt="img4" id="imgs"></td> -->
<!-- 		</tr> -->
			<div class="img_box">
				<div id="img1" class="img" style="float: left;"><img src="/reviewImgFile/<%=imgs.get(0).getStoredImg() %>" alt="img1" id="imgs"/></div>
				<div id="img2" class="img" style="float: left;"><img src=".." alt="img2" id="imgs"/></div>
				<div id="img3" class="img" style="float: left;"><img src=".." alt="img3" id="imgs"/></div>
				<div id="img4" class="img" style="float: left;"><img src=".." alt="img4" id="imgs"/></div>
				<div style="clear: both;"></div>
			</div>
	<% } else if(i < 3 && i > 1)  {%>	
<!-- 		<tr> -->
<!-- 			<!-- 이미지 띄워줄 자리 --> 
<%-- 			<td colspan="2"><img src="/reviewImgFile/<%=imgs.get(0).getStoredImg() %>" alt="img1" id="imgs" /></td> --%>
<%-- 			<td colspan="2"><img src="/reviewImgFile/<%=imgs.get(1).getStoredImg() %>" alt="img2" id="imgs" /></td> --%>
<!-- 			<td colspan="2"><img src=".." alt="img3" id="imgs" /></td> -->
<!-- 			<td colspan="2"><img src=".." alt="img4" id="imgs"></td> -->
<!-- 		</tr> -->
			<div class="img_box">
				<div id="img1" class="img" style="float: left;"><img src="/reviewImgFile/<%=imgs.get(0).getStoredImg() %>" alt="img1" id="imgs"/></div>
				<div id="img2" class="img" style="float: left;"><img src="/reviewImgFile/<%=imgs.get(1).getStoredImg() %>" alt="img2" id="imgs"/></div>
				<div id="img3" class="img" style="float: left;"><img src=".." alt="img3" id="imgs"/></div>
				<div id="img4" class="img" style="float: left;"><img src=".." alt="img4" id="imgs"/></div>
				<div style="clear: both;"></div>
			</div>
	<% } else if(i < 4 && i > 2) { %>
<!-- 		<tr> -->
<!-- 			<!-- 이미지 띄워줄 자리 -->
<%-- 			<td colspan="2"><img src="/reviewImgFile/<%=imgs.get(0).getStoredImg() %>" alt="img1" id="imgs" /></td> --%>
<%-- 			<td colspan="2"><img src="/reviewImgFile/<%=imgs.get(1).getStoredImg() %>" alt="img2" id="imgs" /></td> --%>
<%-- 			<td colspan="2"><img src="/reviewImgFile/<%=imgs.get(2).getStoredImg() %>" alt="img3" id="imgs" /></td> --%>
<!-- 			<td colspan="2"><img src=".." alt="img4" id="imgs"></td> -->
<!-- 		</tr> -->
			<div class="img_box">
				<div id="img1" class="img" style="float: left;"><img src="/reviewImgFile/<%=imgs.get(0).getStoredImg() %>" alt="img1" id="imgs"/></div>
				<div id="img2" class="img" style="float: left;"><img src="/reviewImgFile/<%=imgs.get(1).getStoredImg() %>" alt="img2" id="imgs"/></div>
				<div id="img3" class="img" style="float: left;"><img src="/reviewImgFile/<%=imgs.get(2).getStoredImg() %>" alt="img3" id="imgs"/></div>
				<div id="img4" class="img" style="float: left;"><img src=".." alt="img4" id="imgs"/></div>
				<div style="clear: both;"></div>
			</div>
	<% } else if(i < 5 && i > 3) { %>
<!-- 		<tr> -->
<!-- 			<!-- 이미지 띄워줄 자리 --> 
<%-- 			<td colspan="2"><img src="/reviewImgFile/<%=imgs.get(0).getStoredImg() %>" alt="img1" id="imgs" /></td> --%>
<%-- 			<td colspan="2"><img src="/reviewImgFile/<%=imgs.get(1).getStoredImg() %>" alt="img2" id="imgs" /></td> --%>
<%-- 			<td colspan="2"><img src="/reviewImgFile/<%=imgs.get(2).getStoredImg() %>" alt="img3" id="imgs" /></td> --%>
<%-- 			<td colspan="2"><img src="/reviewImgFile/<%=imgs.get(3).getStoredImg() %>" alt="img4" id="imgs"></td> --%>
<!-- 		</tr> -->
			<div class="img_box">
				<div id="img1" class="img" style="float: left;"><img src="/reviewImgFile/<%=imgs.get(0).getStoredImg() %>" alt="img1" id="imgs"/></div>
				<div id="img2" class="img" style="float: left;"><img src="/reviewImgFile/<%=imgs.get(1).getStoredImg() %>" alt="img2" id="imgs"/></div>
				<div id="img3" class="img" style="float: left;"><img src="/reviewImgFile/<%=imgs.get(2).getStoredImg() %>" alt="img3" id="imgs"/></div>
				<div id="img4" class="img" style="float: left;"><img src="/reviewImgFile/<%=imgs.get(3).getStoredImg() %>" alt="img4" id="imgs"/></div>
				<div style="clear: both;"></div>
			</div>
	<% } %>

<input type="file" name="upfile" id="upfile" multiple="multiple"
	accept="image/jpeg, image/jpg" style="display: none;" /><br>
<button class="browse btn" type="button">사진 첨부</button>


</form>
</div>
<br>

<div class="text-center">	
	<button type="button" id="btnCancel" class="btn">취소</button>
	<button type="button" id="btnWrite" class="btn">작성</button>
</div>

</div>

<br><br>
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content", //에디터가 적용될 <textarea>의 id를 입력
	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
})
</script>

</body>
</html>