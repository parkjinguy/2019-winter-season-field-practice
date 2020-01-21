<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
if(request.getProtocol().compareTo("HTTP/1.0")==0){
	response.setHeader("Pargma","no-cache");
}else if(request.getProtocol().compareTo("HTTP/1.1")==0){
	response.setHeader("Cache-Control","no-cache,must-revalidate");
}
response.setDateHeader("Expires",0);
%>
<script>
location.href="index.html";
</script>
</body>
</html>