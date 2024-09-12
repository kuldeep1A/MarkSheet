package com.marksheet.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.marksheet.UI.Colors;
import com.marksheet.management.MarkSheetOperation;
import com.marksheet.management.Marksheet;

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

	public static boolean connect() throws ClassNotFoundException, SQLException, NullPointerException {
		Class.forName(rb.getString("DRIVER"));
		conn = DriverManager.getConnection(rb.getString("URL"), rb.getString("USER"), rb.getString("PASSWORD"));
		stmt = conn.createStatement();
		stmt.executeUpdate(
				"CREATE TABLE IF NOT EXISTS result1 ( rollNo VARCHAR(13) NOT NULL, name VARCHAR(255) NOT NULL, math INT NOT NULL, chemistry INT NOT NULL, physics INT NOT NULL, DOB DATE NOT NULL, email VARCHAR(255) NOT NULL, gender CHAR(1) NOT NULL, PRIMARY KEY (rollNo), UNIQUE (email) );");

		String selectQuery = "SELECT * FROM result1";
		ResultSet resultSet = stmt.executeQuery(selectQuery);
		while (!resultSet.next()) {
			System.out
					.println(
							"\n\t\tIn this result table there no marksheet detail of any student. \n\t\tPlease update some details, then use Marksheet Management");
			operation.add(new Marksheet());
			resultSet = stmt.executeQuery(selectQuery);
		}
		return true;
	}

}
