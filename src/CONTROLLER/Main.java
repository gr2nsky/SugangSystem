package CONTROLLER;

import java.sql.Connection;
import java.util.*;

import CONTROLLER.LoginController;
import DB.DBMS;
import DB.Querys;
import MODEL.data.*;
import MODEL.mgr.*;
import VIEW.Login.LoginView;
import VIEW.Main.MainProcess;

public class Main {
	public static void main(String[] args) {
		Main m = new Main();
		m.start();
	}
	
    MainProcess main;
    public static User user;
    public addControl ac;
    public static Querys querys;
    
    public static LoginController lc;
	static public Manager<Lecture> lecMgr = new Manager<>();
	Scanner scn = new Scanner(System.in);
	
	public static Connection mysqlConn;
 
	void start() {
		DBMS dbms = new DBMS();
		mysqlConn = dbms.DBA();
		querys = new Querys(mysqlConn);
		
		lecMgr.readAll("lectures.txt", new LecFactory());
		Collections.sort(lecMgr.tList);
		lecMgr.printAll();
		
		user = new User(this);
		lc = new LoginController(user, mysqlConn);
		
		ac = new addControl(user, lecMgr);
		
    	main = new MainProcess(this);
        main.loginView = new LoginView(this, lc); // 로그인창 보이기
        main.loginView.setMain(main); // 로그인창에게 메인 클래스보내기
	}

	class LecFactory implements Factory<Lecture> {
		public Lecture create() {
			return new Lecture();
		}
	}
	
	public Main getMain() {
		return this;
	}
	
	public static User getUser() {
		return user;
	}
}