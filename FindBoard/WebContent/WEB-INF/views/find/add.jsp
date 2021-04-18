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
		submitContents($("#write"))
		$("form").submit();
	})
	
})


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
		return false
	}
	
	for(var i = 0; i < files.length; i++) {
		//확장자 검사
		if(!files[i].type.match("image/jpeg")) {
			alert('jpg 또는 jpeg 확장자만 업로드 가능합니다.')
			return false
		}
		
		//확장자 제거한 파일명
		var lastDot = files[i].name.lastIndexOf('.')
		var fileName = files[i].name.substring(0, lastDot)
		
		//파일명 길이 검사	
		var byteLen = getByteLength(fileName)
		if(byteLen > 30) {
			alert('파일명은 한글 10자 이하로 가능합니다.')
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
					$("<img>").attr({
						"src" : ev.target.result
						, "width" : "400px"
						, "height" : "310px"
					}).appendTo($("#mainimg"))
				}
				break
			case 1:
				reader.onload = function(ev){
					$("<img>").attr({
						"src" : ev.target.result
						, "width" : "100px"
						, "height" : "100px"
					}).appendTo($("#subimg1"))
				}
				break
			case 2:
				reader.onload = function(ev){
					$("<img>").attr({
						"src" : ev.target.result
						, "width" : "100px"
						, "height" : "100px"
					}).appendTo($("#subimg2"))
				}
				break
			case 3:
				reader.onload = function(ev){
					$("<img>").attr({
						"src" : ev.target.result
						, "width" : "150px"
						, "height" : "150px"
					}).appendTo($("#subimg3"))
				}
				break
			default: return false
			
			} //switch END
			
		reader.readAsDataURL(files[i])
		
	} //for() END
	
} //uploadImg() END

</script>
<style type="text/css">
input#title:focus{outline: none;}

.mainimg{
	position: absolute;
	width: 400px;
	height: 310px;
	border: 1px solid;
}

.subimg-grid {
	display: inline-grid;
	grid-template-rows: 100px 100px 100px;
	grid-row-gap: 3px;
	margin: 0 0 0 405px;
	border: 1px solid;
	width: 150px;
	height: 309px;
}

.petinfo-grid {
	display: grid;
	grid-template-rows: 50px 40px 50px 50px 50px;
	margin: 0 5% 0 0;
	/* border: 1px solid; */
	width: 450px;
	height: 240px;
	float: right;
	align-items: center;
	font-size: 14px;
	text-align: center;
	padding: 40px 0;
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
<form action="/find/add" method="post" enctype="multipart/form-data">
	<h1><input id="title" name="title" placeholder="제목입력" style="border: none;" /></h1>
	<hr>
	
	<!-- img -->
	<div id="mainimg" class="mainimg"></div>
	<div class="subimg-grid">
		<div id="subimg1"></div>
		<div id="subimg2"></div>
		<div id="subimg3"></div>
	</div>
	
	<div class="petinfo-grid">
		<div>
			<label for="patname">반려동물이름</label>
			<input class="pat" type="text" id="petname" name="petname" />
		</div>
		<div style="text-align: center;">
			<input type="radio" value="dog" name="petkinds" />강아지
			<input type="radio" value="cat" name="petkinds" />고양이
			<input type="radio" value="etc" name="petkinds" />기타 동물
		</div>
		<div>
			<label for="petage">반려동물나이</label>
			<input class="pat" type="text" id="petage" name="petage"/>
		</div>
		<div>
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
			<input type="text" id="detail-loc" name="detail-loc" placeholder="상세주소 입력"/>
		</div>
		<div>이메일 <%= session.getAttribute("email") %></div>
	</div>
	
	<input type="file" id="upfile" name="upfile" multiple="multiple" accept="image/jpeg" style="display:none;"/><br>
	<button class="btn browse" type="button">사진 업로드</button>
	
	<div><textarea id="content" name="content"></textarea></div>

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