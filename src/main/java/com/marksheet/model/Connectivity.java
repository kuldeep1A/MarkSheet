package main.java.com.marksheet.model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import main.java.com.marksheet.UI.Colors;
import main.java.com.marksheet.UI.Content;
import main.java.com.marksheet.UI.Display;
import main.java.com.marksheet.management.MarkSheetOperation;
import main.java.com.marksheet.management.Marksheet;
import main.java.com.marksheet.management.Validation;

/**
 * Connectivity
 * 
 * @author kuldeeep1a
 * @apiNote Connection between database and application
 */
public class Connectivity implements Colors, Content {
	private static MarkSheetOperation operation = new MarkSheetOperation();
	private static String _user = null;
	private static String _pass = null;
	public static Connection conn = null;

	public static Statement stmt = null;
	public static PreparedStatement pstmt = null;
	public static ResultSet resultSet = null;
	public static String TABLE_NAME = null;

	public static void login() {
		_user = Validation.checkRoot();
		_pass = Validation.checkPassword();
		connect();
	}

	public static boolean connect() {
		ArrayList<String> tableNames = new ArrayList<>();
		int i = 1;
		try {
			ResourceBundle rb = ResourceBundle.getBundle("main.java.com.marksheet.resources.mysql");

			Class.forName(rb.getString("DRIVER"));
			conn = DriverManager.getConnection(rb.getString("URL"), _user, _pass);
			stmt = conn.createStatement();

			stmt.execute("CREATE DATABASE IF NOT EXISTS marksheet");
			String url = String.format(rb.getString("URL_F"), "marksheet");
			stmt.execute("USE marksheet");
			conn = DriverManager.getConnection(url, _user, _pass);

			DatabaseMetaData dmd = conn.getMetaData();
			String catalog = conn.getCatalog();

			ResultSet rsTable = dmd.getTables(catalog, null, "%", new String[] { "TABLE" });

			Display.loading("Loading tables ", 25);
			Display.printMessage("\n\n\t\tTables in the database:");

			while (rsTable.next()) {
				String tableName = rsTable.getString("TABLE_NAME");
				tableNames.add(tableName);
				Display.printMessage("\t\t" + i++ + ". " + "Table : " + tableName);
			}
			Display.printMessage("\t\t" + i++ + ". " + "Create new Table");
			int _tab = tableNames.size() > 9 ? 26 : 27;
			Display.printInformation(CYAN2 + COMMANDS_RULES + RESET, tableNames.size() + 1,
					COMMANDS_RULES.length() + _tab);
			int commad = Validation.checkCommand(tableNames.size() + 1);

			if (commad == tableNames.size() + 1) {
				TABLE_NAME = Validation.checkTable(tableNames);
			} else {
				TABLE_NAME = tableNames.get(commad - 1);
			}

			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `" + TABLE_NAME
					+ "` ( rollNo VARCHAR(13) NOT NULL, name VARCHAR(255) NOT NULL, math INT NOT NULL, chemistry INT NOT NULL, physics INT NOT NULL, DOB DATE NOT NULL, email VARCHAR(255) NOT NULL, gender CHAR(1) NOT NULL, PRIMARY KEY (rollNo), UNIQUE (email) );");

			String selectQuery = "SELECT * FROM " + TABLE_NAME;
			ResultSet resultSet = stmt.executeQuery(selectQuery);

			while (!resultSet.next()) {
				Display.printMessage(CYAN2
						+ "\n\t\tIn this result table there no marksheet detail of any student. \n\t\tPlease update some details, then use Marksheet Management"
						+ RESET);
				operation.add(new Marksheet());
				resultSet = stmt.executeQuery(selectQuery);
			}
		} catch (MissingResourceException e) {
			Display.printMessage(RED + "\n\t\tError Resource: " + RESET + CYAN2
					+ "Resource file not not found, please add the resource file for accesss the credential." + RESET);
			Display.printMessage("Eror-2.1");
			Display.waterMark();
			System.exit(0);
		} catch (ClassNotFoundException e) {
			Display.printMessage(RED + "\n\t\tError DBMS: " + RESET + CYAN2
					+ "MySql Driver not found, please connect the connector for creating a brige." + RESET);
			Display.printMessage("Eror-2");
			Display.waterMark();
			System.exit(0);
			// e.printStackTrace();
		} catch (CommunicationsException e) {
			Display.printMessage(RED + "\n\t\tCommunications Exception: " + RESET + CYAN2
					+ "It cause because dbms server may not start or dbms are not running." + RESET);
			Display.printMessage("Eror-3");
			Display.waterMark();
			System.exit(0);
		} catch (SQLSyntaxErrorException e) {
			Display.printMessage(RED + "\n\t\tSQL Exception: " + RESET + CYAN2 + e.getMessage() + RESET);
			Display.waterMark();
			Display.printMessage("Eror-4");
			// e.printStackTrace();
			System.exit(0);
		} catch (SQLException e) {
			Display.printMessage(RED + "\n\t\tSQL Exception: " + RESET + CYAN2 + e.getMessage() + RESET);
			// e.printStackTrace();
			Display.waterMark();
			Display.printMessage("Eror-5");
			System.exit(0);
		}
		return true;
	}

}
