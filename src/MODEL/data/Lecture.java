package MODEL.data;

import java.util.Scanner;

import MODEL.mgr.Manageable;

public class Lecture implements Manageable, Comparable<Lecture> {
	// 과목번호 학수코드 과목이름 학년 이수구분(학과) 학점 담당교수 교시
	public String lecNum;// 과목번호
	public String name;// 과목이름
	public String grade;// 학년
	public String dept;// 이수구분
	public String point;// 학점
	public String professor;// 담당교수
	public String day;// 요일
	public String time;// 시간
	public String room;// 강의실
	// 교양 과목의 경우 학년 = null

	public void read(Scanner scn) {
		dept = scn.next();
		name = scn.next();
		grade = scn.next(); // 교양 과목의 경우 학년 = null
		if(grade.equals("null"))
			grade = "교양";
		lecNum = scn.next();  
		professor = scn.next();
		point = scn.next();
		day = scn.next();
		time = scn.next();
		room = scn.next();
	}

	public void print() {
		System.out.printf("%s %s %s %s %s %s\n", lecNum, name, professor, day, time, room);
	}

	public boolean compare(String kwd) {
		if (lecNum.equals(kwd))
			return true;
		return false;
	}

	public boolean matches(String kwd) {
		if (compare(kwd))
			return true;
		if (name.contains(kwd))
			return true;
		if (professor.equals(kwd))
			return true;
		// 요일 시간을 붙여 입력한 경우
		String day = kwd.substring(0, 1);
		String time = kwd.substring(1, kwd.length());
		if (this.day.equals(day) && this.time.equals(time))
			return true;
		// 요일 시간을 띄어 입력한 경우
		String[] str = kwd.split(" ", 2);
		if (this.day.equals(str[0]) && this.time.equals(str[1]))
			return true;
		return false;
	}

	public int compareTo(Lecture l) {
		return lecNum.compareTo(l.lecNum);
	}
	
	public String[] getData() {
		//dept 이수구분, name 과목이름 ,grade학년, lecNum 과목번호 ,professor담당교수, point 학점 ,day 요일, time 시간 ,room 강의실
		String[] data = {dept, name, grade, lecNum, professor, point, day+time, room};
		return data;
	}
	public String getlecNum() {
		return lecNum;
	}
	
	@Override
	public String toString() {
		return name;
	}
	public Manageable getSource() {
		return this;
	}
}
