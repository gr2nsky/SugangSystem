package MODEL.data;

import java.util.*;

import CONTROLLER.Main;
import DB.Querys;
import MODEL.mgr.Manageable;

public class User implements Manageable {
	Main main;
	ArrayList<Lecture> mainLec;
	String id;
	int auth; // 0:학생 1:관리자
	public static ArrayList<Lecture> lecList = new ArrayList<Lecture>();
	Querys querys;
	
	public User(Main main) {
		this.main = main;
		mainLec = main.lecMgr.getLecArr();
	}

	public boolean hasLec() {
		if (lecList.isEmpty())
			return false;
		return true;
	}

	public boolean hasLecAlready(Lecture lec) {
		if (lecList.contains(lec))
			return true;
		return false;
	}

	public boolean hasLecDuplicatedName(Lecture lec) {
		for (Lecture lecture : lecList) {
			if (lec.name.equals(lecture.name))
				return true;
		}
		return false;
	}

	public boolean hasLecDuplicatedDaytime(Lecture lec) {
		String[] time = new String[4];
		// "678"의 경우 {"6", "7", "8", "0"}
		for (int i = 0; i < lec.time.length(); i++) {
			time[i] = lec.time.substring(i, i + 1);
		}
		for (int i = lec.time.length(); i < 4; i++) {
			time[i] = "0";
		}

		for (Lecture l : lecList) {
			if (lec.day.equals(l.day)) {
				if (l.time.contains(time[0]))
					return true;
				if (l.time.contains(time[1]))
					return true;
				if (l.time.contains(time[2]))
					return true;
				if (l.time.contains(time[3]))
					return true;
			}
		}
		return false;
	}

	public void addLec(Lecture lec) {
		lecList.add(lec);
	}

	public void delLec(Lecture lec) {
		lecList.remove(lec);
	}

	public void print() {
		System.out.printf("id");
	}

	public void printLecList() {
		for (Lecture lec : lecList) {
			System.out.printf("\t%s %s %s %s\n", lec.name, lec.day, lec.time, lec.room);
		}
		System.out.println();
	}

	public boolean compare(String kwd) {
		if (kwd.equals(String.valueOf(id)))
			return true;
		return false;
	}

	public boolean matches(String kwd) {
		if (compare(kwd))
			return true;
		return false;
	}

	public Manageable getSource() {
		return this;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public void setAtuh(int auth) {
		this.auth = auth;
	}
	public int getAuth() {
		return auth;
	}
	
	public void loadLec() {
		String lecComma = Main.querys.loadLecList(id);
		if(lecComma.equals("0")) {
			return;
		}
		String[] lec = lecComma.split(",");
		for(String s : lec) {
			System.out.println("이미 있던 수업 이름 : "+s);
		}
		setlecList(lec);
	}
	
	public void setlecList(String[] list) {
		for(Lecture l : mainLec) {
			for(String s : list) {
				if(l.compare(s))
					lecList.add(l);
			}
		}
	}
	
	public static ArrayList<Lecture> getManager() {
		return lecList;
	}
	
	public void removeAt(int index) {
		Lecture l = lecList.get(index);
		System.out.println("user : "+l.name+" 가 삭제됩니다.");
		lecList.remove(l);
		System.out.println("현재 목록");
		for(Lecture lecc : lecList) {
			System.out.print(lecc+" ");
		}
		System.out.println();
	}

	@Override
	public void read(Scanner scan) {
		
	}
}
