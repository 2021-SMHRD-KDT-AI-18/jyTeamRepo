package View;

import java.util.Scanner;

import Controller.ConoController;
import Model.SongDAO;
import Model.SongDTO;
import Model.UserDAO;
import Model.UserDTO;
import javazoom.jl.player.MP3Player;

public class MainCono {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		// ------------------- intro ------------------
		
		// mp3 타이틀 노래 재생
		
		// 타이틀 화면
		
		// 로그인 화면
		// 전역변수
		UserDAO dao = null;
		UserDTO dto = null;
		
		ConoController controller = new ConoController();
		while(true) {
			System.out.println("[1] 로그인 [2] 회원가입 [3] 랭킹 보기");
			int choice = sc.nextInt();
			if(choice == 1) {
				System.out.println("로그인 화면");
				System.out.print("ID 입력 : ");
				String id = sc.next();
				System.out.print("PW 입력 :");
				String pw = sc.next();
				
				UserDTO info = controller.login(id,pw);
				
				if(info != null) {
					System.out.println(" 환영합니다 ");
				}
				
			}
			if(choice == 2) {
				System.out.println("회원 등록 화면");
				System.out.print("ID 입력 : ");
				String id = sc.next();
				System.out.print("PW 입력 : ");
				String pw = sc.next();
				System.out.print("유저 닉네임 입력: ");
				String user_name = sc.next();
				
				dto = new UserDTO(id, pw, user_name);
				
				int cnt = controller.join(dto);
				
				if(cnt > 0) {
					System.out.println("회원등록 성공");
				}else {
					System.out.println("회원등록 실패");
				}
			}
			
		}
		//
		
		
		
		MP3Player mp3 = new MP3Player();
		SongDTO songDto = new SongDTO();
		SongDAO songDao = new SongDAO();
		
		
		//문제 출제
		//노래재생
		System.out.println("노래재생을 원하시면 1번을 눌러주세요(시끄러워서 임시로 만들었어요)");
		int num1 = sc.nextInt();
		if(num1==1) {
		songDao.musicPlay(songDto);
		}
		//다시듣기(1번입력시)
		System.out.println("[1]정답입력\t[2]힌트\t[3]다시 듣기\t[4] 포기");
		int num2 = sc.nextInt();
		if(num2 ==3) {
			songDao.musicPlay(songDto);
		}else if(num2 ==4){
			//포기 시 다시 듣기
			songDao.musicPlay(songDto);
			
			//포기 시 정답 출력
			songDao.answerOpen(songDto);
		}
		
		
		
		
		
		
		
	}

}
