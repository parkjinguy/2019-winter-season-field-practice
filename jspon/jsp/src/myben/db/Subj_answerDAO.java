package myben.db;

import java.sql.*;


public class Subj_answerDAO {
	private static Subj_answerDAO instance = new Subj_answerDAO(); // 객체생성
	public static Subj_answerDAO getInstance() {
		return instance;//클래스정보?
	}//하나의 클래스를 반환
	
	private Subj_answerDAO() {} // 다른 클래스와 공유할수있게하는것
	public void insertSub(Subj_answerDTO obj) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "insert into subj_answer(idx,answer,top_idx,student_id) values(?,?,?,?)";
		try {
			conn = DBcon.getConnection();
			pstmt = conn.prepareStatement(query);
			System.out.print("1");
					pstmt.setString(1,  obj.getIdx());
					pstmt.setString(2,  obj.getAnswere());
					pstmt.setString(3,  obj.getTop_idx());
					pstmt.setString(4,  obj.getStudent_id());
					System.out.print("2");
					pstmt.executeUpdate();
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
	}
}
