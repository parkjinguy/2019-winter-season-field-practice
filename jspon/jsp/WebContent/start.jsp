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
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="obj" scope="page" class="myben.db.Obj_useDTO">
	</jsp:useBean>
<jsp:useBean id="sbj" scope="page" class="myben.db.Subj_answerDTO">
	</jsp:useBean>
<%

request.setCharacterEncoding("utf-8");
String count=request.getParameter("count");
String count2=request.getParameter("count2");
String top = request.getParameter("top_idx");

String major=(String)session.getAttribute("major");
String student_id=(String)session.getAttribute("student_id");
String name=(String)session.getAttribute("name");
String grade=(String)session.getAttribute("grade");

int tmp= Integer.parseInt(count);
int tmp2= Integer.parseInt(count2);
List<List<String>> tn = new ArrayList<List<String>>();
List<List<String>> ts = new ArrayList<List<String>>();
//String tn[] =null;
//String ts[] =null;
for(int i=1;i<tmp+1;i++){
	List<String> tn_tmp = new ArrayList<String>();
	tn_tmp.add(request.getParameter("at"+i));
	try{
		String tmpp=request.getParameter("a"+i);
		tn_tmp.add(tmpp);
		//tn.add(tmpp);
	}catch(Exception ee){
	}
	tn.add(tn_tmp);
}
for(int i=1;i<tmp2+1;i++){
	List<String> ts_tmp = new ArrayList<String>();
	ts_tmp.add(request.getParameter("ta"+i));
	ts_tmp.add(request.getParameter("t"+i));
	//ts.add(tmpp);
	ts.add(ts_tmp);
}
for(int i=0;i<tn.size();i++){
	obj.setMajor(major);
	obj.setStudent_id(student_id);
	obj.setGrade(grade);
	obj.setTop_idx(top);
	obj.setName(name);
	obj.setIdx(tn.get(i).get(0));
	obj.setCh(tn.get(i).get(1));
	Obj_useDAO dao = Obj_useDAO.getInstance();
	dao.insertObj(obj);
	
}
for(int i=0;i<ts.size();i++){
	sbj.setIdx(ts.get(i).get(0));
	sbj.setAnswere(ts.get(i).get(1));
	sbj.setTop_idx(top);
	sbj.setStudent_id(student_id);
	Subj_answerDAO sao = Subj_answerDAO.getInstance();
	sao.insertSub(sbj);
}

Connection conn = DBcon.getConnection();
PreparedStatement pstmt1 = null;
ResultSet rs = null;
Statement pstmt2 = null;
ResultSet rs2 = null;
ResultSet rs5 = null;
PreparedStatement pstmt3 = null;
Statement pstmt4 = null;
Statement pstmt5 = null;
	String query = "select top_idx,idx,ch,count(ch) as count from obj_use where not ch is null group by idx,Top_idx,ch";
	pstmt1 = conn.prepareStatement(query);
	rs=pstmt1.executeQuery();
	while(rs.next()){
		String tm="ch"+rs.getString("ch")+"_cn";
		String query2 = "update obj_list set "+tm+"="+rs.getString("count")+" where top_idx="+rs.getString("top_idx")+" and idx="+rs.getString("idx");
		pstmt2 = conn.createStatement();
		pstmt2.executeUpdate(query2);
		pstmt2.close();
	}
	String query5 = "insert into obj_count(top_idx,name) values("+top+",'"+name+"')";
	pstmt5 = conn.createStatement();
	pstmt5.executeUpdate(query5);
	pstmt5.close();
	String query3 = "select top_idx,count(name)as co from obj_count group by top_idx";
	pstmt3 = conn.prepareStatement(query3);
	rs2=pstmt3.executeQuery();
	while(rs2.next()){
		String query4 = "update topsub set stare="+rs2.getString("co")+" where top_idx="+rs2.getString("top_idx");
		pstmt4 = conn.createStatement();
		pstmt4.executeUpdate(query4);
		pstmt4.close();
	}
	rs.close();
	pstmt1.close();
	conn.close();

%>
<script>
alert("설문지가 제출 되었습니다");
//location.replace("index_tmp.jsp");
location.href="index_tmp.jsp";
//history.pushState(null,null,"index.html");//뒤로가기 주소를 바꾸는것
</script>
</body>
</html>