package com.kh.student.view;

public class StudentMenu {
	public static void Main() {
		System.out.println("==============================================================================================");
		System.out.print(String.format("%10s","1. 출력"));
		System.out.print(String.format("%10s","2. 입력"));
		System.out.print(String.format("%10s","3. 수정"));
		System.out.print(String.format("%10s","4. 삭제"));
		System.out.print(String.format("%10s","5. 등급적용"));
		System.out.print(String.format("%10s","6. 학생검색"));
		System.out.print(String.format("%10s","7. 휴지통"));
		System.out.println(String.format("%10s","8. 종료"));
		System.out.println("==============================================================================================");
		System.out.println("");
		System.out.print("원하는 작업을 번호로 입력 해 주세요 : ");
	}
	
	public static void Trash() {
		System.out.println("======================================");
		System.out.print(String.format("%10s","1. 출력"));
		System.out.print(String.format("%10s","2. 비우기"));
		System.out.println(String.format("%10s","3. 뒤로"));
		System.out.println("======================================");
		System.out.println("");
		System.out.print("원하는 작업을 번호로 입력 해 주세요 : ");
	}
}
