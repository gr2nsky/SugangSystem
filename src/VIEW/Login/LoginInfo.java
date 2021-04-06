package VIEW.Login;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import CONTROLLER.Main;
import MODEL.data.User;

public class LoginInfo extends JPanel{
	
	JLabel lable;
	JButton btn;
	User user;
	
	public LoginInfo() {
		user = Main.getUser();
		
		lable = new JLabel();
		lable.setText("["+ ((user.getAuth()==0) ? "학생" : "관리자") +"] "+user.getId()+"님 안녕하세요");
		lable.setHorizontalAlignment(SwingConstants.RIGHT);
		btn = new JButton("로그아웃");
		btn.setHorizontalAlignment(SwingConstants.RIGHT);
		
		add(lable);
		add(btn);
	}
	
	public JPanel getLoginInfoPanel() {
		return this;
	}
	
}
