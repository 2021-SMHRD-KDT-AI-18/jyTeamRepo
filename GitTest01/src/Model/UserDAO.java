package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			if (rs.next()) {
				String login_id = rs.getString(1);
				String login_pw = rs.getString(2);
				info = new UserDTO(login_id, login_pw);
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
			
			if(cnt > 0) {
				insertTBSong(dto.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return cnt;
	}

	private void insertTBSong(String id) {
		// TB_SONG 테이블에 신규 회원가입 한 인원 초기값 넣기
		
		connection();
		
		String sql = "INSERT INTO TB_SONG VALUES(?,?,?,?,?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setInt(2, 0);
			psmt.setInt(3, 0);
			psmt.setInt(4, 0);
			psmt.setInt(5, 0);
			psmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		
		
	}

	// 랭킹
	public ArrayList<UserDTO> userlist(){
		
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		
		try {
			connection();
			String sql = "SELECT * FROM TB_SONG ORDER BY SCORE_MAX DESC";
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			System.out.println("ID\t최고점수\t  코인수\t  정답률\t ");
			while(rs.next() != false) {
				String list_id = rs.getString(1);
				int list_score = rs.getInt(2);
				int list_coin = rs.getInt(3);
				int list_play = rs.getInt(4);
				int list_correct = rs.getInt(5);
				UserDTO dto = new UserDTO(list_id, list_score, list_coin,list_play,list_correct);
				list.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
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

	// connection 메소드
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
