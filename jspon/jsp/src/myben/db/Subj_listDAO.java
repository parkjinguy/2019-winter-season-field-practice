package myben.db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Subj_listDAO {
	private static Subj_listDAO instance = new Subj_listDAO(); // 객체생성
	public static Subj_listDAO getInstance() {
		return instance;//클래스정보?
	}//하나의 클래스를 반환
	
	private Subj_listDAO() {} // 다른 클래스와 공유할수있게하는것
	public List<Subj_listDTO>  getSubjlist(int topidx){ // 정보 가저오는것 리스트 방식
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Subj_listDTO> subjlist = new ArrayList<Subj_listDTO>();

		String query = "select * from subj_list where top_idx=? and view='on'";
		try {
		conn = DBcon.getConnection();
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1,Integer.toString(topidx));
		rs=pstmt.executeQuery();

		if(rs.next()){
			do {
				Subj_listDTO subj = new Subj_listDTO();
				
				subj.setIdx(rs.getInt("idx"));
				subj.setSubject(rs.getString("subject"));
				subj.setKind(rs.getString("kind"));
				subj.setM_height(rs.getString("m_height"));
				subj.setView(rs.getString("view"));
				subj.setWidth(rs.getInt("width"));
				subj.setTop_idx(rs.getInt("top_idx"));
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
	public List<Subj_listDTO>  getSuCount(int topidx){ // 정보 가저오는것 리스트 방식
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Subj_listDTO> subjlist = new ArrayList<Subj_listDTO>();

		String query = "select count(idx) from subj_list where top_idx=? and view='on'";
		try {
		conn = DBcon.getConnection();
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1,Integer.toString(topidx));
		rs=pstmt.executeQuery();

		if(rs.next()){
			do {
				Subj_listDTO subj = new Subj_listDTO();
				
				subj.setIdx(rs.getInt("count"));
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
