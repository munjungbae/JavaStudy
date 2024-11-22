package studentHome;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentMain {
	public static Scanner sc = new Scanner(System.in);


	public static void main(String[] args) throws SQLException {
		boolean exitFlag = false;
		while (!exitFlag) {
			Main();
			int number = Integer.parseInt(sc.nextLine());
			switch (number) {
			case StuIF.PRINT:
				stuPrint();
				break;
			case StuIF.INSERT:
				stuInsert();
				break;
			case StuIF.UPDATE:
				stuUpdate();
				break;
			case StuIF.DELETE:
				stuDelete();
				break;
			case StuIF.GRADE:
				stuGrade();
				break;
			case StuIF.FUNC:
				stuInfo();
				break;
			case StuIF.EXIT:
				exitFlag = true;
				break;
			}
		}
		System.out.println("종료합니다.");
	}

	private static void stuInfo() throws SQLException {
		Connection con = null;
		CallableStatement cstmt = null;
		
		con = DBConnection.dbCon();
		
		System.out.print("조회 할 학생의 학번을 입력 해 주세요 : ");
		int no = Integer.parseInt(sc.nextLine());
		
		cstmt = con.prepareCall("{ ? = call STU01_FUNC(?)}");
		
		cstmt.registerOutParameter(1, Types.VARCHAR);
		cstmt.setInt(2, no);
		
		int result = cstmt.executeUpdate();
		String message = cstmt.getString(1);
		System.out.println(message);
		
		System.out.println((result != 0) ? "메뉴 화면으로 돌아갑니다." : "기능 적용 실패.");
		DBConnection.dbClose(con, cstmt);
	}

	private static void stuGrade() throws SQLException {
		Connection con = null;
		CallableStatement cstmt = null;
		
		con = DBConnection.dbCon();
		
		System.out.print("조회 할 학생의 학번을 입력 해 주세요 : ");
		int no = Integer.parseInt(sc.nextLine());
		
		cstmt = con.prepareCall("{call STU01_PROC(?, ?)}");
		
		cstmt.setInt(1, no);
		cstmt.registerOutParameter(2, Types.VARCHAR);
		
		int result = cstmt.executeUpdate();
		String message = cstmt.getString(2);
		System.out.println(message);
		
		System.out.println((result != 0) ? "메인 화면으로 돌아갑니다." : "등급 적용 실패");

		DBConnection.dbClose(con, cstmt);
		
	}

	private static void stuDelete() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBConnection.dbCon();

		System.out.print("삭제하고자 하는 학번을 입력해 주세요 : ");
		int no = Integer.parseInt(sc.nextLine());
		pstmt = con.prepareStatement("DELETE FROM STU01 WHERE NO = ? ");
		
		pstmt.setInt(1, no);
		
		int result = pstmt.executeUpdate();
		
		System.out.println((result != 0) ? "삭제가 완료 되었습니다." : "삭제실패");
		System.out.println("메인 화면으로 돌아갑니다.");


		DBConnection.dbClose(con, pstmt);

	}

	private static void stuUpdate() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBConnection.dbCon();

		System.out.print("수정을 하는 하는 학번을 입력해 주세요 : ");
		int no = Integer.parseInt(sc.nextLine());
		System.out.print("수정하고자 하는 국어 점수을 입력해 주세요 : ");
		int kor = Integer.parseInt(sc.nextLine());
		System.out.print("수정하고자 하는 영어 점수을 입력해 주세요 : ");
		int eng = Integer.parseInt(sc.nextLine());
		System.out.print("수정하고자 하는 수학 점수을 입력해 주세요 : ");
		int math = Integer.parseInt(sc.nextLine());

		Student stu = new Student(no, kor, eng, math);
		pstmt = con.prepareStatement("UPDATE STU01 SET KOR =  ?, ENG = ?, MATH = ? WHERE NO = ?");
		pstmt.setInt(1, stu.getKor());
		pstmt.setInt(2, stu.getEng());
		pstmt.setInt(3, stu.getMath());
		pstmt.setInt(4, stu.getNo());
		
		
		int result = pstmt.executeUpdate();
		System.out.println((result != 0) ? "수정이 완료되었습니다" : "수정실패");
		System.out.println("메인 화면으로 돌아갑니다.");

		DBConnection.dbClose(con, pstmt);
	}

	private static void stuInsert() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBConnection.dbCon();
		con.setAutoCommit(false);
		System.out.print("입력하고자 하는 국어 점수을 입력해 주세요 : ");
		int kor = Integer.parseInt(sc.nextLine());
		System.out.print("입력하고자 하는 영어 점수을 입력해 주세요 : ");
		int eng = Integer.parseInt(sc.nextLine());
		System.out.print("입력하고자 하는 수학 점수을 입력해 주세요 : ");
		int math = Integer.parseInt(sc.nextLine());

		Student stu = new Student(kor, eng, math);
		pstmt = con.prepareStatement("INSERT INTO STU01(NO, NAME, KOR, ENG, MATH) VALUES(STU_SEQ.NEXTVAL, DBMS_RANDOM.STRING('U', 4), ?, ?, ?)");
		
		pstmt.setInt(1, stu.getKor());
		pstmt.setInt(2, stu.getEng());
		pstmt.setInt(3, stu.getMath());
		
		int result = pstmt.executeUpdate();
		System.out.println((result != 0) ? "입력이 완료되었습니다" : "입력실패");
		System.out.println("메인 화면으로 돌아갑니다.");

		if(result != 0) {
			con.commit();
		} else {
			con.rollback();
		}
		
		DBConnection.dbClose(con, pstmt);
	}

	private static void stuPrint() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		List<Student> stuList = new ArrayList<Student>();

		con = DBConnection.dbCon();

		stmt = con.createStatement();

		rs = stmt.executeQuery("SELECT * FROM STU01");

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

			Student stu = new Student(no, name, kor, eng, math, sum, avg, grade, rank);
			stuList.add(stu);
		}

		stuListPrint(stuList);

		DBConnection.dbClose(con, stmt, rs);
	}

	private static void stuListPrint(List<Student> stuList) {
		for (Student stu : stuList) {
			System.out.println(stu.toString());
		}
	}

	private static void Main() {
		System.out.println("학생");
		System.out.println("======================");
		System.out.println("1. 출력");
		System.out.println("2. 입력");
		System.out.println("3. 수정");
		System.out.println("4. 삭제");
		System.out.println("5. 등급적용");
		System.out.println("6. 학생검색");
		System.out.println("7. 종료");
		System.out.println("======================");
		System.out.print("원하는 작업을 번호로 입력 해 주세요 : ");
	}
}