package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	public UserDTO login(String id, String pw) {

		UserDTO info = null;
		connection();
		try {
			String sql = "select * from TB_USER_INFO where ID = ? and PW = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(2, pw);
			psmt.setString(1, id);

			rs = psmt.executeQuery();
			if(rs.next()) {
				String login_id = rs.getString(1);
				String login_pw = rs.getString(2);
				info = new UserDTO(login_id, pw, login_pw);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			close();
		}

		return info;
	}

	public int join(UserDTO dto) {
		
		String id = dto.getId();
		String pw = dto.getPw();
		String user_nm = dto.getUser_nm();
		int cnt = 0;
		connection();
		try {
			String sql = "insert into TB_USER_INFO values(?,?,?)";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, user_nm);
			
			cnt = psmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return cnt;
	}

	// 클로즈
	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//connection 메소드
	private void connection() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String db_url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";
			String db_id = "campus_23K_AI18_p1_1";
			String db_pw = "smhrd1";

			conn = DriverManager.getConnection(db_url, db_id, db_pw);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
}
