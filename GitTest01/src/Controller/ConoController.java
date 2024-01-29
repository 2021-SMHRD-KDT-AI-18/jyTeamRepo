package Controller;

import java.util.ArrayList;

import Model.UserDAO;
import Model.UserDTO;

public class ConoController {

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

}
