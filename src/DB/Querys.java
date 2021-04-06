package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Querys {
	
	static Connection conn;
	
	public Querys(Connection conn){
		this.conn = conn;
	}

	//중복검사 : 해당 아이디가 존재하는가?false:ture
	public boolean checkId(String userid) {
		PreparedStatement psmt = null;
	    ResultSet rs = null;
        String sql = "SELECT * FROM user WHERE xid = ?";
        int row = -1;
        try {
        	psmt = conn.prepareStatement(sql);
	        psmt.setString(1, userid);
	        rs = psmt.executeQuery();
	        
	        if(rs.next()) {
	        	row = rs.getRow();
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        if(row != 1)
        	return true;
        return false;
	}
	//계정 insert
	public  void insertAccount(String xid, String xpw) {
		PreparedStatement psmt = null;
		String sql = "INSERT INTO user(xid, xpw) VALUES(?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, xid);
			psmt.setString(2, xpw);
			psmt.executeUpdate();
			psmt.close();
			}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	//로그인
	public  boolean loginAccount(String xid, String xpw) {
		PreparedStatement psmt = null;
		String sql = "SELECT * FROM user WHERE xid = ? AND xpw = ?";
		ResultSet rs;
		int row = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, xid);
			psmt.setString(2, xpw);
			rs = psmt.executeQuery();
	        if(rs.next()) {
	        	row = rs.getRow();
	        }
		}catch (SQLException e) {
			e.printStackTrace();
		}
		if(row == 1)
			return true;
		return false;
	}
	//수강신청 불러오기
	public String loadLecList(String xid) {
		PreparedStatement psmt = null;
		String sql = "SELECT * FROM user WHERE xid = ?";
		ResultSet rs;
		String result = null;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, xid);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getString("lectures");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	//수강신청 초기화 : 미사용
	public  void initMyXII(String xid) {
		PreparedStatement psmt = null;
		String sql = "UPDATE user SET lectures = ? WHERE xid = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, "0");
			psmt.setString(2, xid);
			psmt.executeUpdate();
			psmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	//수강신청목록 저장 : 미사용
	public  void saveMyXII(String xid, String data) {
		PreparedStatement psmt = null;
		String sql = "UPDATE user SET lectures = ? WHERE xid = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, data);
			psmt.setString(2, xid);
			psmt.executeUpdate();
			psmt.close();
		}catch(SQLException e) {
			System.out.println("데이터 저장 실패");
		}
		System.out.println("데이터 저장 완료");
	}
}
