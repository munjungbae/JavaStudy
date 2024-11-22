package com.kh.subjectMVCProject.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.subjectMVCProject.model.SubjectVO;

public class SubjectDAO {
	public static final String SUBJECT_SELECT = "SELECT * FROM SUBJECT";
	public static final String SUBJECT_INSERT = "insert into subject(no, num, name) values (subject_seq.nextval, ?, ?)";
	public static final String SUBJECT_CALL_RANK_PROC = "{call STUDENT_RANK_PROC()}";
	public static final String SUBJECT__UPDATE = "UPDATE STUDENT SET NAME = ?, KOR = ?, ENG = ?, MAT = ? WHERE NO = ? ";
	public static final String SUBJECT_DELETE = "DELETE FROM STUDENT WHERE NO = ?";
	public static final String SUBJECT_SORT = "SELECT * FROM STUDENT ORDER BY RANK";
	
	public static ArrayList<SubjectVO> subjectSelect() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<SubjectVO> subjectList= new ArrayList<SubjectVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(SUBJECT_SELECT);

		while (rs.next()) {
			int no = rs.getInt("NO");
			String num = rs.getString("NUM");
			String name = rs.getString("NAME");

			SubjectVO stu = new SubjectVO(no, num, name);
			subjectList.add(stu);
		}
//		stuListPrint(studentList);
		DBUtility.dbClose(con, stmt, rs);
		
		return subjectList;
	}

	public boolean subjectInsert(SubjectVO svo) throws SQLException {
		// Conection
		boolean successFlag = false;
		Connection con = null;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;

		// 1 Load, 2. connect
		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(SUBJECT_INSERT);
		pstmt.setString(1, svo.getNum());
		pstmt.setString(2, svo.getName());

		int result = pstmt.executeUpdate();

		DBUtility.dbClose(con, pstmt, cstmt);
		
		successFlag = (result != 0) ? true : false;
		return successFlag;
	}

	public static boolean studentUpdate(SubjectVO svo) throws SQLException {
		boolean successFlag = false;
		Connection con = null;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;
		
		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(SUBJECT__UPDATE);
		pstmt.setString(1, svo.getName());
		pstmt.setInt(5, svo.getNo());

		int result1 = pstmt.executeUpdate();
		cstmt = con.prepareCall(SUBJECT_CALL_RANK_PROC);
		int result2 = cstmt.executeUpdate();

		successFlag = (result1 != 0 && result2 != 0) ? true : false;
		
		DBUtility.dbClose(con, pstmt, cstmt);
		
		return successFlag;
	}

	public static boolean studentDelete(SubjectVO svo) throws SQLException {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(SUBJECT_DELETE);
		pstmt.setInt(1, svo.getNo());
		int result = pstmt.executeUpdate();

		successFlag = (result != 0) ? true : false;

		DBUtility.dbClose(con, pstmt);
		
		return successFlag;
	}

	public static ArrayList<SubjectVO> subjectSort() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<SubjectVO> subjectList = new ArrayList<SubjectVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(SUBJECT_SORT);

		while (rs.next()) {
			int no = rs.getInt("NO");
			String name = rs.getString("NAME");
			int kor = rs.getInt("KOR");
			int eng = rs.getInt("ENG");
			int mat = rs.getInt("MAT");
			int total = rs.getInt("TOTAL");
			int ave = rs.getInt("AVE");
			int rank = rs.getInt("RANK");

			SubjectVO stu = new SubjectVO();
			subjectList.add(stu);
		}
		DBUtility.dbClose(con, stmt, rs);
		return subjectList;
	}


}
