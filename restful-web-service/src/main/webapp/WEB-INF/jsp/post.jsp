<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" initial-scale="1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<title>글상세</title>
<style>
#div_box {
	position: absolute;
	top: 10%;
	left: 40%;
}
}
</style>
</head>
<body>
	<%
	String name = null;
	if (session.getAttribute("name") != null) {
		name = (String) session.getAttribute("name");
	}
	%>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/main">JSP 게시판 웹 사이트</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1" aria-expanded="false">
			<ul class="nav navbar-nav">
				<li><a href="/main">메인</a></li>
				<li class="active"><a href="/board">게시판</a></li>
			</ul>
			<%
			if (name == null) {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li class="active"><a href="/login">로그인</a></li>
						<li><a href="/join">회원가입</a></li>
					</ul></li>
			</ul>
			<%
			} else {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">회원관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/logoutAction">로그아웃</a></li>
					</ul></li>
			</ul>
			<%
			}
			%>

		</div>
	</nav>
	<div class="container">
		<div class="row">
			<form method="POST" action="/insertPostAction">
				<input type="hidden" name="boardId" value="${boards.boardId}" />
				<table class="table table-striped"
					style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="2"
								style="background-color: #eeeeee; text-align: center;">게시글
								상세보기</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control"
								placeholder="글 제목" name="boardTitle" value="${board.title}"></td>
						</tr>
						<tr>
							<td><input type="text" class="form-control"
								placeholder="글쓴이" name="writer" value="${boards.writer}"></td>
						</tr>
						<tr>
							<td><textarea class="form-control" placeholder="글 내용"
									name="boardContent" maxlength="750">${boards.boardContent}</textarea></td>
						</tr>
						<tr>
							<td>등록일</td>
							<td align="left">${boards.writer}</td>
						</tr>
					</tbody>
				</table>
				<input type="submit" class="btn btn-primary pull-rigth" value="글수정">
			</form>
			<hr>
			<a href="/insertPost" class="btn btn-primary pull-rigth">새 게시글 등록</a>
			<a href="/deleteBoard?boardId=${boards.boardId}"
				class="btn btn-primary pull-rigth">게시글 삭제</a> <a href="/board"
				class="btn btn-primary pull-rigth">전체 게시물 목록 보기</a>
		</div>
	</div>
</body>
</html>