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
			System.out.println("[1] 게임시작 \t[3] 랭킹 보기 \t[4] 게임시작하기 \t[5] 종료 하기");
			choice = sc.nextInt();
			
			if(choice == 1) {
			
			System.out.println("[1] 로그인 \t[2] 회원가입");
			
			//1번 게임 시작 - 로그인 
			

			int choiceLogin = sc.nextInt();
			
			
			
			if(choiceLogin == 1) {
				System.out.println("로그인 화면");
				System.out.print("ID 입력 : ");
				String id = sc.next();
				System.out.print("PW 입력 :");
				String pw = sc.next();
				
				UserDTO info = controller.login(id,pw);
				
				if(info != null) {
					System.out.println(" 환영합니다 ");
					
					
					
					//로그인 성공 시 코인 투입화면
					while(true) {
						System.out.print("코인을 투입해주세요(코인투입 : 1,게임시작 : 0)  : " );
						int inputCoin;
						inputCoin = sc.nextInt();
						if(inputCoin == 1 && nowCoin<=10) {
							nowCoin++;
							//동전 효과음
							songDao.soundCoinPlay();
							System.out.println("현재 투입 코인 : " + nowCoin);
						}else if(inputCoin == 1 && nowCoin == 10) {
							System.out.println("게임을 시작합니다.");
							break;
						}else if (inputCoin == 0 && nowCoin <= 10 && nowCoin >= 1){
							System.out.println("게임을 시작합니다.");
							break;
						}else if(inputCoin == 0 && nowCoin == 0 ) {
							System.out.println("코인을 투입해주세요.(현재보유코인 : 0)");
						}
						else {
							System.out.println("올바른 코인을 투입해주세요(1 또는 0 입력)");
						}
						
						
						// 코인 입력 화면 벗어나기
						if(nowCoin == 10) {
							System.out.println("게임을 시작합니다.(최대 코인 10개 투입)");
							break;
						}
						

					}

					
					
					
					
					
				}else {
					System.out.println("회원정보를 찾을 수 없습니다. 회원가입을 먼저 진행해주세요.");
					
					
				}
				
			}else if(choiceLogin == 2) {
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
				
			else if(choice == 3) {
				System.out.println("===== 랭킹 보기 =====");
				ArrayList<UserDTO> list = controller.userlist();
				
				for(int i = 0; i < list.size(); i++) {
					System.out.print(list.get(i).getId() + "\t");
					System.out.print(list.get(i).getList_score() + "\t");
					System.out.print(list.get(i).getList_coin() + "\t");
					// 정답률 넣을 자리 아직 변수 못넣음
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
