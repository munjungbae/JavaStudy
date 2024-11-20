package studentTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectDB {

	public static void main(String[] args) {
		//변수선언
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Employees> employeesList = new ArrayList<Employees>();
		
		// 1. jdbc driver load , // 2. connection
		con = DBConnection.dbCon();
		
		try {
			// 3. statement *query : c u r d
			stmt = con.createStatement();
			// 4. result set
			rs = stmt.executeQuery("SELECT * FROM EMPLOYEES");
			// 5. 데이터 저장 진행
			while (rs.next()) {
				int employeeID = rs.getInt("EMPLOYEE_ID");
				String firstName = rs.getString("FIRST_NAME");
				int salary = rs.getInt("SALARY");
				Employees employees = new Employees(employeeID, firstName, salary);
				employeesList.add(employees);
			}
		} catch (SQLException e) {
			System.out.println("DB 실행문 오류" + e.toString());
		}
		//6. 데이터 출력
		employeesListPrint(employeesList);

		//7. close
		DBConnection.dbClose(con, stmt, rs);

	}

	private static void employeesListPrint(ArrayList<Employees> employeesList) {
		for (Employees e : employeesList) {
			System.out.println(e.toString());
		}
	}

}
