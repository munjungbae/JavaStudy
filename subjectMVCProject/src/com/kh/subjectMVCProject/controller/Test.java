package com.kh.subjectMVCProject.controller;

import java.sql.Connection;

public class Test {

	public static void main(String[] args) {
		Connection con = DBUtility.dbCon();
		
		if(con != null) {
			System.out.println("DB 접속 완료");
		} else {
			System.out.println("DB 접속 실패");
		}
	}

}
