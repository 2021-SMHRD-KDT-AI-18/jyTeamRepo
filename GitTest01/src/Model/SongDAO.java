package Model;

import java.util.ArrayList;
import java.util.Random;

import javazoom.jl.player.MP3Player;

public class SongDAO {
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
	
}
