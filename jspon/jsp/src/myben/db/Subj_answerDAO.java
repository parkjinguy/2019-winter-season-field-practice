package myben.db;

import java.sql.*;


public class Subj_answerDAO {
	private static Subj_answerDAO instance = new Subj_answerDAO(); // ��ü����
	public static Subj_answerDAO getInstance() {
		return instance;//Ŭ��������?
	}//�ϳ��� Ŭ������ ��ȯ
	
	private Subj_answerDAO() {} // �ٸ� Ŭ������ �����Ҽ��ְ��ϴ°�
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
