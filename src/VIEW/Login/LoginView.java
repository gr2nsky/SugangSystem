package VIEW.Login;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import CONTROLLER.LoginController;
import CONTROLLER.Main;
import VIEW.Main.MainProcess;
import VIEW.Main.TestFrm;
 
public class LoginView extends JFrame{
	Main controller;
	LoginController loginCon;
	
    private MainProcess main;
    private TestFrm testFrm;
   
    private JButton btnLogin;
    private JButton btnInit;
    private JPasswordField passText;
    private JTextField userText;
 
    public LoginView(Main controller, LoginController loginCon) {
    	System.out.println("로그인뷰 실행");
    	this.controller = controller;
    	this.loginCon = loginCon;
        // setting
        setTitle("login");
        setSize(280, 150);
        setResizable(false);
        setLocation(800, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       
        // panel
        JPanel panel = new JPanel();
        placeLoginPanel(panel);
       
       
        // add
        add(panel);
       
        // visiible
        setVisible(true);
    }
   
    public void placeLoginPanel(JPanel panel){
        panel.setLayout(null);     
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);
       
        JLabel passLabel = new JLabel("Pass");
        passLabel.setBounds(10, 40, 80, 25);
        panel.add(passLabel);
       
        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);
       
        passText = new JPasswordField(20);
        passText.setBounds(100, 40, 160, 25);
        panel.add(passText);
        passText.addActionListener(new ActionListener() {          
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();        
            }
        });
       
        btnInit = new JButton("Sign in");
        btnInit.setBounds(10, 80, 100, 25);
        panel.add(btnInit);
        btnInit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userText.setText("");
                passText.setText("");
            }
        });
       
        btnLogin = new JButton("Login");
        btnLogin.setBounds(160, 80, 100, 25);
        panel.add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();
            }
        });
    }
    
   //로그인 성공여뷰
    public void isLoginCheck(){
    	String inputId = userText.getText();
    	char[] pw = passText.getPassword();
    	String inputPw = "";
    	System.out.println("isLoginChecked..." + inputId + "," + inputPw);
    	for(char c : pw) {
    		Character.toString(c);
    		inputPw += (pw.equals("")) ? ""+c+"" : ""+c+"";
    	}
    	System.out.println("isLoginChecked..." + inputId + "," + inputPw);
        if(loginCon.LoginFlow(inputId, inputPw)){
            JOptionPane.showMessageDialog(null, "Success");
            controller.user.setId(inputId);
            controller.user.loadLec();
            main.showFrameTest(); // 메인창 메소드를 이용해 창뛰우기
        }else
            JOptionPane.showMessageDialog(null, "Faild");
    }
 
    // mainProcess와 연동
    public void setMain(MainProcess main) {
        this.main = main;
    }
 
}