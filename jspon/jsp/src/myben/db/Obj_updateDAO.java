package myben.db;
import java.sql.*;
import java.util.*;

public class Obj_updateDAO {
	private static Obj_updateDAO instance = new Obj_updateDAO(); // 객체생성
	public static Obj_updateDAO getInstance() {
		return instance;//클래스정보?
	}//하나의 클래스를 반환
	
	private Obj_updateDAO() {} // 다른 클래스와 공유할수있게하는것
	public List<Obj_updateDTO>  getObjlist(){ // 정보 가저오는것 리스트 방식
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Obj_updateDTO> subjlist = new ArrayList<Obj_updateDTO>();

		String query = "select top_idx,idx,ch,count(ch) from obj_use group by ch;";

		try {
		conn = DBcon.getConnection();
		pstmt = conn.prepareStatement(query);
		rs=pstmt.executeQuery();

		if(rs.next()){
			do {
				Obj_updateDTO subj = new Obj_updateDTO();
				
				subj.setIdx(rs.getInt("idx"));
				subj.setCount(rs.getInt("count"));
				subj.setTop_idx(rs.getInt("top_idx"));
				subj.setCh(rs.getInt("ch"));
				subj.setColumn("ch"+rs.getInt("ch")+"_cn");
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
	public void updateUse(Obj_updateDTO obs) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "update obj_list set ?=(select count(ch) from obj_use where ch=? and idx=? and top_idx=?) where top_idx=? and idx=?;";
		try {
			conn = DBcon.getConnection();
			pstmt = conn.prepareStatement(query);
					pstmt.setString(1,  obs.getColumn());
					pstmt.setString(2,  "");
					pstmt.executeUpdate();
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				if(pstmt != null)
					try {pstmt.close();} catch(SQLException ex) {}
				if(conn != null)
					try {conn.close();} catch(SQLException ex) {}
			}
	}
}
