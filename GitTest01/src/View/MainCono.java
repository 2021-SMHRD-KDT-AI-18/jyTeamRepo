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
		
		
		while(true) {
			System.out.println("[1] 로그인 [2] 회원가입 [3] 랭킹 보기");
			int choice = sc.nextInt();
			if(choice == 1) {
				System.out.println("로그인 화면");
				System.out.print("ID 입력 : ");
				String id = sc.next();
				System.out.print("PW 입력 :");
				String pw = sc.next();
				
				UserDTO info = ConoController.login(dto);
				
			}
		}
		
		
		//
		
	}

}
