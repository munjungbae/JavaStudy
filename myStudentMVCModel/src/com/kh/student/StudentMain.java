package com.kh.student;

import java.sql.SQLException;
import java.util.Scanner;

import com.kh.student.controller.StudentManager;
import com.kh.student.view.StudentMenu;
import com.kh.student.view.StudentMenuNo;

public class StudentMain {
	public static Scanner sc = new Scanner(System.in);


	public static void main(String[] args) throws SQLException {
		boolean exitFlag = false;
		while (!exitFlag) {
			StudentMenu.Main();
			int number = Integer.parseInt(sc.nextLine());
			switch (number) {
			
			case StudentMenuNo.PRINT:
				System.out.println("");
				StudentManager.studentPrint();
				break;
				
				
			case StudentMenuNo.INSERT:
				StudentManager.studentInsert();
				break;
				
				
			case StudentMenuNo.UPDATE:
				StudentManager.studentUpdate();
				break;
				
				
			case StudentMenuNo.DELETE:
				StudentManager.studentDelete();
				break;
				
				
			case StudentMenuNo.GRADE:
				StudentManager.studentGrade();;
				break;
				
				
			case StudentMenuNo.SEARCH:
				StudentManager.studentSelect();
				break;
				
				
			case StudentMenuNo.TRASH:
				System.out.println("");
				boolean tExitFlag = false;
				while(!tExitFlag) {
				StudentMenu.Trash();
					int tNumber = Integer.parseInt(sc.nextLine());
					switch (tNumber) {
					case StudentMenuNo.PRINT:
						StudentManager.studentTrashPrint();
						break;
					case StudentMenuNo.TDELETE:
						StudentManager.studentTrashDelete();
						break;
					case StudentMenuNo.TEXIT:
						tExitFlag = true;
						break;
					}
				}
				break;
				
				
			case StudentMenuNo.EXIT:
				exitFlag = true;
				break;
			}
		}
		
		System.out.println("종료합니다.");
		
	}

}