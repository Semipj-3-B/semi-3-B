<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript"
src = "http://code.jquery.com/jquery-2.2.4.min.js"></script>


<script type="text/javascript">

$(document).ready(function() {
	
	
	var uidReg = /^[A-Za-z0-9]{8,}$/
	var upwReg = /^[A-Za-z0-9]{8,}$/
	var emailReg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
	var telReg = /^\d{3}\d{3,4}\d{4}$/;
	var idFlag = "";
	var pwFlag = "";
	var ckFlag = "";
	var emFlag = "";
	var numFlag = "";
	var telFlag = "";
	var idchFlag = "";
	var nickchFlag = "";
	
		//#id 검증
		$("#id").blur(function() {
			if( !uidReg.test( $("#id").val())) {
				$("#idMsg_cor").html("");
				$("#idMsg").html("8자 이상 영문/숫자로 입력");
				$("#idbtn").attr('disabled', true);
				idFlag = "false";
			} else {
				$("#idMsg").html("");
				idFlag = "true";
				$("#idbtn").attr('disabled', false);
			}
		})
		
		//#pw 검증
		$("#pw").blur(function() {
		
			if(!upwReg.test($("#pw").val())) {
				$("#pwMsg").html("8자 이상 영문/숫자로 입력");
				pwFlag = "false";
			} else {
				$("#pwMsg").html("");
				pwFlag = "true";
			}
			
			if($("#pw").val() == $("#cpw").val()) {
				$("#cpwMsg").html("");
			}
		})
		
		//#cpw 검증
		$("#cpw").blur(function() {
			if($("#pw").val() != $("#cpw").val()) {
				$("#cpwMsg").html("비밀번호 불일치");
				ckFlag = "false";
			} else {
				$("#cpwMsg").html("");
				ckFlag = "true";
			}
		})
		
		//#email 검증
		$("#email").blur(function() {
			if(!emailReg.test($("#email").val())) {
				$("#emailMsg").html("이메일 양식이 맞지 않습니다")
				emFlag = "false";
			} else {
				$("#emailMsg").html("");
				emFlag = "true";
				$("#mailbtn").attr('disabled', false);
				$("#email").attr('readonly', true);
			}
		})
		
		//#tel 검증
		$("#tel").blur(function() {
			if(!telReg.test($("#tel").val())) {
				$("#telMsg").html("숫자만 입력가능 합니다")
				telFlag = "false";
			} else {
				$("#telMsg").html("");
				telFlag = "true";
			}
		})		
	
		//입력 검증
		$("#submit").click(function() {
			if($("#id").val() == "" || idFlag == "false") {
				alert('ID를 확인해주세요');
				return false;		
			}
			
			if(idchFlag == "false") {
				alert('ID 중복검사를 해주세요');
				return false;		
			}
			
			if(pwFlag == "false" || $("#pw").val() == "") {
				alert('PW를 확인해주세요');
				return false;
			}
			
			if(ckFlag == "false" || $("#cpw").val() == "") {
				alert('비밀번호가 일치하지않습니다.');
				return false; 
			}
			
			if($("#nick").val() == "") {
				alert('닉네임을 입력해 주세요');
				return false;
			}
			
			if(nickchFlag == "false") {
				alert('닉네임 중복검사를 해주세요');
				return false;		
			}
	
			if($("#year").val() == "" || $("#month").val() == "" || $("#day").val() == "") {
				alert('생년월일을 입력해 주세요');
				return false;
			}
			
			if($("#name").val() == "") {
				alert('이름을 입력해 주세요');
				return false;
			}
			
			if(emFlag == "false" || $("#email").val() == "") {
				alert('이메일을 확인해 주세요');
				return false;
			}
			
			if(numFlag == "false" || $("#atnum").val() == "") {
				alert('인증번호를 확인해 주세요');
				return false;
			}
			
			if(telFlag == "false" || $("#tel").val() == "") {
				alert('전화번호를 입력해 주세요');
				return false;
			}

		})
		
		$("#mailbtn").click(function() {
		var email = $("#email").val();
			
				$.ajax({
					type : 'get',
					url : '/login/email',
					data : {email:email},
					success : function(data) {
						alert("인증메일이 발송되었습니다.");
						
						$("#atnbtn").click(function() {
							if($("#atnum").val() != data) {
								$("#cor_num").html("");
								$("#wro_num").html("인증번호가 일치하지 않습니다")
								numFlag = "false";
							} else if($("#atnum").val() == data) {
								$("#wro_num").html("")
								$("#cor_num").html("인증번호가 일치합니다");
								numFlag = "true";
							}
						})
					} //success end
						
					}) //ajax end
					
		}); //$("#mailbtn").click end
			
		$("#idbtn").click(function() {
			var id = $("#id").val();
			
				$.ajax({
					type: 'post',
					url: '/login/idcheck',
					data: {id:id},
					success : function(data) {
						if(data > 0) {
							$("#idMsg_cor").html("");
							$("#idMsg").html("중복된 아이디 입니다.");
							idchFlag = false;
						} else {
							$("#idMsg").html("");
							$("#idMsg_cor").html("사용가능한 아이디 입니다.");
							idchFlag = true;
						}
					}
				})
		}) //$("#idbtn").click end
		
		$("#nickbtn").click(function() {
			var nick = $("#nick").val();
			
				$.ajax({
					type: 'post',
					url: '/login/nickcheck',
					data: {nick:nick},
					success : function(data) {
						if(data > 0) {
							$("#nickMsg_cor").html("");
							$("#nickMsg").html("중복된 닉네임 입니다.");
							idchFlag = false;
						} else {
							$("#nickMsg").html("");
							$("#nickMsg_cor").html("사용가능한 닉네임 입니다.");
							idchFlag = true;
						}
					}
				})
		}) //$("#nickbtn").click end
		
	
}) //$(document).ready end
</script>

