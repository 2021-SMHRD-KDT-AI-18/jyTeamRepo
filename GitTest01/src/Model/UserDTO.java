package Model;

public class UserDTO {
	
	// 유저 정보 필드
	private String id;
	private String pw;
	private String user_nm;
	
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
	
	

}
