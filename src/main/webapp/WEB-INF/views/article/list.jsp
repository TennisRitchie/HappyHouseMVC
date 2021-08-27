<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%-- <c:if test="${userinfo == null}">
	<c:redirect url="/map"/>
</c:if>
<c:if test="${userinfo != null}"> --%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<title>BackEnd Project</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="shortcut icon" href="img/favicon.ico">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function movewrite() {
			location.href="${root}/map?act=mvwrite";
		}
		function searchArticle() {
			if(document.getElementById("word").value == "") {
				alert("모든 목록 조회!!");
			}
			document.getElementById("searchform").action = "${root}/map";
			document.getElementById("searchform").submit();
		}
		</script>
</head>
<body>
	<div class="container">
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
				<form id="searchform" method="get" class="form-inline" action="">
	  			<input type="hidden" name="act" id="act" value="list">
	  			<table class="table table-borderless">
	  			<tr>
	  				<td align="left"><button type="button" class="btn btn-warning" onclick="javascript:movewrite();">글쓰기</button></td>
	  				<td align="right">
		  	  		<select class="form-control" name="key" id="key">
			    	<option value="subject" selected="selected">제목</option>
			   		<option value="articleno">글번호</option>
			 		 </select>
			  		<input type="text" class="form-control" placeholder="검색어 입력." name="word" id="word">
			  		<button type="button" class="btn btn-primary" onclick="javascript:searchArticle();">검색</button>
					</td>
	  			</tr>
	  			</table>
	  			</form>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>번호</th>
							<th class="title">제목</th>
							<th>내용</th>
							<th>작성일</th>
							<c:if test="${articles.size() != 0}">
	  				<c:forEach var="article" items="${articles}">
							<c:if test="${userinfo.userid == article.userid}">
								<th></th>
	      					</c:if>
	      					</c:forEach>
	  		</c:if>
						</tr>
					</thead>
					<tbody>
				<c:if test="${articles.size() != 0}">
	  				<c:forEach var="article" items="${articles}">

						<tr>
							<td>${article.articleno}</td>
							<td class="notice">${article.subject}</td>
							<td>${article.content}</td>
							<td>${article.regtime}</td>
							<c:if test="${userinfo.userid == article.userid}">
	        					<td>
								<a href="${root}/map?act=mvmodify&articleno=${article.articleno}">수정</a>
								<a href="${root}/map?act=delete&articleno=${article.articleno}">삭제</a>
								</td>
	      					</c:if>
						</tr>
				</c:forEach>
	  		</c:if>
					</tbody>
				</table>
	  		<c:if test="${articles.size() == 0}">
	  		<table class="table table-striped">
	  		<tbody>
	      <tr class="table-info" align="center">
	        <td>작성된 글이 없습니다.</td>
	      </tr>
	    </tbody>
	  </table>
	  </c:if>
				</div>
			</div>
		</section>
	</div>
</body>
</html>

<%-- </c:if> --%>