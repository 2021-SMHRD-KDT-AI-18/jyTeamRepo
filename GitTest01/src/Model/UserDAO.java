package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	public UserDTO login(String id, String pw) {

		UserDTO info = null;
		// 커넥션자리
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
			// 클로즈 자리
		}

		return info;
	}

	public int join(UserDTO dto) {
		
		String id = dto.getId();
		String pw = dto.getPw();
		String user_nm = dto.getUser_nm();
		int cnt = 0;
		// 커넥션 자리
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
			// 클로즈 자리
		}
		
		return 0;
	}

}
