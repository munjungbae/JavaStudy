package studentHome;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentMain {
	public static Scanner sc = new Scanner(System.in);
	public static final int PRINT = 1, INSERT = 2, UPDATE = 3, DELETE = 4, EXIT = 5;

	public static void main(String[] args) throws SQLException {
		boolean exitFlag = false;
		while(!exitFlag) {
			Main();
			int number = Integer.parseInt(sc.nextLine());
			switch (number) {
			case PRINT:
				stuPrint();
				break;
			case INSERT:
				stuInsert();
				break;
			case UPDATE:
				stuUpdate();
				break;
			case DELETE:
				stuDelete();
				break;
			case EXIT:
				exitFlag = true;
				break;
			}
		}
		System.out.println("종료합니다.");
	}
	
	

//	private static void stuRank() throws SQLException {
//		Connection con = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		
//		List<Student> stuListRank = new ArrayList<Student>();
//		
//		con = DBConnection.dbCon();
//		stmt = con.createStatement();
//		
//		rs = stmt.executeQuery("SELECT NO, NAME, AVG, RANK() OVER(ORDER BY AVG DESC) AS RANK FROM STU01");
//		
//		while(rs.next()) {
//			int no = rs.getInt("NO");
//			String name = rs.getString("NAME");
//			double avg = rs.getDouble("AVG");
//			int rank = rs.getInt("RANK");
//			
//			Student stuRank = new Student(no, name, avg, rank);
//			
//			stuListRank.add(stuRank);
//		}
//		
//		stuListRankPrint(stuListRank);
//		
//		DBConnection.dbClose(con, stmt, rs);
//		
//	}

	private static void stuDelete() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		
		con = DBConnection.dbCon();
		stmt = con.createStatement();
		
		System.out.print("삭제하고자 하는 학번을 입력해 주세요 : ");
		int no = Integer.parseInt( sc.nextLine());
		int result = stmt.executeUpdate("DELETE FROM STU01 WHERE NO = " + no);
		System.out.println((result != 0) ? "삭제성공" : "삭제실패");
		
		DBConnection.dbClose(con, stmt);
		
	}

	private static void stuUpdate() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		
		con = DBConnection.dbCon();
		stmt = con.createStatement();
		
		System.out.print("수정을 하는 하는 학번을 입력해 주세요 : ");
		int no = Integer.parseInt(sc.nextLine());
		System.out.print("수정하고자 하는 국어 점수을 입력해 주세요 : ");
		int kor = Integer.parseInt(sc.nextLine());
		System.out.print("수정하고자 하는 영어 점수을 입력해 주세요 : ");
		int eng = Integer.parseInt(sc.nextLine());
		System.out.print("수정하고자 하는 수학 점수을 입력해 주세요 : ");
		int math = Integer.parseInt(sc.nextLine());

		Student stu = new Student(kor,eng,math);
		int result = stmt.executeUpdate("UPDATE STU01 SET KOR = " + stu.getKor() + ", " + "ENG = " + stu.getEng() + ", " + "MATH = " + stu.getMath() + " WHERE NO = " + no);
		System.out.println((result != 0) ? "수정성공" : "수정실패");
		

//		int result = stmt.executeUpdate("UPDATE STU01 SET KOR = " + kor + ", " + "ENG = " + eng + ", " + "MATH = " + math + " WHERE NO = " + no);
//		System.out.println((result != 0) ? "수정성공" : "수정실패");
		
		
		DBConnection.dbClose(con, stmt);
	}

	private static void stuInsert() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		
		con = DBConnection.dbCon();
		stmt = con.createStatement();

		System.out.print("입력하고자 하는 국어 점수을 입력해 주세요 : ");
		int kor = Integer.parseInt( sc.nextLine());
		System.out.print("입력하고자 하는 영어 점수을 입력해 주세요 : ");
		int eng = Integer.parseInt( sc.nextLine());
		System.out.print("입력하고자 하는 수학 점수을 입력해 주세요 : ");
		int math = Integer.parseInt( sc.nextLine());
		
		Student stu = new Student(kor,eng,math);
		int result = stmt.executeUpdate("INSERT INTO STU01(NO, NAME, KOR, ENG, MATH) "
										+ "VALUES( STU_SEQ.NEXTVAL, DBMS_RANDOM.STRING('U', 4), " + stu.getKor() + ", " + stu.getEng() + ", " + stu.getMath() + ")");
		System.out.println((result != 0) ? "입력성공" : "입력실패");
		
//		
//		int result = stmt.executeUpdate("INSERT INTO STU01(NO, NAME, KOR, ENG, MATH) "
//										+ "VALUES( STU_SEQ.NEXTVAL, DBMS_RANDOM.STRING('U', 4), " + kor + ", " + eng + ", " + math + ")");
//		
//		System.out.println((result != 0) ? "입력성공" : "입력실패");
		
		DBConnection.dbClose(con, stmt);
	}

	private static void stuPrint() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Student> stuList = new ArrayList<Student>();
		
		con = DBConnection.dbCon();
		
		stmt = con.createStatement();
		
		rs = stmt.executeQuery("SELECT * FROM STU01");
		
		while(rs.next()) {
			int no = rs.getInt("NO");
			String name = rs.getString("NAME");
			int kor = rs.getInt("KOR");
			int eng = rs.getInt("ENG");
			int math = rs.getInt("MATH");
			int sum = rs.getInt("SUM");
			double avg = rs.getDouble("AVG");
			
			Student stu = new Student(no, name, kor, eng, math, sum, avg);
			stuList.add(stu);
		}
		
		stuListPrint(stuList);
		
		DBConnection.dbClose(con, stmt, rs);
	}

	private static void stuListPrint(List<Student> stuList) {
		for ( Student stu : stuList ) {
			System.out.println(stu.toString());
		}
	}
	
//	private static void stuListRankPrint(List<Student> stuListRank) {
//		for ( Student stu : stuListRank ) {
//			System.out.println(stu.toString());
//		}
//	}

	private static void Main() {
		System.out.println("학생");
		System.out.println("======================");
		System.out.println("1. 출력");
		System.out.println("2. 입력");
		System.out.println("3. 수정");
		System.out.println("4. 삭제");
//		System.out.println("5. 순위");
		System.out.println("5. 종료");
		System.out.println("======================");
		System.out.print("번호를 입력하시오 : ");
	}
}