<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.example.demo.user.User"%>
<%@ page import="com.example.demo.user.UserDaoService"%>
<%@ page import="java.io.PrintWriter"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%
// 사용자 입력 정보 추출
String name = request.getParameter("name");
String password = request.getParameter("password");

// 2. UserDO 클래스 객체 생성 후 맴버변수에 값 초기화
User userDO = new User();
userDO.setName(name);
userDO.setPassword(password);

// 3. UserDO 클래스 객체 생성 후 getUser() 메소드 호출하면서 userDO 객체를 넘겨준다
UserDaoService userDAO = new UserDaoService();
User user = userDAO.getUser(userDO);

// 화면 네비게이션
if (user != null) {
	session.setAttribute("NameKey", name);
	response.sendRedirect("/board");
	/* out.println("<script>alert('로그인 성공');</script>"); */
} else {
	/* out.println("<script>alert('로그인 실패');</script>"); */
	response.sendRedirect("/login");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RESTful Service</title>
</head>
<body>

</body>
</html>