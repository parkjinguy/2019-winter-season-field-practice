<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="myben.db.*" %>
<%@page import="java.util.*"%>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<style>
body{background-color:#E2E2E2}
form{margin-left:25%;background-color:#ffffff;width:50%;margin-top:5%}
td{height:30px}
table{margin-left:6%}
</style>
<script src="//code.jquery.com/jquery.min.js"></script>
<body>
<%
String major = (String)session.getAttribute("major");
String tmp1=request.getParameter("tmp1");
String tmp2=request.getParameter("tmp2");
int idx=Integer.parseInt(tmp2);
Obj_listDAO topDao = Obj_listDAO.getInstance();
List<Obj_listDTO> liDto = null;
liDto=topDao.getObjlist(idx);

Subj_listDAO subDao = Subj_listDAO.getInstance();
List<Subj_listDTO> sliDto = null;
sliDto=subDao.getSubjlist(idx);
int numm=0;
int con=1;
%>

<form action="start.jsp" method="get" id="form">
	<table>
		<tr>
			<th><h2><%=tmp1 %></h2></th>
		</tr>
		<tr>
			<th>객관식<input type="text" value="<%=tmp2%>" name="top_idx" class="st"></th>
		</tr>
<%
String on="on";
String radio="radio";
String checkbox="checkbox";
int b=0;
for(Obj_listDTO dto:liDto){
	b++;
	if(dto.getView().equals(on)){
		String[] ch1 = {dto.getCh1(),dto.getCh2(),dto.getCh3(),dto.getCh4(),dto.getCh5(),dto.getCh6(),dto.getCh7()};
		String[] ch2 = {dto.getInnum1(),dto.getInnum2(),dto.getInnum3(),dto.getInnum4(),dto.getInnum5(),dto.getInnum6(),dto.getInnum7()};
		int[] ch3 = {dto.getInch1(),dto.getInch2(),dto.getInch3(),dto.getInch4(),dto.getInch5(),dto.getInch6(),dto.getInch7()};
%>
		<tr>
			<td colspan="7"><%=con%>. <%=dto.getSubject() %></td>
		</tr>
		<tr>
			<td id="<%=dto.getIdx()%>">
			<input type="text" value="<%=b %>" name="at<%=b%>" class="st">
			<%if(dto.getKind().equals(radio)){
				for(int i=0;i<Integer.parseInt(dto.getNum());i++){%>
					<input type="radio" value="<%=i+1 %>" id="a<%=b %>" class="<%=b %>" name="a<%=b %>" onclick="cleck('<%=ch2[i]%>','<%=ch3[i]%>','<%=b-1%>')"><span class=<%=b %>><%=ch1[i] %></span>
					<span name="q<%=b %>" class="sc">0</span>
			<%}}else if(dto.getKind().equals(checkbox)){ 
				for(int i=0;i<Integer.parseInt(dto.getNum());i++){%>
				<input type="checkbox" value="<%=i+1 %>" id="a<%=b %>" class="<%=b %>" name="a<%=b %>" onclick="cleck('<%=ch2[i]%>','<%=ch3[i]%>','<%=b-1%>')"><span class=<%=b %>><%=ch1[i] %></span>
				<span name="q<%=b %>" class="sc">1</span>
				<%}}con++; %>
				</td>
		</tr>
		<%}%>
		<%} %>
		<tr>
			<th>주관식</th>
		</tr>
		<%
		for(Subj_listDTO sto:sliDto){ 
			numm++; 
			String texbox="text";
		%>
		<tr>
			<td><%=con%>. <%=sto.getSubject() %>
		</tr>
		<tr>
			<td><%if(sto.getKind().equals(texbox)){%>
				<input type="text" name="t<%=numm %>" id="t<%=numm %>" style="width:<%=sto.getWidth() %>px">
			<%}else{ %>
					<textarea rows="<%=sto.getM_height() %>" id="t<%=numm %>" style="width:<%=sto.getWidth()%>px" name="t<%=numm %>"></textarea>
				<%}%>
				<input type="text" value="<%=numm %>" name="ta<%=numm %>" class="st">
				</td>
		</tr>
		<%con++;}%>
		<tr>
			<td style="text-align:center">
				<input type="button" value="완료" onclick="go()">
			</td>
		</tr>
	</table>
	<input type="text" value="<%=b %>" name="count" class="st">
	<input type="text" value="<%=numm %>" name="count2" class="st">
</form>
<script>
var count = new Array();
var countt = new Array();
var tmp2=0;
$(".st").hide();
var tmp=<%=numm%>
<%
int c=0;
for(Obj_listDTO dto:liDto){%>
	count[<%=c%>]="<%=dto.getCount()%>";
	<%c++;}%>
	var ee=0;
	var as=0;
	$('.sc').css("opacity",0.1);
	function cleck(a,b,c){
		countt[c]=1;
		
		if(a=='off'){
			as=1;
			tmp2=b;
			$('.'+b).removeAttr("onclick");
			$("input:checkbox[class="+b+"]").prop("checked",false);
			$('.'+b).attr("onclick","return false");
			$("input[name=a"+b+"]").attr("readonly",true);
			$('.'+b).css("opacity",0.5);
			countt[b-1]=1;
		}else if(a=='on'){
			b=tmp2;
			//$('.'+b).css("display","inline");
			$('.'+b).removeAttr("onclick");
			$('.'+b).attr("onclick","ch()");
			$('.'+b).css("opacity",1.0);
		}
	}
	function ch(){
		countt[ee]=1;
	}
	var cotta=count.length;
	var check=0;
	var check2=0;

	function go(){
		for(var i=0;i<count.length-1;i++){
				if(countt[i]==1){
					check=1;
				}else{
					check=0;
					break;
				}
		}for(var i=1;i<tmp+1;i++){
			var tx=$('#t'+i).val();
			if(tx!=""){
				check2=1;
			}else{
				check2=0;
				break;
			}
		}
		if(check==0 || check2==0){
			alert("빠진 부분이 있습니다.");
		}else{
			$("#form").submit();
		}
	}
	
</script>
</body>
</html>