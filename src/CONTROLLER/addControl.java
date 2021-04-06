package CONTROLLER;

import java.util.Scanner;

import MODEL.data.Lecture;
import MODEL.data.User;
import MODEL.mgr.Manager;

public class addControl {
	
	User u;
	Main main;
	Manager<Lecture> lecMgr;

	public addControl(User u, Manager<Lecture> lecMgr) {
		this.u = u;
		this.lecMgr = lecMgr;
	}
	
	public int selectLec(String lecNo) {
		Lecture selectedLec = lecMgr.find(lecNo);
		// 잘못 입력
		if (selectedLec == null) {
			System.out.println("수강신청 실패: 잘못 선택\n");
			return 0;
		}
		// 이미 수강신청한 과목인 경우
		if (u.hasLecAlready(selectedLec)) {
			System.out.println("수강신청 실패: 이미 수강신청한 과목\n");
			return 1;
		}
		// 과목명이 겹치는 경우
		if (u.hasLecDuplicatedName(selectedLec)) {
			System.out.println("수강신청 실패: 과목이름 동일 과목\n");
			return 2;
		}
		// 요일시간이 겹치는 경우
		if (u.hasLecDuplicatedDaytime(selectedLec)) {
			System.out.println("수강신청 실패: 시간이 겹치는 과목\n");
			return 3;
		}

		u.addLec(selectedLec);
		System.out.println("수강신청 성공");
		u.printLecList();
		return 4;
	}
	
	public void deleteLec(String lecNo) {
		if (!u.hasLec()) {
			System.out.println("수강신청 취소 실패: 수강신청 과목 없음\n");
			return;
		}
		Lecture selectedLec = lecMgr.find(lecNo);
		// 잘못 입력
		if (selectedLec == null) {
			System.out.println("수강신청 취소 실패: 잘못 선택\n");
			return;
		}
		u.delLec(selectedLec);
		System.out.println("수강신청 취소 성공");
		u.printLecList();
	}

}
