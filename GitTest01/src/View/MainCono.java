package View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Controller.ConoController;
import Model.SongDAO;
import Model.SongDTO;
import Model.UserDAO;
import Model.UserDTO;
import javazoom.jl.player.MP3Player;

public class MainCono {

	public static void main(String[] args) {
		Random ran = new Random();
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

		// 보유 코인 변수 선언
		int nowCoin = 0;

		UserDTO info = null;

		int tempoCoin = 0; // 최종끝날때 꼭 이 변수에다가 남은 코인값을 넣어주세요
		int ScoreFinal = 0; // 최종끝날때 꼭 이 변수에다가 최종 점수를 넣어주세요

		
		
		
		
		while(true) {
			
			
			System.out.println("    ####    #####    ####    ##   ##            #####    ####    ##   ##    ####    ####    ##   ##    ####             #####   ##   ##   ####    ####### \r\n"
					+ "  ##  ##  ##   ##    ##     ###  ##           ##   ##    ##     ###  ##   ##  ##    ##     ###  ##   ##  ##           ##   ##  ##   ##    ##     #   ##  \r\n"
					+ " ##       ##   ##    ##     #### ##           ##         ##     #### ##  ##         ##     #### ##  ##                ##   ##  ##   ##    ##        ##   \r\n"
					+ " ##       ##   ##    ##     ## ####            #####     ##     ## ####  ##         ##     ## ####  ##                ##   ##  ##   ##    ##       ##    \r\n"
					+ " ##       ##   ##    ##     ##  ###                ##    ##     ##  ###  ##  ###    ##     ##  ###  ##  ###           ##   ##  ##   ##    ##      ##     \r\n"
					+ "  ##  ##  ##   ##    ##     ##   ##           ##   ##    ##     ##   ##   ##  ##    ##     ##   ##   ##  ##           ##  ###  ##   ##    ##     ##    # \r\n"
					+ "   ####    #####    ####    ##   ##            #####    ####    ##   ##    #####   ####    ##   ##    #####            #####    #####    ####    ####### \r\n"
					+ "                                                                                                                          ### \r\n"
					+ "\r\n"
					+ "");
			
			
			
			
			
			
			
			
			
			System.out.println("[1] 게임시작 \t[2] 랭킹 보기 \t[3] 종료 하기");
			choice = sc.nextInt();

			if (choice == 1) { // 게임시작

				System.out.println("[1] 로그인 \t[2] 회원가입");

				// 1번 게임 시작 - 로그인

				choice = sc.nextInt();

				if (choice == 1) {
					System.out.println("로그인 화면");
					System.out.print("ID 입력 : ");
					String id = sc.next();
					System.out.print("PW 입력 :");
					String pw = sc.next();

					info = controller.login(id, pw);

					if (info != null) {
						System.out.println(" 환영합니다 ");

						// 로그인 성공 시 코인 투입 화면
						// 기존 보유 코인(DB에서 가져오기)

						int existingCoin = songDao.exist(info.getId());
						System.out.println("기존 보유 코인 : " + existingCoin + "개");
						int coin = existingCoin + nowCoin;

						
						
						
						System.out.println(" ＼😆へ　 へ😁ヘ　 く😎/ \r\n"
								+ "ヘ / 　　　 ( ヘ　　 　( ヘ \r\n"
								+ "　 >　 　 <　　　　く\r\n"
								+ "");
						
						
						while(true) {System.out.print("코인을 투입해주세요(코인투입 : 1,게임시작 : 0)  : " );
						int inputCoin;
						inputCoin = sc.nextInt();
						if(inputCoin == 1 && coin <= 10) {
							coin++;
							//동전 효과음
							songDao.soundCoinPlay();
							System.out.println("현재 투입 코인 : " + coin + "개");
						}else if(inputCoin == 1 && coin == 10) {
							System.out.println("게임을 시작합니다.");
							break;
						}else if (inputCoin == 0 && coin <= 10 && coin >= 1){
							System.out.println("게임을 시작합니다.");
							break;
						}else if(inputCoin == 0 && coin == 0 ) {
							System.out.println("코인을 투입해주세요.(현재보유코인 : 0)");
						}

						// 문제 출제
						// 노래재생
						else {
							System.out.println("올바른 코인을 투입해주세요(1 또는 0 입력)");
						}
						
						
						// 코인 입력 화면 벗어나기
						if(coin == 10) {
							System.out.println("게임을 시작합니다.(최대 코인 10개 투입)");
							break;
							
							
						}
					}
						
						
						//문제 출제
						//노래재생
						System.out.println("노래재생을 원하시면 start를 입력해주세요");
						String start = sc.next();

						if (start.equals("start")) {
							controller.musicPlay(songDto);

						}
						// 다시듣기(3번입력시)

						
							int[] array = new int[coin];

							for (int i = 0; i < array.length; i++) {
								array[i] = ran.nextInt(10);
								for (int j = 0; j < i; j++) {
									if (array[i] == array[j]) {
										--i;
									}
								}
							}
							for (int i = 0; i < coin; i++) { // 문제 재생
								System.out.print("정답을 입력하세요 : ");
								String answer = sc.next();
								int musicNum = i + 1;
								ArrayList<SongDTO> list = controller.answer(musicNum);
								int cnt = 0;
								if (list != null) { // 문제 정답 비교
									for (int j = 0; j < list.size(); j++) {
										if (answer.equals(list.get(j).getTitle())) {
											list.remove(answer);
											i--;
										}

									}
										
								}
								// 다시 듣기
								System.out.println("[1]다시듣기\t[2]포기\t");
								int num2 = sc.nextInt();
								if (num2 == 1) {
									controller.musicPlay(songDto);
									i--;
								} else if (num2 == 2) {
									// 포기 시 다시 듣기
									controller.musicPlay(songDto);
									// 포기 시 정답 출력
									controller.answerOpen(songDto);
								}
								
								
								
								
							} // for문 중괄호
						

					} else { // 로그인 실패
						System.out.println("회원정보를 찾을 수 없습니다. 회원가입을 먼저 진행해주세요.");

					}

				} else if (choice == 2) {
					System.out.println("회원 등록 화면");
					System.out.print("ID 입력 : ");
					String id = sc.next();
					System.out.print("PW 입력 : ");
					String pw = sc.next();
					System.out.print("유저 닉네임 입력: ");
					String user_name = sc.next();

					dto = new UserDTO(id, pw, user_name);

					int cnt = controller.join(dto);

					if (cnt > 0) {
						System.out.println("회원등록 성공");

					} else {
						System.out.println("회원등록 실패");
					}
				}
			

			else if (choice == 2) { // 랭킹보기
				System.out.println("===== 랭킹 보기 =====");
				ArrayList<UserDTO> list = controller.userlist();

				for (int i = 0; i < list.size(); i++) {
					System.out.print(list.get(i).getList_id() + "\t");
					System.out.print(list.get(i).getList_score() + "\t  ");
					System.out.print(list.get(i).getList_coin() + "\t  ");
					double play = (double) list.get(i).getList_play();
					double correct = (double) list.get(i).getList_correct();
					double result = (double) correct / play * 100;
					System.out.print(result);
					System.out.println("%");

					
					
					
					
				}
			

			}

		
		
	
		
		System.out.println(" \r\n"
				+ "     `   `                                                                  ```                               `                      `                                       ``` `                       ` `  `   \r\n"
				+ "             ````  `                                                                ``##``                           `##`                  ``` `                                    ### `                        ``##`    \r\n"
				+ "             ``###                  ```                           `##########``       ##``          ``####            ###`                ``##``             ``  ``                 ### `    `              `     `##`    \r\n"
				+ "              ###``                 `####################`       ` ###########        ##`            `###`            ###`                `###`              ``##``                 ###       ##############      `##``   \r\n"
				+ "            ``####`                 `  `````````````` ###`         `         ``       ##`            `##``   ``##########               `#######`` `          `##``                 ###       ##` ` `````` ``     `##``   \r\n"
				+ "       ``  `###`#### ```                             `###`   ` `##################`   ##`        `   #####` ```       ###       `     ####`   `####` ``  `    `##``                 ###       ##`                 `##``   \r\n"
				+ "         `#### `  ####`                               ###     ``````````````````````  ##`          `### ###`   `#########       ``######`        `######`     `##``                 ###       ##`                 `##``   \r\n"
				+ "    `######``    `  `######`               `  `       ###`           ` ```    `       ########   `####`  `###` `#########       `     `           `   ` `     `##``                 ###       ##`                 `#######\r\n"
				+ "     `````             ````                `##`       ##``       ``###########        ########``####`      `####`   ``###     ``````````````````````````````  `##``                 ###       ##`                 `#######\r\n"
				+ "    `  `                `  `               `##`       ##``        ###  ` `  ###`      ##`       `` ` `    ` `   `  `` ###     ##############################  `##``                 ###       ##`                 `##``   \r\n"
				+ " `#############################            `##`      `##`        ###``      `###      ##`              `  `     `   `           `   `                `        `##``          ` `` ` ###       ##`                 `##``   \r\n"
				+ " ```         ``##```           `           `##`     `###        ``##`     ` `###      ##`               ###``    `###`          ` `##`              ###``     `##``            ```#####       ##`            ````####``   \r\n"
				+ "              `##`                         `##`       ``        ``####``  ``###`      ##`              `##      ``###`            `##`              ###  `    `###################``###      `###################``##``   \r\n"
				+ "              `##`              ```        `##`              ` ``  `#########`        ##`           ` `####``````####`  ``        `####################                             ###                           `##``   \r\n"
				+ "              `##`              `#############################    ``  ` `  `` `       ##`            `##``###`` ### ###``         `##``             ###                             ###                           `##``   \r\n"
				+ "              `##`               ` `                                                  ##`       ` ``###` ```######   `###` `      `##`              ###                             ###                           `##``   \r\n"
				+ "              `##```                                                                ` ##`        ``##`    ```###       `##```     `####################` `                          ### `                       ```##`    \r\n"
				+ "");
		
	
		System.out.println(" .-..-.\r\n"
				+ "`·.·´Thank you♡\r\n"
				+ "●/\r\n"
				+ "/▌\r\n"
				+ " !!");
	}else if (choice == 3) { // 게임종료
			break;
	
		}
		sc.close();

		if (info != null) {
			songDao.update(tempoCoin, info.getId(), ScoreFinal);
				}

	}
}
}