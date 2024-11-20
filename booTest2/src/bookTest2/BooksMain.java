package bookTest2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class BooksMain {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {

		boolean exitFlag = false;
		while (!exitFlag) {
			// 사용자로부터 Books 출력, 입력, 수정, 삭제 요청을 받는다
			printMenu();
			int number = Integer.parseInt(sc.nextLine());
			switch (number) {
			case BookMenu.PRINT:
				booksPrint();
				break;
			case BookMenu.INSERT:
				booksInsert();
				break;
			case BookMenu.UPDATE:
				booksUpdate();
				break;
			case BookMenu.DELETE:
				booksDelete();
				break;
			case BookMenu.EXIT:
				exitFlag = true;
				break;

			default:

			}
		}
		System.out.println("End");
	}

	// 삭제
	private static void booksDelete() throws SQLException {
		// connection
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		// 1. load, 2. coonnect
		con = DBConnection.dbCon();
		// 3. statement
		System.out.print("삭제 번호를 입력 해 주세요 : ");
		int no = Integer.parseInt(sc.nextLine());
		
//		stmt = con.createStatement();
		
		pstmt = con.prepareStatement("DELETE FROM BOOKS WHERE ID = ?");
		pstmt.setInt(1, no);
		int result = pstmt.executeUpdate();
//		int result = stmt.executeUpdate("DELETE FROM BOOKS WHERE ID = " + no);
		
		//4. 내용이 잘 입력 되었는지 체크
		System.out.println((result != 0) ? "삭제성공" : "삭제실패");
		//5. 객체반납
		DBConnection.dbClose(con, pstmt);

	}

	// 수정
	private static void booksUpdate() throws SQLException {
		// connection
		Connection con = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;

		// 1. load, 2. coonnect
		con = DBConnection.dbCon();
		// 3. statement
		//수정 할 데이터
		
		Books books = new Books(8, "Java Java Java", "MJB", "2024", 33000);
//		stmt = con.createStatement();
		
		pstmt = con.prepareStatement("UPDATE BOOKS SET TITLE = ?, PUBLISHER = ?, YEAR = ?, PRICE = ? WHERE ID = ?");
		
		pstmt.setString(1, books.getTitle());
		pstmt.setString(2, books.getPublisher());
		pstmt.setString(3, books.getYear());
		pstmt.setInt(4, books.getPrice());
		pstmt.setInt(5, books.getId());
		
		int result = pstmt.executeUpdate();
//		int result = stmt.executeUpdate("UPDATE BOOKS SET TITLE = '"+books.getTitle()+"', PUBLISHER = '"+books.getPrice()+"', YEAR = '"+books.getYear()+"', PRICE = "+books.getPrice()+" WHERE ID = "+books.getId()+"");
		
		//4. 내용이 잘 입력 되었는지 체크
		System.out.println((result != 0) ? "수정성공" : "수정실패");
		//5. 객체반납
		DBConnection.dbClose(con, pstmt);

	}

	// 삽입
	private static void booksInsert() throws SQLException {
		// connection
		Connection con = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null; 

		// 1. load, 2. coonnect
		con = DBConnection.dbCon();
		// 3. statement
		Books books = new Books(0, "Head First Java", "MJB", "2008", 23000);
//		stmt = con.createStatement();
		
		//statement 대신 prepared statement 사용
		pstmt = con.prepareStatement("INSERT INTO books (ID, TITLE, PUBLISHER, YEAR, PRICE) VALUES "
				+ "(BOOKS_ID_SEQ.nextval, ?, ?, ?, ?)");
		pstmt.setString(1, books.getTitle());
		pstmt.setString(2, books.getPublisher());
		pstmt.setString(3, books.getYear());
		pstmt.setInt(4, books.getPrice());
		
		int result = pstmt.executeUpdate();
		
		
		
		//일반 statement로 받아 excuteupdate해서 결과를 출력
//		int result = stmt.executeUpdate("INSERT INTO books VALUES "
//				+ "(BOOKS_ID_SEQ.nextval, '"+ books.getTitle() +"', '"+books.getPublisher()+"', '"+ books.getYear()+"', '"+books.getPrice()+"')");
//		
		//4. 내용이 잘 입력 되었는지 체크
		System.out.println((result != 0) ? "성공" : "실패");
		//5. 객체반납
		DBConnection.dbClose(con, pstmt);
	}

	// 출력
	public static void booksPrint() throws SQLException {
		// connection
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Books> booksList = new ArrayList<Books>();

		// 1. load, 2. coonnect
		con = DBConnection.dbCon();

		// 3. statement
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM BOOKS");

		// 4. 데이터(테이블) 가져오기
		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String publisher = rs.getString("PUBLISHER");
			String year = rs.getString("YEAR");
			int price = rs.getInt("PRICE");
			Books books = new Books(id, title, publisher, year, price);
			booksList.add(books);

			// 5. 출력하기
		}
		booksListPrint(booksList);
		// 6. 반납하기
		DBConnection.dbClose(con, stmt, rs);
	}

	private static void printMenu() {
		System.out.println("Books Menu");
		System.out.println("------------------------");
		System.out.println("1. PRINT");
		System.out.println("2. INSERT");
		System.out.println("3. UPDATE");
		System.out.println("4. DELETE");
		System.out.println("5. EXIT");
		System.out.println("------------------------");
	}

	private static void booksListPrint(ArrayList<Books> booksList) {
		for (Books books : booksList) {
			System.out.println(books.toString());
		}
	}
}
  