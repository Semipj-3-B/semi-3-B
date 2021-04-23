<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<!-- 스마트 에디터 2 -->
<script type="text/javascript"
	src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>


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

<%! int index = 0; %>

<script type="text/javascript">
$(document).ready(function() {
	
<%-- 	<% session.removeAttribute("reviewSort"); %> --%>
	
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

function getByteLength(name) {
	var b = 0	//byte
	var c = 0	//char
	
	for(j = 0; j < name.length; j++) {
		c = name.charCodeAt(j)
		if(c >> 11) c = 3
		else if(c >> 7) c = 2
		else c = 1
		b+=c	
	}
	return b
}

function uploadImg(e) {
	//이미지 영역 초기화
	$("#img1").empty()
	$("#img2").empty()
	$("#img3").empty()
	$("#img4").empty()
	
	var files = null
	if(e.target.files != null) {
		files = e.target.files
	}
	
	//파일 개수 검사
	if(files.length > 4 ) {
		alert('최대 4장까지 업로드 할 수 있습니다.')
		return false
	}
	
	for(var i = 0; i < files.length; i++) {
		//확장자 검사
		if(!files[i].type.match("image/jpeg")) {
			alert('jpg 또는 jpeg 확장자만 업로드 가능합니다.')
			return false;
		}
		
		//확장자 제거한 파일명
		var lastDot = files[i].name.lastIndexOf('.')
		var fileName = files[i].name.substring(0, lastDot)
		
		//파일명 길이 검사	
		var byteLen = getByteLength(fileName)
		if(byteLen > 30) {
			alert('파일명은 한글 10자 또는 영문 30자 이하로 가능합니다.')
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
					$("<img>").attr({
						"src" : ev.target.result
						, "width" : "250px"
						, "height" : "200px"
					}).appendTo($("#img1"))
				}
				break;
			case 1:
				reader.onload = function(ev){
					$("<img>").attr({
						"src" : ev.target.result
						, "width" : "250px"
						, "height" : "200px"
					}).appendTo($("#img2"))
				}
				break;
			case 2:
				reader.onload = function(ev){
					$("<img>").attr({
						"src" : ev.target.result
						, "width" : "250px"
						, "height" : "200px"
					}).appendTo($("#img3"))
				}
				break;
			case 3:
				reader.onload = function(ev){
					$("<img>").attr({
						"src" : ev.target.result
						, "width" : "147px"
						, "height" : "97px"
					}).appendTo($("#img4"))
				}
				break;
			default: return false;
			
			} //switch END
			
		reader.readAsDataURL(files[i])
		
	} //for() END
}


</script>

<style type="text/css">
#content {
/* 	width: 100%; */
	width: 98%;
}

table {
	width: 1100px;


.img { 
 	border: 1px solid #EBC680; 
} 

</style>

<div class="container">

<h3 style="text-align: center;">후기 작성</h3>
<hr>

<div>
<form action="/review/write" method="post" enctype="multipart/form-data">

<table>
<tr>
	<td class="info">분류</td>
	<td>
		<select name="review" id="select">
			<option value="1" selected="selected">스토어 후기</option>
			<option value="2">유기동물 찾아준 후기</option>
			<option value="3">반려동물 찾은 후기</option>
		</select>
	</td>
</tr>
<tr>
	<td class="info">제목</td>
	<td colspan="3"><input type="text" name="title" style="width:90%"/></td>
</tr>
<tr>
	<td class="info"  colspan="4">내용</td>
</tr>
<tr>
	<td colspan="4"><textarea class="form-control" 
			style="width: 95%; height: 300px;" name="content" id="content"></textarea></td>
</tr>
</table>

<div class="img_box">
	<div id="img1" class="img" style="float: left;"></div>
	<div id="img2" class="img" style="float: left;"></div>
	<div id="img3" class="img" style="float: left;"></div>
	<div id="img4" class="img" style="float: left;"></div>
	<div style="clear: both;"></div>
</div>
<br>
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
	elPlaceHolder: "content", // 에디터가 적용될 <textarea>의 id를 입력
	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
})
</script>

</body>
</html>