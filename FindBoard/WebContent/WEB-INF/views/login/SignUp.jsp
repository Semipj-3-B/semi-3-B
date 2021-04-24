<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src ="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<link rel="stylesheet" type="text/css" href="/resources/css/signup.css"/>

<script type="text/javascript" src="/resources/js/signup.js"> </script>

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
	
    <label for="search_addr_btn">주소</label><br>
    <input type="text" class="un" name="postnum" class="postnum" id="uAddress_zoneCode" readonly="readonly" />
    <input type="button" value="검색" name="addr_btn" id="search_addr_btn" /><br> 
    <input type="text" class="dff" name="addr" id="uAddress_addr" readonly="readonly" /><br><br>
    <label for="uAddres_detail">상세 주소</label><br>
    <input type="text" class="df" name="addrDetail" id="uAddress_detail" /> <br><br>

	<label for="email">이메일</label> <br>
	<input type="text" name="email" id="email" placeholder="ex) abcd1234@naver.com"/>
	<input type="button" id="mailbtn" value="인증" disabled="disabled" /><br>
	<input type="text" name="atnum" id="atnum" placeholder="인증번호"/>
	<input type="button" id="atnbtn" value="확인" /> <br>
	<span id="emailMsg"></span> <span id="cor_num"></span> <span id="wro_num"></span> <br>
	
	<label for="tel">전화번호</label> <br>
	<input type="text" name="tel" id="tel" placeholder="' - ' 없이 입력해주세요"/> <br>
	<span id="telMsg"></span> <br>
	
	<br>
	
	<a href="/main"><input type="button" id="main" value="main"/></a>
	 <input type="submit" id="submit" value="sign up"/>

</form>

</body>
</html>