<style type="text/css">

body {
	margin: 0px;
	padding: 0px;
	font-family: sans-serif;
}

.box {
	
	margin: 0 auto;
	padding: 40px;
	text-align: center;
}

.box h1, .box label {
	color : #EA9A56;
	text-transform: uppercase;
	font-weight: 500px;
}

.box input[type = "text"], .box input[type = "password"] {
	border: 0px;
	background: none;
	margin: 20px auto;
	text-align: center;
	border : 2px solid #FFC091;
	padding: 5px 5px;
	width: 200px;
	outline: none;
	color: #000;
	border-radius: 24px;
	transition: 0.25s;
}

.box input[type = "number"], .box select {
	border: 0px;
	background: none;
	margin: 20px auto;
	text-align: center;
	border : 2px solid #FFC091;
	padding: 5px 5px;
	width: 50px;
	outline: none;
	color: #000;
	border-radius: 24px;
	transition: 0.25s;
}

.box input[type = "text"]:focus, .box input[type = "password"]:focus, .box select:focus {
	width: 280px;
	border-color: A48654;
	
}	

.box input[type = "submit"] {
	border : 0;
	background : none;
	margin: 20px auto;
	text-align: center;
	border : 2px solid #A48654;
	padding: 5px 5px;
	outline: none;
	color: #000;
	border-radius: 24px;
	transition: 0.25s;
	cursor: pointer;
}

 .box input[type = "button"] {
 	border : 0;
	background : none;
	margin: 20px auto;
	text-align: center;
	border : 2px solid #cccc13;
	padding: 5px 5px;
	outline: none;
	color: #000;
	border-radius: 24px;
	transition: 0.25s;
	cursor: pointer;
 } 
 
 .box input[type = "submit"]:hover {
	background: #A48654;
}

 .box input[type = "button"]:hover {
 	background: #cccc13;
 }
 
span {
	color : red;
}

#cor_num, #idMsg_cor, #nickMsg_cor {
	color : blue;
}

.birth {
	width: 30px; 
}


</style>

</head>
<body>

<form action="/login/signup" method="post" class="box">

<h1>Sign Up</h1> <br><br>

<label for="id">아이디</label><br>
<input type="text" name="id" id="id" />
<input type="button" id="idbtn" value="중복확인" disabled="disabled"/><br>
<span id="idMsg"></span><span id="idMsg_cor"></span><br><br>

<label for="pw">비밀번호</label> <br>
<input type="password" name="pw" id="pw" /> <br>
<span id="pwMsg"></span> <br><br>

<label for="cpw">비밀번호 확인</label> <br>
<input type="password" name="cpw" id="cpw" /> <br>
<span id="cpwMsg"></span> <br><br>

<label for="nick">닉네임</label> <br>
<input type="text" name="nick" id="nick" />
<input type="button" id="nickbtn" value="중복확인" /> <br>
<span id="nickMsg"></span><span id="nickMsg_cor"></span> <br><br>

<label for="year">생년월일</label><br>
<input type="number" name="year" id="year" class="birth" /><label>년</label>

<select name="month" id="month" class="birth">
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	<option value="7">7</option>
	<option value="8">8</option>
	<option value="9">9</option>
	<option value="10">10</option>
	<option value="11">11</option>
	<option value="12">12</option>
</select><label>월</label>

<input type="number" name="day" id="day" class="birth"/><label>일</label> <br>
<span id="birthMsg"></span> <br>

<label for="name">이름</label> <br>
<input type="text" name="name" id="name" /> <br><br>
<label>남</label> <input type="radio" name="gender" id="gender_m" value="m" checked="checked" />
<label>여</label> <input type="radio" name="gender" id="gender_f" value="f"/> <br>
<span id="namegenMsg"></span> <br><br>

<label for="email">이메일</label> <br>
<input type="text" name="email" id="email" />
<input type="button" id="mailbtn" value="전송" disabled="disabled" /> <br>
<input type="text" name="atnum" id="atnum" />
<input type="button" id="atnbtn" value="확인" /> <br>
<span id="emailMsg"></span> <span id="cor_num"></span> <span id="wro_num"></span> <br><br>

<label for="tel">전화번호</label> <br>
<input type="text" name="tel" id="tel" /> <br>
<span id="telMsg"></span> <br>

<input type="button" id="cancle" value="cancle" onclick='history.go(-1)' />
 <input type="submit" id="submit" value="sign up"/>

</form>

</body>
</html>