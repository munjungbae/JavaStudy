package com.kh.student.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import com.kh.student.model.StudentVO;

public class StudentDAO {
	public static String SelectSQL = "SELECT * FROM STU01";
	public static String SelectTrashSQL = "SELECT * FROM STU01_TRASH";
	public static String InsertSQL = "INSERT INTO STU01(NO, NAME, KOR, ENG, MATH) VALUES(STU_SEQ.NEXTVAL, DBMS_RANDOM.STRING('U', 4), ?, ?, ?)";
	public static String UpdateSQL = "UPDATE STU01 SET KOR =  ?, ENG = ?, MATH = ? WHERE NO = ?";
	public static String DeleteSQL = "DELETE FROM STU01 WHERE NO = ? ";
	public static String TrashDeleteSQL = "DELETE FROM STU01_TRASH";
	public static String CallableProc2SQL = "{call STU01_PROC2()}";
	public static String CallableProcSQL = "{call STU01_PROC(?, ?)}";
	public static String CallableProcTrashSQL = "{call STU01_PROC3(?)}";
	public static String CallableFuncSQL = "{ ? = call STU01_FUNC(?)}";
	
	public static ArrayList<StudentVO> studentPrint() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<StudentVO> studentList = new ArrayList<StudentVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(SelectSQL);

		while (rs.next()) {
			int no = rs.getInt("NO");
			String name = rs.getString("NAME");
			int kor = rs.getInt("KOR");
			int eng = rs.getInt("ENG");
			int math = rs.getInt("MATH");
			int sum = rs.getInt("SUM");
			double avg = rs.getDouble("AVG");
			String grade = rs.getString("GRADE");
			int rank = rs.getInt("RANK");

			StudentVO stu = new StudentVO(no, name, kor, eng, math, sum, avg, grade, rank);
			studentList.add(stu);
		}
		DBUtility.dbClose(con, stmt, rs);
		return studentList;
	}
	
	public static boolean studentInsert(StudentVO svo) throws SQLException {
		boolean insertFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;
		
		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(InsertSQL);
	
		pstmt.setInt(1, svo.getKor());
		pstmt.setInt(2, svo.getEng());
		pstmt.setInt(3, svo.getMath());
		
		cstmt = con.prepareCall(CallableProc2SQL);
		
		int result1 = pstmt.executeUpdate();
		int result2 = cstmt.executeUpdate();

		DBUtility.dbClose(con, pstmt, cstmt);
		
		insertFlag = (result1 != 0 && result2 != 0) ? true : false;
		
		return insertFlag;
	}
	
	public static boolean studentUpdate(StudentVO svo) throws SQLException {
		
		boolean updateFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;

		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(UpdateSQL);
		pstmt.setInt(1, svo.getKor());
		pstmt.setInt(2, svo.getEng());
		pstmt.setInt(3, svo.getMath());
		pstmt.setInt(4, svo.getNo());
		
		cstmt = con.prepareCall(CallableProc2SQL);
		
		int result1 = pstmt.executeUpdate();
		int result2 = cstmt.executeUpdate();
		
		updateFlag = (result1 !=0 && result2 != 0) ? true : false;
		
		DBUtility.dbClose(con, pstmt, cstmt);
		
		return updateFlag;
	}

	public static boolean studentDelete(StudentVO svo) throws SQLException {
		boolean deleteFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;

		con = DBUtility.dbCon();
		cstmt = con.prepareCall(CallableProcTrashSQL);
		cstmt.setInt(1, svo.getNo());
		
		pstmt = con.prepareStatement(DeleteSQL);
		pstmt.setInt(1, svo.getNo());
		
		int result2 = cstmt.executeUpdate();
		int result1 = pstmt.executeUpdate();
		
		deleteFlag = (result1 !=0 && result2 != 0) ? true : false;
		
		DBUtility.dbClose(con, pstmt, cstmt);
		
		return deleteFlag;
	}

	public static String studentGrade(StudentVO svo) throws SQLException {
		Connection con = null;
		CallableStatement cstmt = null;
		
		con = DBUtility.dbCon();

		cstmt = con.prepareCall(CallableProcSQL);
		
		cstmt.setInt(1, svo.getNo());
		cstmt.registerOutParameter(2, Types.VARCHAR);
		
		cstmt.executeUpdate();
		String message = cstmt.getString(2);
		
		DBUtility.dbClose(con, cstmt);
		
		return message;
		
	}

	public static String studentSelect(StudentVO svo) throws SQLException {
		Connection con = null;
		CallableStatement cstmt = null;
		
		con = DBUtility.dbCon();
		
		cstmt = con.prepareCall(CallableFuncSQL);
		
		cstmt.registerOutParameter(1, Types.VARCHAR);
		cstmt.setInt(2, svo.getNo());
		
		cstmt.executeUpdate();
		String message = cstmt.getString(1);
		
		DBUtility.dbClose(con, cstmt);
		
		return message;
	}

	public static ArrayList<StudentVO> studentTrash() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<StudentVO> studentTrashList = new ArrayList<StudentVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(SelectTrashSQL);

		while (rs.next()) {
			int no = rs.getInt("NO");
			String name = rs.getString("NAME");
			int kor = rs.getInt("KOR");
			int eng = rs.getInt("ENG");
			int math = rs.getInt("MATH");
			int sum = rs.getInt("SUM");
			double avg = rs.getDouble("AVG");

			StudentVO stu = new StudentVO(no, name, kor, eng, math, sum, avg);
			studentTrashList.add(stu);
		}
		DBUtility.dbClose(con, stmt, rs);
		return studentTrashList;
	}

	public static boolean trashDelete() throws SQLException {
		boolean deleteFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		con = DBUtility.dbCon();
		
		pstmt = con.prepareStatement(TrashDeleteSQL);
		
		int result1 = pstmt.executeUpdate();
		
		deleteFlag = (result1 !=0) ? true : false;
		
		DBUtility.dbClose(con, pstmt);
		
		return deleteFlag;
	}
}
