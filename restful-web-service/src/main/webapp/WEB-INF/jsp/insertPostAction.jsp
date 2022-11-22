<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.demo.user.Board" %>
<%@ page import="com.example.demo.user.BoardDaoService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>

<%
	request.setCharacterEncoding("UTF-8");
	String writer = request.getParameter("writer");
	String title = request.getParameter("boardTitle");
	String content = request.getParameter("boardContent");
	
	Board boardDO = new Board();
	boardDO.setWriter(writer);
	boardDO.setBoardTitle(title);
	boardDO.setBoardContent(content);
	
	BoardDaoService boardDAO = new BoardDaoService();
	boardDAO.insertBoard(boardDO);
	
	response.sendRedirect("/board");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>