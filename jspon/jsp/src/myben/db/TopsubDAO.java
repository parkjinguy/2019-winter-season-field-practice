package myben.db;
import java.sql.*;

import java.util.*;
public class TopsubDAO {
	private static TopsubDAO instance = new TopsubDAO(); // 객체생성
	public static TopsubDAO getInstance() {
		return instance;//클래스정보?
	}//하나의 클래스를 반환
	
	private TopsubDAO(){}
	public List<TopsubDTO> getTopsub(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TopsubDTO> subjlist = new ArrayList<TopsubDTO>();
		String query = "select * from topsub";
		
		try {
			conn = DBcon.getConnection();
			pstmt = conn.prepareStatement(query);
			rs=pstmt.executeQuery();

			if(rs.next()){
				do {
					TopsubDTO subj = new TopsubDTO();
					
					subj.setTop_idx(rs.getInt("top_idx"));
					subj.setTopsubject(rs.getString("topsubject"));
					subj.setUses(rs.getString("uses"));
					subj.setStare(rs.getInt("stare"));
					
					
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
