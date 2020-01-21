package myben.db;
import java.sql.*;
import java.util.*;
public class Obj_listDAO {
	private static Obj_listDAO instance = new Obj_listDAO(); // 객체생성
	public static Obj_listDAO getInstance() {
		return instance;//클래스정보?
	}//하나의 클래스를 반환
	
	private Obj_listDAO() {} // 다른 클래스와 공유할수있게하는것
	public List<Obj_listDTO>  getObjlist(int topidx){ // 정보 가저오는것 리스트 방식
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Obj_listDTO> subjlist = new ArrayList<Obj_listDTO>();

		String query = "select * from obj_list where top_idx=? and view='on'";

		try {
		conn = DBcon.getConnection();
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1,Integer.toString(topidx));
		rs=pstmt.executeQuery();

		if(rs.next()){
			do {
				Obj_listDTO subj = new Obj_listDTO();
				
				subj.setIdx(rs.getInt("idx"));
				subj.setSubject(rs.getString("subject"));
				subj.setKind(rs.getString("kind"));
				subj.setNum(rs.getString("num"));
				subj.setView(rs.getString("view"));
				subj.setCh1(rs.getString("ch1"));
				subj.setCh2(rs.getString("ch2"));
				subj.setCh3(rs.getString("ch3"));
				subj.setCh4(rs.getString("ch4"));
				subj.setCh5(rs.getString("ch5"));
				subj.setCh6(rs.getString("ch6"));
				subj.setCh7(rs.getString("ch7"));
				subj.setCh1_cn(rs.getInt("ch1_cn"));
				subj.setCh2_cn(rs.getInt("ch2_cn"));
				subj.setCh3_cn(rs.getInt("ch3_cn"));
				subj.setCh4_cn(rs.getInt("ch4_cn"));
				subj.setCh5_cn(rs.getInt("ch5_cn"));
				subj.setCh6_cn(rs.getInt("ch6_cn"));
				subj.setCh7_cn(rs.getInt("ch7_cn"));
				subj.setInnum1(rs.getString("innum1"));
				subj.setInnum2(rs.getString("innum2"));
				subj.setInnum3(rs.getString("innum3"));
				subj.setInnum4(rs.getString("innum4"));
				subj.setInnum5(rs.getString("innum5"));
				subj.setInnum6(rs.getString("innum6"));
				subj.setInnum7(rs.getString("innum7"));
				subj.setInch1(rs.getInt("inch1"));
				subj.setInch2(rs.getInt("inch2"));
				subj.setInch3(rs.getInt("inch3"));
				subj.setInch4(rs.getInt("inch4"));
				subj.setInch5(rs.getInt("inch5"));
				subj.setInch6(rs.getInt("inch6"));
				subj.setInch7(rs.getInt("inch7"));
				subj.setTop_idx(rs.getInt("top_idx"));
				subj.setCount(rs.getString("idx"));
				subjlist.add(subj);
			}while(rs.next());
		}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(rs != null)
				try {rs.close();}catch(SQLException ex){}
			if(pstmt != null)
				try {pstmt.close();} catch(SQLException ex) {}
			if(conn != null)
				try {conn.close();} catch(SQLException ex) {}
		}
		return subjlist;
	}
}
