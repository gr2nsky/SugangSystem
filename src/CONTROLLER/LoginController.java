package CONTROLLER;

import java.sql.Connection;

import DB.Querys;
import MODEL.data.User;

public class LoginController {
	
	User user;
	Connection conn;
	Querys query;
	
	LoginController(User user, Connection conn){
		this.user = user;
		this.conn = conn;
		query = new Querys(conn);
	}
	
	public boolean LoginFlow(String uesrId, String pw) {
		
		if(query.checkId(uesrId)) { //존재하지 않는 id
			System.out.println(query.checkId(uesrId));
			SignIn(uesrId, pw); //바로 회원가입
			LoginSuccess(uesrId); //로그인 성공
			return true;
		}
		else //존재하는 id일경유
			if(query.loginAccount(uesrId, pw)) { //login 정보가 정확한 경우
				LoginSuccess(uesrId); //로그인 성공
				return true;
			}
			//로그인 실패의 경우 팝업으로 경고창을 띄운다.
			else 
				return false;
	}
	
	public void LoginSuccess(String id) {
		user.setId(id);
		}
	
	public void SignIn(String id, String pw) {
		query.insertAccount(id, pw);
	}
	
	public User getUser() {
		return user;
	}
	
}
