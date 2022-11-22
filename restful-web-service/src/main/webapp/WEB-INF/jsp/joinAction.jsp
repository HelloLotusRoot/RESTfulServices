<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.example.demo.user.User"%>
<%@ page import="com.example.demo.user.UserDaoService"%>
<%@ page import="java.io.PrintWriter"%>
<%
request.setCharacterEncoding("UTF-8");
String name = request.getParameter("name");
String password = request.getParameter("password");
String ssn = request.getParameter("ssn");

User userDO = new User();
userDO.setName(name);
userDO.setPassword(password);
userDO.setSsn(ssn);

UserDaoService userDAO = new UserDaoService();
userDAO.insertUser(userDO);

response.sendRedirect("/login");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RESTful Service</title>
</head>
<body>

</body>
</html>