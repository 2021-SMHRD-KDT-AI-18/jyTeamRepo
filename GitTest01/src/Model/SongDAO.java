package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javazoom.jl.player.MP3Player;

public class SongDAO {
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	
	// close
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

	
	
	
	
	
	
	
	
	
	
	
	Random ran = new Random();
	
	// 자영 노래 재생 기능 만들기
	
	String title = "제목";
	String singer = "가수명";
	String path = "경로주소";
	
	//랜덤 곡 10개 추가
	// 노래재생 추가
	SongDTO m1 = new SongDTO("Anne Marie", "2002",  "C:\\music_v2\\Anne Marie - 2002.mp3");
	SongDTO m2 = new SongDTO("Billie Eilish", " bad guy","C:\\music_v2\\Billie Eilish - bad guy.mp3");
	SongDTO m3 = new SongDTO("Carmen Twillie, Lebo M.", " Circle of Life", "C:\\music_v2\\Carmen Twillie, Lebo M. - Circle of Life.mp3");
	SongDTO m4 = new SongDTO("CHUNG HA", " 벌써 12시","C:\\music_v2\\CHUNG HA - 벌써 12시.mp3");
	SongDTO m5 = new SongDTO("Idina Menzel", "Let It Go", "C:\\music_v2\\Idina Menzel - Let It Go.mp3");
	SongDTO m6 = new SongDTO("Itzy", "Dalla Dalla",  "C:\\music_v2\\Itzy - Dalla Dalla.mp3");
	SongDTO m7 = new SongDTO("JENNIE", "SOLO", "C:\\music_v2\\JENNIE - SOLO.mp3");
	SongDTO m8 = new SongDTO("Mena Massoud, Naomi Scott", "A Whole New World",  "C:\\music_v2\\Mena Massoud, Naomi Scott - A Whole New World.mp3");
	SongDTO m9 = new SongDTO("Rain", "깡", "C:\\music_v2\\Rain - 깡.mp3");
	SongDTO m10 = new SongDTO("SHINee", "Ring Ding Dong",  "C:\\music_v2\\SHINee - Ring Ding Dong.mp3");
	
	//노래(m1~m10) 목록 ->가변배열로 만들기
	ArrayList<SongDTO> list = new ArrayList<SongDTO>();
	
	
	public SongDAO() {
		list.add(m1);
		list.add(m2);
		list.add(m3);
		list.add(m4);
		list.add(m5);
		list.add(m6);
		list.add(m7);
		list.add(m8);
		list.add(m9);
		list.add(m10);
	}
	
	
	//MP3Plyer 객체 생성
	MP3Player mp3 = new MP3Player();
	
	
	//10개 곡 중 랜덤번호 뽑기
	int n = ran.nextInt(10);
	
	// 매개변수 SongDTO 타입(제목, 가수, 경로주소) 넣으면 노래재생 되는 메소드
	public void musicPlay(SongDTO songDto) {
		
		//10개 중 랜덤으로 재생 
		//10개 곡 중 랜덤번호 뽑기
		mp3.play(list.get(n).getPath());
		

	}
	
	//포기 시 정답 출력 메소드
	public void answerOpen(SongDTO songDto) {
		System.out.println("포기하시다니 아쉽군요~! 정답은 " + list.get(n).getSinger() + "의 " + list.get(n).getTitle() +  " 이었습니다~!");
	}
	
	
	//COIN 효과음 재생
	//soundCoinPlay 메소드
	public void soundCoinPlay() {
		SongDTO soundCoin = new SongDTO("C:\\music_v2\\Coin 1.mp3");
		mp3.play(soundCoin.getPath());
	}

	public void update(int tempoCoin, String id, int scoreFinal) {
		
		int nowScoreFinal = 0;
		
		connection();
		
		// 현재 로그인한 사람의 최종 점수값 먼져 가져오기
		String sql1 = "SELECT SCORE_MAX FROM TB_SONG WHERE ID = ?";
		
		try {
			psmt = conn.prepareStatement(sql1);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				nowScoreFinal = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql2 = "";
		
		if(nowScoreFinal < scoreFinal) {
			// 신기록 달성
			sql2 = "UPDATE TB_SONG SET COIN_CNT = ?, SCORE_MAX = ? WHERE ID = ?";
			
			try {
				psmt = conn.prepareCall(sql2);
				psmt.setInt(1, tempoCoin);
				psmt.setInt(2, scoreFinal);
				psmt.setString(3, id);
				psmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
		}else {
			// 신기록 미달성
			sql2 = "UPDATE TB_SONG SET COIN_CNT = ? WHERE ID = ?";
			
			try {
				psmt = conn.prepareCall(sql2);
				psmt.setInt(1, tempoCoin);
				psmt.setString(2, id);
				psmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
			
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	// 보유 coin 가져오기 (자영 select 메서드 만드는 중)
	public int exist(String id) {
		// TODO Auto-generated method stub
			
		int existingCoin = 0;
			connection();
			String sql = "SELECT COIN_CNT FROM TB_SONG WHERE ID =?";
			
			
			
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				
			
				rs = psmt.executeQuery();
				
				if (rs.next()) {
					existingCoin = rs.getInt(1);
					
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
		
		return existingCoin;
		
	}
	
	
	
	
	
	
	
}
