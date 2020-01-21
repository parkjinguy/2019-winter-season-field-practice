package myben.db;
import java.sql.*;
import java.util.*;

public class Obj_updateDAO {
	private static Obj_updateDAO instance = new Obj_updateDAO(); // ��ü����
	public static Obj_updateDAO getInstance() {
		return instance;//Ŭ��������?
	}//�ϳ��� Ŭ������ ��ȯ
	
	private Obj_updateDAO() {} // �ٸ� Ŭ������ �����Ҽ��ְ��ϴ°�
	public List<Obj_updateDTO>  getObjlist(){ // ���� �������°� ����Ʈ ���
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
