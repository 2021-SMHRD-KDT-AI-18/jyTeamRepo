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

}
