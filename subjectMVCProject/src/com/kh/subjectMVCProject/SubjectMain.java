package com.kh.subjectMVCProject;

import java.sql.SQLException;
import java.util.Scanner;

import com.kh.subjectMVCProject.controller.LessonRegisterManager;
import com.kh.subjectMVCProject.controller.StudentRegisterManager;
import com.kh.subjectMVCProject.controller.SubjectRegisterManager;
import com.kh.subjectMVCProject.controller.TraineeRegisterManager;
import com.kh.subjectMVCProject.view.LESSON_CHOICE;
import com.kh.subjectMVCProject.view.MENU_CHOICE;
import com.kh.subjectMVCProject.view.MenuViewer;
import com.kh.subjectMVCProject.view.STUDENT_CHOICE;
import com.kh.subjectMVCProject.view.SUBJECT_CHOICE;
import com.kh.subjectMVCProject.view.TRANIEE_CHOICE;

public class SubjectMain {
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		int  no;
		boolean exitFlag = false;
        while (!exitFlag) {
            try {
            	MenuViewer.mainMenuView();
                no = Integer.parseInt(sc.nextLine());

                switch (no) {
                case MENU_CHOICE.SUBJECT:
                	subjectMenu();
                    break;
                case MENU_CHOICE.STUDENT:
                	studentMenu();
                    break;
                case MENU_CHOICE.LESSON:
                	lessonMenu();
                    break;
                case MENU_CHOICE.TRAINEE:
                	traineeMenu();
                    break;
                case MENU_CHOICE.EXIT:
                    System.out.println("프로그램을 종료합니다.");
                    exitFlag = true;
                    break;
                default:
                    System.out.println("해당 메뉴 번호만 입력하세요.");
                }
            } catch (Exception e) {
                System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
            }
        }
	}
	//학과 정보 메뉴
	private static void subjectMenu() throws SQLException {
        int no;
        // StudentRegisterManager studnetManager = new StudentRegisterManager();
        MenuViewer.subjectMenuView();
        no = Integer.parseInt(sc.nextLine());
        SubjectRegisterManager srm = new SubjectRegisterManager(); 
        switch (no) {
        case SUBJECT_CHOICE.LIST:
            System.out.println("");
            srm.SelectManager();
            break;
        case SUBJECT_CHOICE.INSERT:
            System.out.println("");
            srm.insertManager();
            break;
        case SUBJECT_CHOICE.UPDATE:
            System.out.println("");
            srm.updateManager();
            break;
        case SUBJECT_CHOICE.DELETE:
            System.out.println("");
            srm.deleteManager();
            break;
        case SUBJECT_CHOICE.MAIN:
            return;
        default:
            System.out.println("해당 메뉴 번호만 입력하세요.");
        }
    }
	
	//학생 메뉴
	private static void studentMenu() {
        int no;
        // StudentRegisterManager studnetManager = new StudentRegisterManager();
        MenuViewer.studentMenuView();
        no = Integer.parseInt(sc.nextLine());
        StudentRegisterManager srm = new StudentRegisterManager(); 
        switch (no) {
        case STUDENT_CHOICE.LIST:
            System.out.println("");
//            studnetManager.studnetTotalList();
            break;
        case STUDENT_CHOICE.INSERT:
            System.out.println("");    
            srm.insertManager();
            break;
        case STUDENT_CHOICE.UPDATE:
            System.out.println("");
//            studnetManager.studnetUpdate();
            break;
        case STUDENT_CHOICE.DELETE:
            System.out.println("");
//            studnetManager.studnetTotalList();
            break;
        case STUDENT_CHOICE.MAIN:
            return;
        default:
            System.out.println("해당 메뉴 번호만 입력하세요.");
        }

    }
	//과목 메뉴
	private static void lessonMenu() {
        int no;
        // StudentRegisterManager studnetManager = new StudentRegisterManager();
        MenuViewer.lessonMenuView();
        no = Integer.parseInt(sc.nextLine());
        LessonRegisterManager lrm = new LessonRegisterManager(); 
        switch (no) {
        case LESSON_CHOICE.LIST:
            System.out.println("");
//            studnetManager.studnetTotalList();
            break;
        case LESSON_CHOICE.INSERT:
            System.out.println("");
        //    srm.insertManager();
            break;
        case LESSON_CHOICE.UPDATE:
            System.out.println("");
//            studnetManager.studnetUpdate();
            break;
        case LESSON_CHOICE.DELETE:
            System.out.println("");
//            studnetManager.studnetUpdate();
            break;
        case LESSON_CHOICE.MAIN:
            return;
        default:
            System.out.println("해당 메뉴 번호만 입력하세요.");
        }
    }
	//수강신청 메뉴
	private static void traineeMenu() {
        int no;
        // StudentRegisterManager studnetManager = new StudentRegisterManager();
        MenuViewer.traineeMenuView();
        no = Integer.parseInt(sc.nextLine());
        TraineeRegisterManager srm = new TraineeRegisterManager(); 
        switch (no) {
        case TRANIEE_CHOICE.LIST:
            System.out.println("");
//            studnetManager.studnetTotalList();
            break;
        case TRANIEE_CHOICE.INSERT:
            System.out.println("");
        //    srm.insertManager();
            break;
        case TRANIEE_CHOICE.UPDATE:
            System.out.println("");
//            studnetManager.studnetUpdate();
            break;
        case TRANIEE_CHOICE.DELETE:
            System.out.println("");
//            studnetManager.studnetUpdate();
            break;
        case TRANIEE_CHOICE.MAIN:
            return;
        default:
            System.out.println("해당 메뉴 번호만 입력하세요.");
        }
    }


}
