package bookTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class BooksMain {
	public static Scanner sc = new Scanner(System.in);
	public static final int PRINT = 1, INSERT = 2, UPDATE = 3, DELETE = 4, EXIT = 5;

	public static void main(String[] args) throws SQLException {

		boolean exitFlag = false;
		while (!exitFlag) {
			// 사용자로부터 Books 출력, 입력, 수정, 삭제 요청을 받는다
			printMenu();
			int number = Integer.parseInt(sc.nextLine());
			switch (number) {
			case PRINT:
				booksPrint();
				break;
			case INSERT:
				booksInsert();
				break;
			case UPDATE:
				booksUpdate();
				break;
			case DELETE:
				booksDelete();
				break;
			case EXIT:
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

		// 1. load, 2. coonnect
		con = DBConnection.dbCon();
		// 3. statement
		System.out.print("삭제 번호를 입력 해 주세요 : ");
		int no = Integer.parseInt(sc.nextLine());
		stmt = con.createStatement();
		int result = stmt.executeUpdate("DELETE FROM BOOKS WHERE ID = " + no);
		
		//4. 내용이 잘 입력 되었는지 체크
		System.out.println((result != 0) ? "삭제성공" : "삭제실패");
		//5. 객체반납
		DBConnection.dbClose(con, stmt);

	}

	// 수정
	private static void booksUpdate() throws SQLException {
		// connection
		Connection con = null;
		Statement stmt = null;

		// 1. load, 2. coonnect
		con = DBConnection.dbCon();
		// 3. statement
		//수정 할 데이터
		
		Books books = new Books(3, "Java Java Java", "MJB", "2024", 33000);
		stmt = con.createStatement();
		int result = stmt.executeUpdate("UPDATE BOOKS SET TITLE = '"+books.getTitle()+"', PUBLISHER = '"+books.getPrice()+"', YEAR = '"+books.getYear()+"', PRICE = "+books.getPrice()+" WHERE ID = "+books.getId()+"");
		
		//4. 내용이 잘 입력 되었는지 체크
		System.out.println((result != 0) ? "수정성공" : "수정실패");
		//5. 객체반납
		DBConnection.dbClose(con, stmt);

	}

	// 삽입
	private static void booksInsert() throws SQLException {
		// connection
		Connection con = null;
		Statement stmt = null;

		// 1. load, 2. coonnect
		con = DBConnection.dbCon();
		// 3. statement
		Books books = new Books(0, "Head First Java", "MJB", "2008", 23000);
		String publisher = "MJB";
		stmt = con.createStatement();
		int result = stmt.executeUpdate("INSERT INTO books VALUES "
				+ "(BOOKS_ID_SEQ.nextval, '"+ books.getTitle() +"', '"+books.getPublisher()+"', '"+ books.getYear()+"', '"+books.getPrice()+"')");
		
		//4. 내용이 잘 입력 되었는지 체크
		System.out.println((result != 0) ? "성공" : "실패");
		//5. 객체반납
		DBConnection.dbClose(con, stmt);
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
			booksListPrint(booksList);
		}
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
