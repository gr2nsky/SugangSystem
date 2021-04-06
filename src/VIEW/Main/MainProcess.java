package VIEW.Main;


import CONTROLLER.Main;
import VIEW.Login.LoginView;
 
public class MainProcess{
	
    public LoginView loginView;
    public TestFrm testFrm;
    public Main main;
    
    public MainProcess(Main main){
    	this.main = main;
    }
  
    // 테스트프레임창
    public void showFrameTest(){
        loginView.dispose(); // 로그인창닫기
        
        this.testFrm = new TestFrm(main); // 테스트프레임 오픈
        testFrm.setVisible(true);//활성화
    }
 
}