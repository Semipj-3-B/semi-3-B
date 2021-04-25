<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<script type="text/javascript">
$(document).ready(function () {
	
	$("#write").click(function () {
		if(checkForm()) {
			$("form").submit()
		} else {
			return false
		}
	})
})

function checkForm() {
	//NOT NULL: 카테고리, 상품명, 가격,
	var flag = true
	var category = $("select").val()
	var prodName = $("#prodname").val()
	var price = $("#price").val()
	var repreimg = $("#inputimg1").val()
	
	if(category == 0) flag = false
	if(prodName == null || prodName == '') flag = false
	if(price == null || price == '') flag = false
	if(repreimg == null || repreimg == '') flag = false
	
	//내용, 첨부파일 모두 비어있을 때 alert
	var content = $("#content").val()
	var detimg = $("#inputimg2").val()
	
	if(content == null || content == '') {
		if(detimg == null || detimg == '') {
			flag = false
			alert("상세설명 입력 또는 파일을 첨부해주세요.")
			return flag
		}
	}
	
	console.log("flag:",flag)
	return flag
}
</script>

<style type="text/css">
div {font-size: 16px;}

#repre-img {
	width: 100%; 
	height: 300px;
	float:left;
	border: 1px solid;
}
input[type=text]:focus{outline: none;}
input[type=text] {
	border: none; 
	border-bottom: 1px solid;
	width: 250x;
}

span {
	display: inline-block;
	width: 150px;
	margin: 10px 0;
}

textarea {
	width: 870px;
	height: 195px;
	resize: none;
}

label {font-weight: 400;}

button {
	transition-duration: 0.4s;
	background-color: #EBAA5F;
	float:right;
	
}
button:hover{box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);}
</style>

<div class="container" style="width: 900px;">
	<form action="/product/write" method="post" enctype="multipart/form-data">
	<h3>스토어 상품 등록</h3>
	<hr>
	<div>
		<span>카테고리</span>
		<select id="sort" name="sort">
			<option value="0" selected="selected">분류</option>
			<option value="1">반려동물</option>
			<option value="2">악세서리</option>
			<option value="3">폰케이스</option>
		</select>
	</div>
	<div>
		<label><span>상품명</span>
		<input type="text" id="prodname" name="prodname"/></label>
	</div>

	<div>
		<label><span>가격</span>
		<input type="text" id="price" name="price"/></label>
	</div>

	<span><label for="content">상세설명</label></span>
	<textarea id="content" name="content"></textarea>
	
	<div>
		<span>대표이미지</span>
		<input type="file" id="inputimg1" name="inputimg1" accept="image/*"/>
		<p id="repreimg"></p>
	</div>

	<div>
		<span>상세이미지</span>
		<input type="file" id="inputimg2"  name="inputimg2" accept="image/*"/>
		<p id="detimg"></p>
	</div>
	<button type="button" class="btn" id="write">등록</button>
</form>
</div>

<script type="text/javascript">
$(document).ready(function () {
	
	var input1 = "#inputimg1"
	var input2 = "#inputimg2"
	
	var zone1 = "#repreimg"
	var zone2 = "#detimg"
	
	//대표이미지 업로드
	$(input1).change(function () {
		//파일명 filename에 저장
		var file = this.files[0]
		console.log("filename:", file)

		//파일이 null 또는 빈 문자열이 아닌경우 (= 업로드된 경우)
		if(file != null || file != "") {
			uploadImg(file, input1, zone1)
		} else {
			$(zone1).empty()
		}
	})
	
	
	//상세설명 이미지 업로드
	$(input2).change(function () {
		var file = this.files[0]
		console.log("filename:", file)

		if(file != null || file != "") {
			uploadImg(file, input2, zone2)
		} else {
			$(zone2).empty()
		}
	})
	
})

//파일명 길이 구하기
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

function uploadImg(file, input, imgzone) {
	$(imgzone).empty()
	console.log("uploadImg:", file)

	//파일명 추출
	var filename = file.name
	console.log("filename:", file.name)
	
	//확장자 추출
	var lastdot = filename.lastIndexOf('.')
	var ext = filename.substring(lastdot+1, filename.length)
	console.log("확장자 추출:", ext)
	
	//확장자 제거한 이름
	var imgname = filename.substring(0, lastdot)
	console.log("확장자 제거:", imgname)

	//확장자 유효 검사
	if($.inArray(ext,["jpg","jpeg","png"]) == -1) {
	        alert("jpg, jpeg, png 파일만 업로드 가능합니다.")
			$(input).val("")
	        return false
	}
	
	//파일명(이미지 이름) 길이 검사
	var byteLen = getByteLength(imgname)
	if(byteLen > 30) {
		alert('파일명은 한글 10자 또는 영문 30자 이하로 가능합니다.')
		$(input).val("")
        return false
	}
	
	//크기 검사
	var fileSize = file.size
	var sizeKb = fileSize / 1024
	var sizeMb = sizeKb / 1024
	var maxSize = 10 * 1024 * 1024
		
	//파일이 1KB 미만일 때
	if(fileSize < 1024) {
		alert('1KB 이상의 사진을 업로드 할 수 있습니다.')
		$(input).val("")
		return false
	}
		
	//파일의 크기가 10MB 초과일 때
	if(fileSize > maxSize) {
		alert('10MB까지 업로드 할 수 있습니다.')
		$(input).val("")
		return false
	}
		
	console.log("파일사이즈:", sizeKb.toFixed(2), "KB")
	
	//이미지 미리보기
	var reader = new FileReader()
	reader.onload = function(e) {
		console.log("result:", e.target.result)
		
		if(input == "#inputimg1") {
			$("<img>").attr({
				"src" : e.target.result
				, "width" : "350px"
				, "height" : "300px"
			}).appendTo($(imgzone))	
		} else {
			$("<img>").attr({
				"src" : e.target.result
				, "width" : "80%"
				, "height" : "80%"
			}).appendTo($(imgzone))	
		}
	}
	reader.readAsDataURL(file)
		
}
</script>
</body>
</html>