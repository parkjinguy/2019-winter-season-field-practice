<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="myben.db.*"%>
<%request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
	<title></title>
</head>
<body>
<%
String major = request.getParameter("dt1");//학과
String student_id = request.getParameter("dt2");//학번
String name = request.getParameter("dt3");//이름
String grade = request.getParameter("sel");//학년
session.setAttribute("major", major);
session.setAttribute("student_id", student_id);
session.setAttribute("name", name);
session.setAttribute("grade", grade);
%>
<script>
location.href="selections.jsp";
</script>
</body>
</html>