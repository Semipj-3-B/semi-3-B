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

	$("#upfile").bind('change', function () {
		if(this.files.length < 4) {
			checkFiles(this.files)
		} else {
			alert('사진은 최대 4개 업로드할 수 있습니다.')
			this.files.value = 0
			this.files.name = ""
		}
	})
	
	$("#write").click(function () {
		submitContents( $("#write"))
		$("form").submit();
	})
	
})

//등록할 전체 파일의크기
var totalFileSize = 0;	

//파일, 파일크기 List
var fileList = new Array()
var fileSizeList = new Array()

var maxUploadSize = 10 * 1024 * 1024	//등록 가능한 총 파일 크기 10MB
var maxNumOfFiles = 4					//등록 가능한 파일 총 개수

function checkFiles(fileObject) {
	var files = null
	
	if(fileObject != null) {
		files = fileObject
	}

	if(files != null) {
		for(var i = 0; i < maxNumOfFiles; i++) {
			//파일+확장자
			var fileFullName = files[i].name
			console.log("파일.확장자: " + fileFullName)
			
			//이름만 추출
			var lastDot = fileFullName.lastIndexOf('.')
			var fileName = fileFullName.substring(0, lastDot)
			console.log("파일이름: " + fileName)
			
			//확장자 추출
			var ext = fileFullName.substring(lastDot + 1, fileFullName.length).toLowerCase()
			console.log("확장자: " + ext)
		
			//파일 byte 단위 크기
			var fileSize = files[i].size
			console.log("FileSize: " + fileSize)
			
			if(fileSize <= 0) {
				console.log("0KB file return")
				return
			}
			
			var fileSizeKb = fileSize / 1024	//KB단위 파일 크기
			var fileSizeMb = fileSizeKb / 1024	//MB단위 파일 크기
			
			if(fileSize >= (1024 * 1024)) {	//파일이 1MB 이상일 때
				console.log("FileSizeMB: " + fileSizeMb.toFixed(2) + "MB")
			}
			if(fileSize >= 1024) {	//파일이 1KB 이상일 때
				console.log("FileSizeKB: " + fileSizeKb.toFixed(2) + "KB")
			}
			console.log("FileSize: " + parseInt(fileSize) + "byte")
		
			//확장자 유효 검사
			if($.inArray(ext, ['png', 'jpg', 'jpeg']) < 0) {//이미지 형식 아닐 때
				console.log("이미지는 .png .jpg .jpeg만 등록 가능합니다.")
				return
			}
			
			//파일 크기 검사
			if(fileSizeMb > maxUploadSize) {
				alert("최대 10MB까지 업로드 가능")
				return
			}
			
			totalFileSize += fileSizeMb
			fileList[i] = files[i]
			fileSizeList[i] = fileSizeMb
		}
	}
}
	
	/* var formData = new FormData($("#upload")[0]);
	console.log(formData)
	
	$.ajax({ 
		type: "POST"
		, enctype: 'multipart/form-data'
		, url: '/file/add'
		, data: formData
		, processData: false
		, contentType: false
		, cache: false
		, dataType: 'json'
		, success: function (result) { cosole.log("성공") }
		, error: function (e) { console.log("실패")} }); */

</script>
<style type="text/css">
.container {
	width: 1200px;
	margin: 0 auto;
}

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

ul li { list-style: none; }


.pet-info .test1 .test2 {
    overflow: hidden;
    position: relative;
}

img.img-lg {
	border: 1px solid black;
	width: 300px;
	height: 300px;
	margin: 5px 0 0 8%;
	position: absolute
}

img.img-sm {
	border: 1px solid black;
	width: 150px;
	height: 100px;
	margin: 0 5px 5px 370px ;
}

input.pat {
	font-size: 14px;
	width: 270px;
}

textarea {
	width: 1000px;
	margin: 0 0 0 5%;
	height: 400px;
}

</style>

<div class="container">
<form action="/find/add" method="post" enctype="multipart/form-data" id="upload">

<div id="title"><input id="title" name="title" /></div>

<div class="pet-info">
	<img class="img-lg">
<ul class="test1">
	<li class="test2">
	<img class="img-sm">
		<input class="pat" type="text" id="petname" name="petname" placeholder="반려동물 이름"/>
		<label><input type="radio" value="dog" name="petkinds" />강아지</label>
		<label><input type="radio" value="cat" name="petkinds" />고양이</label>
		<label><input type="radio" value="etc" name="petkinds" />기타 동물</label>
	</li>
	<li>
	<img class="img-sm">
		<input class="pat" type="text" id="petage" name="petage" placeholder="반려동물 나이"/>
		<select name="loc">
			<option value="0"  selected = "selected">지역</option>
			<option value="1">서울</option>
			<option value="2">경기</option>
		</select>
			<input type="text" id="detail-loc" name="detail-loc" placeholder="상세주소 입력"/>
	</li>
	<li>
	<img class="img-sm">
		<input type="text" id="detail-loc" name="detail-loc" placeholder="연락받을 이메일"/>
		<input type="file" id="upfile" name="upfile" multiple="multiple"/>
	</li>
</ul>
</div>

<div style="padding: 0 0 0 40px;">
	<textarea id="content" name="content">본문</textarea>
</div>

<div>
	<button id="write">작성</button>
	<button id="cancle" onclick="history.go(-1)">취소</button>
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