<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--  자바 클래스 임포트  -->
<%@ page import="com.example.demo.user.Board" %>
<%@ page import="com.example.demo.user.BoardDaoService" %>
<%@ page import="java.util.List" %>

<%
	request.setCharacterEncoding("UTF-8");
	String boardId = request.getParameter("boardId");
	
	Board boardDO = new Board();
	boardDO.setBoardId(Integer.parseInt(boardId));
	
	BoardDaoService boardDAO = new BoardDaoService();
	boardDAO.deleteBoard(boardDO);
	
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