package com.kh.student.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.student.model.StudentVO;

public class StudentManager {
	public static Scanner sc = new Scanner(System.in);

	
	//학생 모든정보 출력
	public static void studentPrint() throws SQLException {
		ArrayList<StudentVO> studentList = new ArrayList<StudentVO>();
		
		studentList = StudentDAO.studentPrint();
		
		studentListPrint(studentList);
		System.out.println("");
		System.out.println("메인화면으로 돌아갑니다.");
		System.out.println("");
	}

	//입력된 학생 정보 테이블 입력
	public static void studentInsert() throws SQLException {
		System.out.println("");
		System.out.print("입력하고자 하는 국어 점수을 입력해 주세요 : ");
		int kor = Integer.parseInt(sc.nextLine());
		System.out.print("입력하고자 하는 영어 점수을 입력해 주세요 : ");
		int eng = Integer.parseInt(sc.nextLine());
		System.out.print("입력하고자 하는 수학 점수을 입력해 주세요 : ");
		int math = Integer.parseInt(sc.nextLine());

		StudentVO stu = new StudentVO(kor, eng, math);
		
		boolean insertFlag = StudentDAO.studentInsert(stu);
		
		if (insertFlag == true) {
			System.out.println("");
			System.out.println("입력이 완료 되었습니다.");
		}
		else {
			System.out.println("");
			System.out.println("입력에 실패하였습니다.");
		}
		System.out.println("");
		System.out.println("메인 화면으로 돌아갑니다.");
		System.out.println("");
	}
	
	//해당되는 학생의 정보 수정
	public static void studentUpdate() throws SQLException {

		System.out.println("");
		System.out.print("수정을 하는 하는 학번을 입력해 주세요 : ");
		int no = Integer.parseInt(sc.nextLine());
		System.out.println("");
		System.out.print("수정하고자 하는 국어 점수을 입력해 주세요 : ");
		int kor = Integer.parseInt(sc.nextLine());
		System.out.print("수정하고자 하는 영어 점수을 입력해 주세요 : ");
		int eng = Integer.parseInt(sc.nextLine());
		System.out.print("수정하고자 하는 수학 점수을 입력해 주세요 : ");
		int math = Integer.parseInt(sc.nextLine());

		StudentVO stu = new StudentVO(no, kor, eng, math);
		
		boolean updateFlag = StudentDAO.studentUpdate(stu);
		
		if (updateFlag == true) {
			System.out.println("");
			System.out.println("입력이 완료 되었습니다.");
		}
		else {
			System.out.println("");
			System.out.println("입력에 실패하였습니다.");
		}
		System.out.println("");
		System.out.println("메인 화면으로 돌아갑니다.");
		System.out.println("");
		
	}
	
	//해당되는 학생 정보 삭ㅔ
	public static void studentDelete() throws SQLException {
		System.out.println("");
		System.out.print("삭제하고자 하는 학번을 입력해 주세요 : ");
		int no = Integer.parseInt(sc.nextLine());
		
		StudentVO stu = new StudentVO();
		stu.setNo(no);
		
		boolean deleteFlag = StudentDAO.studentDelete(stu);
		
		if (deleteFlag == true) {
			System.out.println("");
			System.out.println("입력이 완료 되었습니다.");
		}
		else {
			System.out.println("");
			System.out.println("입력에 실패하였습니다.");
		}
		System.out.println("");
		System.out.println("메인 화면으로 돌아갑니다.");
		System.out.println("");
	}
	
	//학생의 평균을 바탕으로 해당하는 등급 설정
	public static void studentGrade() throws SQLException {
		System.out.println("");
		System.out.print("조회 할 학생의 학번을 입력 해 주세요 : ");
		int no = Integer.parseInt(sc.nextLine());
		
		StudentVO stu = new StudentVO();
		stu.setNo(no);
		
		String message = StudentDAO.studentGrade(stu);
		
		
		if(message.length() != 0 ) {
			System.out.println("");
			System.out.println(message);
		}else {
			System.out.println("");
			System.out.println("등급 산정에 실패하였습니다.");
		}
		System.out.println("");
		System.out.println("메인 화면으로 돌아갑니다.");
		System.out.println("");
	}
	
	//해당되는 학생의 점수 상세정보 출력
	public static void studentSelect() throws SQLException {
		System.out.println("");
		System.out.print("조회 할 학생의 학번을 입력 해 주세요 : ");
		int no = Integer.parseInt(sc.nextLine());
		
		StudentVO stu = new StudentVO();
		stu.setNo(no);
		

		String message = StudentDAO.studentSelect(stu);
		
		if(message.length() != 0 ) {
			System.out.println("");
			System.out.println(message);
		}else {
			System.out.println("");
			System.out.println("해당하는 학생이 존재하지 않습니다. 다시 입력 바랍니다.");
		}
		System.out.println("");
		System.out.println("메인 화면으로 돌아갑니다.");
		System.out.println("");
	}
	
	public static void studentTrashPrint() throws SQLException {
		ArrayList<StudentVO> studentTrashList = new ArrayList<StudentVO>();
		
		studentTrashList = StudentDAO.studentTrash();
		System.out.println("");
		studentListPrint(studentTrashList);
		System.out.println("");
		System.out.println("메인 화면으로 돌아갑니다.");
		System.out.println("");
	}
	
	public static void studentTrashDelete() throws SQLException {
		
		boolean deleteFlag = StudentDAO.trashDelete();
		
		if (deleteFlag == true) {
			System.out.println("");
			System.out.println("삭제가 완료 되었습니다.");
		}
		else {
			System.out.println("");
			System.out.println("비어있는 상태입니다.");
		}
		System.out.println("");
		System.out.println("메인 화면으로 돌아갑니다.");
		System.out.println("");
	}
	
	
	//출력반복문
	private static void studentListPrint(ArrayList<StudentVO> studentList) {
		for( StudentVO svo : studentList ) {
			System.out.println(svo.toString());
		}
		
	}
}

//wkqk 데이터베이스 시스템개론 운영체계 ...
//on delete set null ( cascade 해당 외래키 삭제시 참조하는 그부분을 널값으로 조정)