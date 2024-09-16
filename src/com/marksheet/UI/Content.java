package com.marksheet.UI;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Content of Document use to display message or use in perform some operation
 * 
 * @apiNote Store the document or message
 */
public interface Content {
	String HIDE_CURSOR = "\u001B[?25l";

	String SHOW_CURSOR = "\u001B[?25h";

	ArrayList<String> HEADER = new ArrayList<>(
			Arrays.asList("Enrollment", "Name", "Math", "Chemistry", "Physics", "DOB", "Email", "Gender"));

	ArrayList<String> HEADER_MARKS = new ArrayList<>(
			Arrays.asList("Enrollment", "Name", "Math", "Chemistry", "Physics"));

	ArrayList<String> HEADER_TOTAL_MARKS = new ArrayList<>(
			Arrays.asList("Enrollment", "Name", "Math", "Chemistry", "Physics", "DOB", "Email", "Gender",
					"Total Marks"));

	int[] COLUMN_WIDTHS = { 15, 30, 6, 10, 8, 12, 40, 6 };

	int[] COLUMN_WIDTHS_NAME = { 30 };

	int[] COLUMN_WIDTHS_MARKS = { 15, 30, 6, 10, 8 };

	int[] COLUMN_WIDTHS_TOTAL_MARKS = { 15, 30, 6, 10, 8, 12, 40, 6, 12 };

	String COMMANDS_RULES = "Command between 1 to %d and `\\-1` or `\\exit` to terminate the Marksheet Management";

	String WELCOME = "----------------------------------- MarkSheet Management -----------------------------------";

	String COMMANDS_DETAILS = """
			\n\n\t\t\t\tFollow the commands to use MarkSheet Management
			\t\t\t\t\t1.\tAdds the student's marksheet information to the result table
			\t\t\t\t\t2.\tUpdates the specific details of the student's of marksheet in the result table
			\t\t\t\t\t3.\tUpdate all details of the student's of marksheet in the result table
			\t\t\t\t\t4.\tDelete the specific data (information) of the student's by Enrollment
			\t\t\t\t\t5.\tDelete the specific data (information) of the student's by Email
			\t\t\t\t\t6.\tDetele the entire table of result
			\t\t\t\t\t7.\tGet the all information of the student's from the result table
			\t\t\t\t\t8.\tGet the all student infromation from the result table
			\t\t\t\t\t9.\tGet the Merit student List from the result table
			\t\t\t\t\t10.\tGet the total number of student in the result
			\t\t\t\t\t11.\tGet the List of students's who failed, from the result
			\t\t\t\t\t12.\tGet the Absent students List form the result
			\t\t\t\t\t13.\tGet the multiple topper form the result
			\t\t\t\t\t14.\tGet the List of students who get the lowest marks from the result table
			\t\t\t\t\t15.\tGet the List of students who passed out from the result table
			\t\t\t\t\t16.\tGet the average of the result of class
			\t\t\t\t\t17.\tGet the List of students who have ATKT(Allowed to keep terms)
			\t\t\t\t\t18.\tGet the CutOff marks of the result
			\t\t\t\t\t19.\tGet the number passed boys student
			\t\t\t\t\t20.\tGet the number passed girls student
			\t\t\t\t\t21.\tGet the Grade marks of the student's
			\t\t\t\t\t22.\tGet the total number of girls
			\t\t\t\t\t23.\tGet the total number of boys
			\t\t\t\t\t24.\tGet the average result of the all grils
			\t\t\t\t\t25.\tGet the average result of the all boys
			\t\t\t\t\t26.\tChange the result set
			    """;
	String SPECIFIC_COMMANDS_DETAIL = """
			\n\n\t\tFollow the commands to update specific details
			       \t\t1.\tChange the Name
			       \t\t2.\tChange the Math marks
			       \t\t3.\tChange the Chemistry marks
			       \t\t4.\tChange the Physics marks
			       \t\t5.\tChange the Date of Birth
			       \t\t6.\tChange the Email
			       \t\t7.\tChange the Gender
			       """;
	String TABLE_NAME_USE = "-".repeat(24) + " MarkSheet Management now working on table ";
}
