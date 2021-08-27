<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%-- <c:if test="${userinfo == null}">
	<c:redirect url="/map"/>
</c:if>
<c:if test="${userinfo != null}"> --%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>SSAFY-글작성</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  function writeArticle() {
	if(document.getElementById("subject").value == "") {
		alert("제목 입력!!!!");
		return;
	} else if(document.getElementById("content").value == "") {
		alert("내용 입력!!!!");
		return;
	} else {
	  	document.getElementById("writeform").action = "${root}/map";
	  	document.getElementById("writeform").submit();
	}
  }

  </script>
</head>
<body>

<div class="container" align="center">
<header id="index_header" class="jumbotron text-center mb-1">
			<h4>행복한 우리 집</h4> 
		</header>
		<!-- nav start -->
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark rounded">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="#">Home</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
						동네 정보
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">APT 매매</a>
						<a class="dropdown-item" href="#">APT 전월세</a>
						<a class="dropdown-item" href="#">주택 매매</a>
						<a class="dropdown-item" href="#">주택 전월세</a>
						<a class="dropdown-item" href="#">상권 정보</a>
						<a class="dropdown-item" href="#">환경 정보</a>
					</div>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${root}/map?act=list&key=&word=">Notice</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">News</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Contact</a>
				</li>
			</ul>
		</nav>
		<section id="index_section">
			<div class="card col-sm-12 mt-1" style="min-height: 850px;">
				<div class="card-body">
					<form id="writeform" method="post" action="">
						<input type="hidden" name="act" id="act" value="write">
						<div class="form-group" align="left">
							<label for="subject">제목:</label>
							<input type="text" class="form-control" id="subject" name="subject">
						</div>
					<div class="form-group" align="left">
						<label for="content">내용:</label>
						<textarea class="form-control" rows="15" id="content" name="content"></textarea>
					</div>
					<button type="button" class="btn btn-primary" onclick="javascript:writeArticle();">글작성</button>
					<button type="reset" class="btn btn-warning">초기화</button>
					</form>	
				</div>
			</div>
	</section>
	</div>
</body>
</html>
<%-- </c:if> --%>