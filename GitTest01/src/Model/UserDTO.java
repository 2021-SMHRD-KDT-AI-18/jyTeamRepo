package Model;

public class UserDTO {
	
	// 유저 정보 필드
	private String id;
	private String pw;
	private String user_nm;
	
	// 유저 랭킹 필드
	private String list_id;
	private int list_score;
	private int list_coin;
	private int list_play;
	private int list_correct;
	

	// 기본 생성자
	public UserDTO() {
		
	}
	
	// 유저 정보 생성자
	public UserDTO(String id, String pw, String user_nm) {
		super();
		this.id = id;
		this.pw = pw;
		this.user_nm = user_nm;
	}
	public UserDTO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

		

	public UserDTO(String list_id, int list_score, int list_coin, int list_play, int list_correct) {
	}

	

	// 유저 정보 getter 메소드
	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getUser_nm() {
		return user_nm;
		
	}
	public String getList_id() {
		return list_id;
	}

	public int getList_score() {
		return list_score;
	}

	public int getList_coin() {
		return list_coin;
	}
	public int getList_play() {
		return list_play;
	}

	public int getList_correct() {
		return list_correct;
	}

}
