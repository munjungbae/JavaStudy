package com.kh.subjectMVCProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.subjectMVCProject.model.StudentVO;
import com.kh.subjectMVCProject.model.SubjectVO;

public class SubjectRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	// 전체 학생 리스트 출력요청
	public static void SelectManager() throws SQLException {
		ArrayList<SubjectVO> subjectList = new ArrayList<SubjectVO>();
		SubjectDAO subDAO = new SubjectDAO();
		subjectList = subDAO.subjectSelect();
		if (subjectList == null) {
			System.out.println("데이터가 존재하지 않습니다");
			return;
		}
		printSubjectList(subjectList);
	}

	// 입력할 학생의 값을 받는 메서드 -> 이후 StudentDAO.studentInsert 메서드로 전달하여 오라클에 전달한 뒤
	// true,false 값을 반환하여 성공,실패 여부 출력
	public static void insertManager() throws SQLException {

		SubjectDAO sd = new SubjectDAO();

		String num; // 학과 번호
		String name; // 학과명

		System.out.println("학과 전체 리스트");
//		sd.getSubjectTotalList();
		System.out.println();

		System.out.println("학과 정보 입력 ( 학과번호 : 01, 02, 03 ... ), ( 학과명  : 01(IT학과), 02(정보학과), 03(보안) ... )");
		System.out.print("학과번호 : ");
		num = sc.nextLine();
		System.out.print("학과명  : ");
		name = sc.nextLine();

		SubjectVO svo = new SubjectVO(num, name);

		boolean successFlag = sd.subjectInsert(svo);
		
		if(successFlag == false) {
			System.out.println("입력 처리 실패");
			return;
		} 
		System.out.println();
		System.out.println("학과 전체 리스트");
		ArrayList<SubjectVO> subjectList = sd.subjectSelect();
		if (subjectList == null) {
			System.out.println("학과 정보가 없습니다.");
			return;
		}
		printSubjectList(subjectList);
		
		
	}
	
	// 수정할 학생의 값을 받는 메서드 -> 이후 StudentDAO.studentUpdate 메서드로 전달하여 오라클에 전달한 뒤
	// true,false 값을 반환하여 성공,실패 여부 출력
	public static void updateManager() throws SQLException {

		System.out.print("수정할 학생의 번호를 입력하세요: ");
		int no = Integer.parseInt(sc.nextLine());
		System.out.print("새로운 이름을 입력하세요: ");
		String name = sc.nextLine();
		System.out.print("새로운 국어 점수를 입력하세요: ");
		int kor = Integer.parseInt(sc.nextLine());
		System.out.print("새로운 영어 점수를 입력하세요: ");
		int eng = Integer.parseInt(sc.nextLine());
		System.out.print("새로운 수학 점수를 입력하세요: ");
		int mat = Integer.parseInt(sc.nextLine());

		StudentVO studentVO = new StudentVO();
		boolean successFlag = StudentDAO.studentUpdate(studentVO);

		if (successFlag == true) {
			System.out.println("수정 성공 ");
		} else {
			System.out.println("수정 실패 ");
		}
	}

	// 삭제할 학생의 값을 받는 메서드 -> 이후 StudentDAO.studentDelete 메서드로 전달하여 오라클에 전달한 뒤
	// true,false 값을 반환하여 성공,실패 여부 출력
	public static void deleteManager() throws SQLException {

		System.out.print("삭제할 학생 번호를 입력하세요: ");
		int no = Integer.parseInt(sc.nextLine());

		StudentVO studentvo = new StudentVO();
		studentvo.setNo(no);

		boolean successFlag = StudentDAO.studentDelete(studentvo);

		if (successFlag == true) {
			System.out.println("삭제 성공 ");
		} else {
			System.out.println("삭제 실패 ");
		}
	}

	public static void SortManager() throws SQLException {

		ArrayList<SubjectVO> subjecttList = new ArrayList<SubjectVO>();
		subjecttList = SubjectDAO.subjectSort();
		printSubjectList(subjecttList);
	}

	// 들어온 리스트를 반복해주는 메서드
	public static void printSubjectList(ArrayList<SubjectVO> subjectList) {
		System.out.println("===================");
		for (SubjectVO svo : subjectList) {
			System.out.println(svo.toString());
		}
		System.out.println("===================");
	}

}
