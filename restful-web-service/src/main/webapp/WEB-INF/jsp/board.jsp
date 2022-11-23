<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>

<%@ page import="com.example.demo.user.Board"%>
<%@ page import="com.example.demo.user.BoardDaoService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

<title>RESTful Service</title>
<style>
.hidden {
	display: none;
}
</style>
</head>
<%
request.setCharacterEncoding("UTF-8");
String searchField = ""; //검색 대상(즉, 제목 or 작성자)
String searchText = ""; //검색 내용
if (request.getParameter("searchCondition") != "" && request.getParameter("searchKeyword") != "") {
	searchField = request.getParameter("searchCondition");
	searchText = request.getParameter("searchKeyword");
}

BoardDaoService boardDAO = new BoardDaoService();
List<Board> boardList = boardDAO.getBoardList(searchField, searchText);

request.setAttribute("boardList", boardList);
// session과 request 차이점 : request는 현재 페이지 / session은 여러 페이지에서 공유할 때 쓴다.

request.setAttribute("totalList", boardList.size());
%>
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
			<a class="navbar-brand" href="/main">20191734</a>
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
		<table class="table table-striped" style="text =align: center;">
			<tr>
				<th style="background-color: #eeeeee; text-align: center;">게시물
					번호</th>
				<th style="background-color: #eeeeee; text-align: center;">제목</th>
				<th style="background-color: #eeeeee; text-align: center;">작성자</th>
				<th style="background-color: #eeeeee; text-align: center;">작성일</th>
			</tr>


			<%
			for (Board boards : boardList) {
			%>
			<tr>
				<td align="center"><%=boards.getBoardId()%></td>
				<!--<td align="left"><a href="/post/<%= boards.getBoardId()%>"><%=boards.getBoardTitle() %></a></td>-->
				<td align="left"><a><%=boards.getBoardTitle() %></a></td>
				<!-- 제목으로 갈 때 게시글 번호를 같이 넘겨줘라.<a href="/post/<%=boards.getBoardId()%>"><%=boards.getBoardTitle()%></a></td> -->
				<td align="center"><%=boards.getWriter()%></td>
				<td align="center"><%=boards.getBoardDate()%></td>
			</tr>
			<tr>
				<th style="background-color: #eeeeee; text-align: center;">글 내용</th>
				<td colspan="3"><%=boards.getBoardContent()%></td>
			</tr>
			<%
			String act = request.getParameter("act");
			if ( act != null ) {
			%>
			<tr>
				<th style="background-color: #eeeeee; text-align: center;">글 내용</th>
				<td colspan="3"><%=boards.getBoardContent()%></td>
			</tr>
			<%
			} else {
			%><tr>
				<td class="hidden" style="text-align: center;">글 내용</td>
				<td class="hidden" colspan="3"><%=boards.getBoardContent()%></td>
			</tr>
			<%
			} 
			%>
			<%
			}
			%>
		</table>
		<%
		if (name == null) {
		%>
		<a href="/board" class="btn btn-primary pull-rigth">전체 게시물 목록 보기</a>
		<%
		} else {
		%>
		<a href="/board" class="btn btn-primary pull-rigth">전체 게시물 목록 보기</a> <a
			href="/insertPost" class="btn btn-primary pull-rigth">새 게시글 등록</a>
		<%
		}
		%>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="application/js" src="/js/bootstrap.min.js"></script>
</body>
</html>