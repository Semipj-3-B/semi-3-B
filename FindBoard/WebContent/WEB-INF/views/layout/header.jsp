<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유기동물 관리</title>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- 부트스트랩 3.2.2 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<style type="text/css">

#header {
   width: 1200px;
   margin: 0 auto;
}

#login {
   width: 1200px;
   height: 50px;
}

#login > button {
    float: right;  
   margin-top: 10px;
   margin-right: 10px;
   background-color: transparent !important;
}

.navbar {
   background-color: #EBC680;
}

.container-fluid > .navbar-header {
/*    margin-right: 30px; */
    margin-left: 10px;
}


/* 메뉴 가운데 정렬 */
.navbar .navbar-nav {
  display: inline-block;
  float: none;
  vertical-align: bottom;
}

.navbar .navbar-collapse {
  text-align: center;
}
/* 가운데 정렬 끝 */

.nav li a {
   color: brown;
   font-size: 17px;
}

.nav li a:hover, .nav li a:active, .nav li a:visited { 
   background-color: #A48654;
    color: brown;  
}

#loginNick {
   float: right;
   margin-top: 10px;
}

.submenu { 
   /* 하위 메뉴 스타일 설정 */ 
   position: absolute; 
   height: 0px; 
   overflow: hidden; 
   transition: height .2s; 
   -webkit-transition: height .2s; 
   -moz-transition: height .2s; 
   -o-transition: height .2s; 
   width: 200px; /* [변경] 가로 드랍다운 메뉴의 넓이 */ 
   background-color: #EBC680;
} 

.submenu li { 
   display: inline-block; /* 가로로 펼쳐지도록 설정  */
}

.submenu li a { 
/*    border: solid 1px #A48654;  */
   margin-right: -1px
} 

.submenu li a:hover {
   text-decoration: none;
   background-color: #A48654;
}

.topMenuLi:hover .submenu {
   height: 40px;
   text-align: center;
   font-size: 15px;
/*    padding: 5px; */
}




</style>

</head>
<body>

<div id="header">

<%   if(session.getAttribute("login") == null || !(boolean)session.getAttribute("login")) { %>
<!-- 비로그인상태 -->

   <div id="login">
      <button class="btn" onclick="location.href= '/login/login';">로그인</button>
      <button class="btn" onclick="location.href= '/login/signup';">회원가입</button>
   </div>

<%   } else if( (boolean)session.getAttribute("login") ) { %>
<!-- 로그인상태 -->

   <div id="login">
      <div id="loginNick">
         <%=session.getAttribute("nick") %>님 
         <button class="btn" onclick="location.href= '';">마이페이지</button> |
         <button class="btn" onclick="location.href= '/login/logout';">로그아웃</button> 
      </div>
   </div>

<%   } %>

<!-- <nav class="navbar navbar-default"> -->
<nav class="navbar">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">
         <img alt="Logo" src="">
      </a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active topMenuLi"><a href="/find/list">반려동물 찾기 <span class="sr-only">(current)</span></a></li>
        <li class="topMenuLi"><a href="/discover/list">유기동물 발견</a></li>
        <li class="topMenuLi"><a href="/product">스토어</a></li>
        <li class="topMenuLi"><a href="#">커뮤니티</a>
           <ul class="submenu nav">
              <li><a href="#">공지사항</a></li>
              <li>|</li>
              <li><a href="/review/list">후기</a></li>
           </ul>
        </li>
      </ul>

    </div>
    
  </div>
</nav>

</div>