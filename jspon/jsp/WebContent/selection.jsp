<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="myben.db.*"%>
<%request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
	<title></title>
</head>
<body>
<%
String major = request.getParameter("dt1");//�а�
String student_id = request.getParameter("dt2");//�й�
String name = request.getParameter("dt3");//�̸�
String grade = request.getParameter("sel");//�г�
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