package Model;

public class UserDTO {
	
	// 필드
	private String id;
	private String pw;
	private String user_nm;
	
	public UserDTO(String id, String pw, String user_nm) {
		this.id = id;
		this.pw = pw;
		this.user_nm = user_nm;
	}
	public UserDTO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

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
