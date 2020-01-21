package myben.db;
import java.sql.*;
import java.util.*;

public class Obj_useDAO {
	private static Obj_useDAO instance = new Obj_useDAO(); // 객체생성
	public static Obj_useDAO getInstance() {
		return instance;//클래스정보?
	}//하나의 클래스를 반환
	
	private Obj_useDAO() {} // 다른 클래스와 공유할수있게하는것
	public void insertObj(Obj_useDTO obj) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "insert into obj_use(major,student_id,name,grade,top_idx,idx,ch) values(?,?,?,?,?,?,?)";
		try {
			conn = DBcon.getConnection();
			pstmt = conn.prepareStatement(query);
			System.out.print("1");
					pstmt.setString(1,  obj.getMajor());
					pstmt.setString(2,  obj.getStudent_id());
					pstmt.setString(3,  obj.getName());
					pstmt.setString(4,  obj.getGrade());
					pstmt.setString(5,  obj.getTop_idx());
					pstmt.setString(6,  obj.getIdx());
					pstmt.setString(7,  obj.getCh());
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
	public void updateUse(String top_idx,String idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "update obj_list set ch3_cn=(select count(ch) from obj_use where ch=3 and idx=? and top_idx=?) where top_idx=? and idx=?;";
		try {
			conn = DBcon.getConnection();
			pstmt = conn.prepareStatement(query);
					pstmt.setString(1,  top_idx);
					pstmt.setString(2,  idx);
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
