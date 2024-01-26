package View;

import java.util.Scanner;

import Controller.ConoController;
import Model.UserDAO;
import Model.UserDTO;

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
	}

}
