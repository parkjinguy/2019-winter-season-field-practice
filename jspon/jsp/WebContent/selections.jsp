<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="myben.db.*" %>
<%@page import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<style>
body{background-color:#E2E2E2}
a:link { color: red; text-decoration: none;}
a:visited { color: black; text-decoration: none;}
a:hover { color: blue; text-decoration: ;}
table{text-align:center;margin-left:12px;border: 1px solid #444444;border-collapse: collapse;width:690px}
div{margin-left:31%;margin-top:10%;width:37%;background-color:#ffffff;border-radius:8px;height:382px;padding-top:15px}
td,th{height:30px;border: 1px solid #444444;}

</style>
<body>
<script src="//code.jquery.com/jquery.min.js"></script>
<%
String major = (String)session.getAttribute("major");
if(major==null){
	//response.sendRedirect("indxe.html");
	//return;
}
TopsubDAO topDao = TopsubDAO.getInstance();
List<TopsubDTO> liDto = null;
liDto=topDao.getTopsub();
Obj_listDAO obj = Obj_listDAO.getInstance();
List<Obj_listDTO> lobj = null;
%>
<div>
	<table id="tab">
		<tr>
			<th>번호</th><th style="width:300px">설문 목록</th><th>객관식 수량</th><th>주관식 수량</th>
		</tr>
<%
String tmp="on";
int i=1;
for(TopsubDTO dto:liDto){
	if(dto.getUses().equals(tmp)){
		Connection conn = DBcon.getConnection();
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		try{
			String query = "select count(idx) as cc from obj_list where top_idx=? and view='on'";
			String query2 = "select count(idx) as ca from subj_list where top_idx=? and view='on'";
			pstmt1 = conn.prepareStatement(query);
			pstmt1.setInt(1,dto.getTop_idx());
			rs=pstmt1.executeQuery();
			pstmt2 = conn.prepareStatement(query2);
			pstmt2.setInt(1,dto.getTop_idx());
			rs2=pstmt2.executeQuery();
			rs.next();
			rs2.next();
%>			
		<tr onclick="go('<%=dto.getTopsubject()%>','<%=dto.getTop_idx()%>')">
			<td style="width:40px"><%=i %>
			</td>
			<td>
			<%=dto.getTopsubject() %></td>
			<td style="width:100px"><%=rs.getString("cc") %>개</td><td style="width:100px"><%=rs2.getString("ca") %>개</td>
		</tr>
		<%
		rs.close();
		pstmt1.close();
		conn.close();
	}catch(SQLException ex){
	}i++;
		}} %>
		<%for(int a=11;a>i;a--){ %>
		<tr>
		<td></td><td></td><td></td><td></td>
		</tr>
		<%} %>
	</table>
</div>
<script>
$(document).ready(function () {
    $('tr').has('td').css('cursor', 'pointer');
});

$("table th").css("backgroundColor","#ffffff");
$(document).ready(function(){
	$('table tr').mouseover(function(){ 
		$(this).css("backgroundColor","#ccc"); 
	}); 

	$('table tr').mouseout(function(){ 
		$(this).css("backgroundColor","#fff"); 
	}); 
});
function go(a, b){
	location.href="startSy.jsp?tmp1="+a+"&tmp2="+b;
}
</script>
</body>
</html>