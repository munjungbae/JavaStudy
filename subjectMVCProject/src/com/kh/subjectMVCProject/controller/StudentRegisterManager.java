package com.kh.subjectMVCProject.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.subjectMVCProject.model.StudentVO;
import com.kh.subjectMVCProject.model.SubjectVO;

public class StudentRegisterManager {
	public static Scanner sc= new Scanner(System.in);
	
	//전체 학생 리스트 출력요청
	public static void SelectManager() throws SQLException {
		ArrayList<StudentVO> studentList = new ArrayList<StudentVO>();
		
		studentList = StudentDAO.studentSelect();
		
		printStudentList(studentList);
	}

	//입력할 학생의 값을 받는 메서드  -> 이후 StudentDAO.studentInsert 메서드로 전달하여 오라클에 전달한 뒤
	//true,false 값을 반환하여 성공,실패 여부 출력
	public static void insertManager() throws SQLException {
		
		SubjectDAO subjectDao = new SubjectDAO();
		StudentDAO studentDao = new StudentDAO();
		StudentVO svo = new StudentVO();

//		String sd_num; // 학번
//
//		
//		String s_num; // 학과번호
//		String sd_birthday; // 생년월일
//		String sd_phone; // 핸드폰번호
//		String sd_address; // 주소
//		String sd_email; // 이메일

		 // 아이디 체크
//		String year; // 년도

		System.out.println("학생 정보 입력");
		System.out.print("성명 >> ");
		String name = sc.nextLine();
		String id = null;
		do {
			System.out.print("아이디(8자 이상 12자 이내) : ");
			id = sc.nextLine();
			boolean idCheck = false;
			idCheck = sd.StudentIdCheck(id);
			if (idCheck == false) {
				break;
			}
			System.out.println("중복된 아이디입니다. 다시 입력하세요");
		} while (true);

		System.out.print("비밀번호(12자 이내) : ");
		String passwd = sc.nextLine();
		
		//학과정보 출력
		ArrayList<SubjectVO> subjectList = subjectDao.subjectSelect();
		SubjectRegisterManager.printSubjectList(subjectList);

		System.out.print("학과번호 : ");
		String s_num = sc.nextLine();

		// 학생 번호는 8자리로 생성한다. (연도2자리+학과2자리+일련번호 - 예로06010001) 
		SimpleDateFormat sdf = new SimpleDateFormat("yy");
		String year = sdf.format(new Date());
		String num = year + s_num +  studentDao.getStudentCount(num);
		String num = year + s_num + "0001";

		System.out.print("생년월일(8자리: 19900829) : ");
		String birthday = sc.nextLine();
		System.out.print("전화번호 : 010-2971-4011");
		String phone = sc.nextLine();
		System.out.print("도로명 주소 : ");
		String address = sc.nextLine();
		System.out.print("이메일   : ");
		String email = sc.nextLine();
		
		StudentVO studentVO = new StudentVO(0, num, name, id, passwd, s_num, birthday, phone, address, email, null);

		boolean successFlag = StudentDAO.studentInsert(studentVO);
		
		if (successFlag == false) {
			System.out.println("입력처리 실패");
		}
	
		System.out.println();
		System.out.println("등록 학생 정보");
		subjectDao.getStudentSelect(num);
//		sd.getStudent(svo.getSd_id(), svo.getSd_passwd());
		System.out.println();
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
