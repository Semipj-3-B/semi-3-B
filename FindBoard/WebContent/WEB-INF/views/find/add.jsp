<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<!-- 네이버 스마트 에디터2 -->
<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
function submitContents(elClickedObj) {
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", [])
	
	try {
		elClickedObj.form.submit();
	} catch(e) {}
}
</script>
<script type="text/javascript">
$(document).ready(function () {

	const upBtn = document.querySelector('.browse')
	const realupInput = document.querySelector('#upfile')

	upBtn.addEventListener('click', function () {
		realupInput.click()
	})

	$("#upfile").on('change', uploadImg)
	
	$("#write").click(function () {
		submitContents( $("#write"))
		$("form").submit();
	})
	
})

function uploadImg(e) {
	//이미지 영역 초기화
	$("#mainimg").empty()	
	$("#subimg1").empty()	
	$("#subimg2").empty()	
	$("#subimg3").empty()	
	
	var files = null
	if(e.target.files != null) {
		files = e.target.files
	}
	
	//파일 개수 검사
	if(files.length > 4 ) {
		alert('최대 4장까지 업로드 할 수 있습니다.')
		e.target.value = null	/* 파일 업로드 초기화 */
		return false
	}
	
	for(var i = 0; i < files.length; i++) {
		//확장자 검사
		if(!files[i].type.match("image/jpeg")) {
			alert('jpg 또는 jpeg 확장자만 업로드 가능합니다.')
			e.target.value = null
			return false
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
			return false
		}
		
		//파일이 10MB 이하일 때
		if(fileSize < totalMaxSize) {
			totalFileSize += fileSize
		}
		
		//모든 파일 합의 크기가 10MB 초과일 때
		if(totalFileSize > totalMaxSize) {
			alert('10MB까지 업로드 할 수 있습니다.')
			return false
		}
		
		//이미지 미리보기
		var reader = new FileReader()
		switch(i) {
			case 0:
				reader.onload = function(ev){
					$("#mainimg").attr({
						"src" : ev.target.result
						, "width" : "300px"
						, "height" : "300px"
					})
				}
				break
			case 1:
				reader.onload = function(ev){
					$("#subimg1").attr({
						"src" : ev.target.result
						, "width" : "150px"
						, "height" : "150px"
					})
				}
				break
			case 2:
				reader.onload = function(ev){
					$("#subimg2").attr({
						"src" : ev.target.result
						, "width" : "150px"
						, "height" : "150px"
					})
				}
				break
			case 3:
				reader.onload = function(ev){
					$("#subimg3").attr({
						"src" : ev.target.result
						, "width" : "150px"
						, "height" : "150px"
					})
				}
				break
			default: return false
			
			} //switch END
			
		reader.readAsDataURL(files[i])
		
	} //for() END
	
} //uploadImg() END

	
var formData = new FormData($("#upload")[0]);
console.log(formData)
	
/* $.ajax({ 
	type: "POST"
	, enctype: 'multipart/form-data'
	, url: '/file/add'
	, data: fileList
	, processData: false
	, contentType: false
	, cache: false
	, dataType: 'json'
	, success: function (result) { cosole.log("성공") }
	, error: function (e) { console.log("실패")} 
}); */

</script>
<style type="text/css">
div#title {
	padding: 0 292.5px;
}


input#title { 
	/* border: none; */ 
	width: 585px;
	font-size: 20px;
	text-align: center;
	margin: 0 0 10px 0;
}

#mainimg{
		width: 500px;
		height: 300px;
		float: left;
}

table table tr td img{
	border:1px solid;
	width: 100px;
	height: 100px;
	float: left;
	margin: 5px 5px 5px 5px;
}

#findinfo{
	width: 450px;
	float: right;
	margin: 2%;
}
#findinfo1{
	border:1px solid;
	margin: 10px 3px 10px 3px;
	padding: 2%;
	text-align: center;
}

input.pat {
	font-size: 14px;
	width: 270px;
}

textarea {
	width: 1000px;
	height: 400px;
}

</style>

<div class="container">
<form action="/find/add" method="post" enctype="multipart/form-data" id="upload">

<div id="title"><input id="title" name="title" /></div>

<div id="findinfo">		
	<div id="findinfo1">
		<label for="patname">반려동물이름</label>
		<input class="pat" type="text" id="petname" name="petname"/>
	</div>
	<div id="findinfo1">
		<input type="radio" value="dog" name="petkinds" />강아지
		<input type="radio" value="cat" name="petkinds" />고양이
		<input type="radio" value="etc" name="petkinds" />기타 동물
	</div>
	<div id="findinfo1">
	<label for="petage">반려동물나이</label>
	<input class="pat" type="text" id="petage" name="petage"/></div>
	<div id="findinfo1">
		<label for="detail-loc">잃어버린 곳</label>
		<select name="loc">
			<option value="0"  selected = "selected">지역</option>
			<option value="1">서울특별시</option>
			<option value="2">경기도</option>
			<option value="3">강원도</option>
			<option value="4">충청북도</option>
			<option value="5">충청남도</option>
			<option value="6">경상북도</option>
			<option value="7">경상남도</option>
			<option value="8">전라북도</option>
			<option value="9">전라남도</option>
			<option value="10">대전광역시</option>
			<option value="11">광주광역시</option>
			<option value="12">인천광역시</option>
			<option value="13">부산광역시</option>
			<option value="14">대구광역시</option>
			<option value="15">울산광역시</option>
			<option value="16">세종시</option>
			<option value="17">제주시</option>
		</select>
		<input type="text" id="detail-loc" name="detail-loc" placeholder="상세주소 입력"/></div>
	<div id="findinfo1">
		연락 받을 이메일 <%= session.getAttribute("email") %>
	</div>
</div>


<table >
	<tr>
		<td style="border: 1px solid;">
			<img alt="main"  id="mainimg"/>
		</td>
		<td>
			<table>
				<tr>
					<td><img alt="sub1" id="subimg1"/></td>
				</tr>
				<tr>
					<td><img alt="sub2" id="subimg2"/></td>
				</tr>
				<tr>
					<td><img alt="sub3" id="subimg3"/></td>
				</tr>
			</table>
		</td>
</table>
<input type="file" id="upfile" name="upfile" multiple="multiple" accept="image/jpeg" style="display:none;"/><br>
<button class="btn browse" type="button">사진 업로드</button>
<div>
	<textarea id="content" name="content">본문</textarea>
</div>

<div style="width: 120px; margin: 5px 45%">
	<button id="write" class="btn btn-info">작성</button>
	<button id="cancle" class="btn btn-danger" onclick="history.go(-1)">취소</button>
</div>
</form>
</div><!-- div.container end -->

<script type="text/javascript">
var oEditors = []
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content",
	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
})
</script>
</body>
</html>