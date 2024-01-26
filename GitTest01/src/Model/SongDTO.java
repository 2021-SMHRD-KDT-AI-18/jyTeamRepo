package Model;

public class SongDTO {
	//곡정보 필드
	private String title = ""; 
	private String singer = ""; 
	private String path = ""; 
	
	
	//곡정보  getter method
	public String getTitle() {
		return title;
	}
	public String getSinger() {
		return singer;
	}
	public String getPath() {
		return path;
	}
	
	
	// 곡정보 생성자
	public SongDTO (String singer, String title, String path) {
		this.singer = singer;
		this.title = title;
		this.path = path;
	}	
	
	// 기본생성자
	public SongDTO () {
		
	}
}
