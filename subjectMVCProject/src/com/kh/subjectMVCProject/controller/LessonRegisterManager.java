package com.kh.subjectMVCProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.subjectMVCProject.model.StudentVO;

public class LessonRegisterManager {
	public static Scanner sc= new Scanner(System.in);
	
	//전체 학생 리스트 출력요청
	public static void totalSelectManager() throws SQLException {
		ArrayList<StudentVO> studentList = new ArrayList<StudentVO>();
		
		studentList = StudentDAO.studentSelect();
		
		printStudentList(studentList);
	}

	//입력할 학생의 값을 받는 메서드  -> 이후 StudentDAO.studentInsert 메서드로 전달하여 오라클에 전달한 뒤
	//true,false 값을 반환하여 성공,실패 여부 출력
	public static void insertManager() throws SQLException {
		
		System.out.print("학생 이름을 입력하세요: ");
		String name = sc.nextLine();
		System.out.print("국어 점수를 입력하세요: ");
		int kor = Integer.parseInt(sc.nextLine());
		System.out.print("영어 점수를 입력하세요: ");
		int eng = Integer.parseInt(sc.nextLine());
		System.out.print("수학 점수를 입력하세요: ");
		int mat = Integer.parseInt(sc.nextLine());
		
		StudentVO svo = new StudentVO();
		boolean successFlag = StudentDAO.studentInsert(svo);
		
		if (successFlag == true) {
			System.out.println("입력 처리 성공 ");
		} else {
			System.out.println("입력 처리 실패 ");
		}
	}
	
	//수정할 학생의 값을 받는 메서드  -> 이후 StudentDAO.studentUpdate 메서드로 전달하여 오라클에 전달한 뒤
	//true,false 값을 반환하여 성공,실패 여부 출력
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
	
	//삭제할 학생의 값을 받는 메서드  -> 이후 StudentDAO.studentDelete 메서드로 전달하여 오라클에 전달한 뒤
	//true,false 값을 반환하여 성공,실패 여부 출력
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
		
		ArrayList<StudentVO> studentList = new ArrayList<StudentVO>();
		studentList = StudentDAO.studentSort();
		printStudentList(studentList);
	}
	
	
	
	//들어온 리스트를 반복해주는 메서드
	private static void printStudentList(ArrayList<StudentVO> studentList) {
		System.out.println("===================");
		for( StudentVO sv : studentList ) {
			System.out.println(sv.toString());
		}
		System.out.println("===================");
	}
	
}
