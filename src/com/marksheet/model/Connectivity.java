package com.marksheet.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.marksheet.UI.Colors;
import com.marksheet.UI.Display;
import com.marksheet.management.MarkSheetOperation;
import com.marksheet.management.Marksheet;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

/**
 * Connectivity
 * 
 * @author kuldeeep1a
 * @apiNote Connection between database and application
 */
public class Connectivity implements Colors {
	private static MarkSheetOperation operation = new MarkSheetOperation();
	private static ResourceBundle rb = ResourceBundle.getBundle("com.marksheet.resources.mysql");
	public static Connection conn = null;
	public static Statement stmt = null;
	public static PreparedStatement pstmt = null;
	public static ResultSet resultSet = null;
	public static ResultSetMetaData resultSetMetaData = null;

	public static boolean connect() {
		try {
			Class.forName(rb.getString("DRIVER"));
			conn = DriverManager.getConnection(rb.getString("URL"), rb.getString("USER"), rb.getString("PASSWORD"));
			stmt = conn.createStatement();
			stmt.executeUpdate(
					"CREATE TABLE IF NOT EXISTS result1 ( rollNo VARCHAR(13) NOT NULL, name VARCHAR(255) NOT NULL, math INT NOT NULL, chemistry INT NOT NULL, physics INT NOT NULL, DOB DATE NOT NULL, email VARCHAR(255) NOT NULL, gender CHAR(1) NOT NULL, PRIMARY KEY (rollNo), UNIQUE (email) );");

			String selectQuery = "SELECT * FROM result1";
			ResultSet resultSet = stmt.executeQuery(selectQuery);
			while (!resultSet.next()) {
				System.out
						.println(CYAN2 +
								"\n\t\tIn this result table there no marksheet detail of any student. \n\t\tPlease update some details, then use Marksheet Management"
								+ RESET);
				operation.add(new Marksheet());
				resultSet = stmt.executeQuery(selectQuery);
			}
		} catch (ClassNotFoundException e) {
			System.err.println(
					RED + "\n\t\tError DBMS: " + RESET + CYAN2
							+ "MySql Driver not found, please connect the connector for creating a brige." + RESET);
			System.out.println("Eror-2");
			e.printStackTrace();
		} catch (CommunicationsException e) {
			System.err.println(RED + "\n\t\tCommunications Exception: " + RESET + CYAN2
					+ "It cause because dbms server may not start or dbms are not running." + RESET);
			Display.waterMark();
			System.out.println("Eror-3");
			System.exit(0);
		} catch (SQLSyntaxErrorException e) {
			System.err.println(RED + "\n\t\tSQL Exception: " + RESET + CYAN2 + e.getMessage() + RESET);
			Display.waterMark();
			System.out.println("Eror-4");
			// e.printStackTrace();
			System.exit(0);
		} catch (SQLException e) {
			System.err.println(RED + "\n\t\tSQL Exception: " + RESET + CYAN2 + e.getMessage() + RESET);
			Display.waterMark();
			System.out.println("Eror-5");
			System.exit(0);
		}
		return true;
	}

}
