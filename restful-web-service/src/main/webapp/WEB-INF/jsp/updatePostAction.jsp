<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<!--  자바 클래스 임포트  -->
<%@ page import="com.example.demo.user.Board"%>
<%@ page import="com.example.demo.user.BoardDaoService"%>
<%@ page import="java.util.List"%>

<%
request.setCharacterEncoding("UTF-8");
String boardId = request.getParameter("boardId");
String boardTitle = request.getParameter("boardTitle");
String boardContent = request.getParameter("boardContent");

Board boardDO = new Board();
boardDO.setBoardId(Integer.parseInt(boardId));
boardDO.setBoardTitle(boardTitle);
boardDO.setBoardContent(boardContent);

BoardDaoService boardDAO = new BoardDaoService();
boardDAO.updateBoard(boardDO);

response.sendRedirect("/board");
%>

<html>
<head>
<meta charset="UTF-8">
<title>"수정" 컨트롤러 페이지</title>
</head>
<body>

</body>
</html>