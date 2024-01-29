package View;

import java.util.ArrayList;
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
		MP3Player mp3 = new MP3Player();
		UserDAO dao = null;
		UserDTO dto = null;
		SongDTO songDto = new SongDTO();
		SongDAO songDao = new SongDAO();
		int choice = 0;
		ConoController controller = new ConoController();
		
		//보유 코인 변수 선언
		int nowCoin = 0;
		
		while(true) {
			System.out.println("[1] 로그인 \t[2] 회원가입 \t[3] 랭킹 보기 \t[4] 게임시작하기 \t[5] 종료 하기");
			choice = sc.nextInt();
			if(choice == 1) {
				System.out.println("로그인 화면");
				System.out.print("ID 입력 : ");
				String id = sc.next();
				System.out.print("PW 입력 :");
				String pw = sc.next();
				
				UserDTO info = controller.login(id,pw);
				
				if(info != null) {
					System.out.println(" 환영합니다 ");
					// 이부분에 문제 출제 넣어야함
				}else {
					System.out.println("회원정보를 찾을 수 없습니다. 회원가입을 먼저 진행해주세요.");
					
					
				}
				
			}else if(choice == 2) {
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
				
			}else if(choice == 3) {
				System.out.println("===== 랭킹 보기 =====");
				ArrayList<UserDTO> list = controller.userlist();
				
				for(int i = 0; i < list.size(); i++) {
					System.out.print(list.get(i).getId() + "\t");
					System.out.print(list.get(i).getList_score() + "\t");
					System.out.print(list.get(i).getList_coin() + "\t");
					double play = list.get(i).getList_play();
					double correct = list.get(i).getList_correct();
					double result = correct/play*100;
					System.out.printf("%.2f",result);
					System.out.print("%");
				}
			}	
			
			
		
		
		
		
		
		
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
		
		
		
			else if(choice == 4) {
				System.out.print("코인을 투입해주세요 : " );
				int inputCoin = sc.nextInt();
				
				if(inputCoin == 1) {
					nowCoin++;
					System.out.println("현재 투입 코인 : " + nowCoin);
				}
						
						
				
			}else if(choice == 5) {
				break;
			}
		
	}
		sc.close();
		
	}

}
