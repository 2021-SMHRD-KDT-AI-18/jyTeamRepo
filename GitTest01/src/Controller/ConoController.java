package Controller;

import java.util.ArrayList;

import Model.SongDAO;
import Model.SongDTO;
import Model.UserDAO;
import Model.UserDTO;

public class ConoController {
		private SongDAO s_dao = new SongDAO();
		private  UserDAO dao = new UserDAO();

	public UserDTO login(String id, String pw) {
		return dao.login(id,pw);
	}

	public int join(UserDTO dto) {
		return dao.join(dto);
	}

	public ArrayList<UserDTO> userlist(){
		return dao.userlist();
	}
	
	
	//노래재생 
	SongDAO songDao = new SongDAO();
	public void  musicPlay(SongDTO songDto) {
		
		songDao.musicPlay(songDto);
	}
	
	//문제 포기 시 정답 출력 메소드
	public void answerOpen(SongDTO songDto) {
		songDao.answerOpen(songDto);
	}
	
	//효과음재생
	public void soundCoinPlay() {
		songDao.soundCoinPlay();
	}
	
	public ArrayList<SongDTO> answer(int abc){
		return s_dao.answer(abc);
		}
	

}
