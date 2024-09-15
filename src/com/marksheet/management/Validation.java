package com.marksheet.management;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.marksheet.UI.Colors;
import com.marksheet.UI.Display;

public class Validation implements Colors {
	public static Scanner sc = new Scanner(System.in);

	public static String checkName() {
		Display.printRules(1);
		while (true) {
			Display._printMessage("\n\t\tName of the Student: -> ");
			String name = sc.nextLine();
			if (name.length() >= 2 && name.length() <= 30 && name.matches("^[A-Z][a-z]*(\\s[A-Z][a-z]*){0,30}$"))
				return name;
			else if (name.toLowerCase().equals("\\-1") || name.toLowerCase().equals("\\exit")) {
				if (confirm("to terminat MarkSheet-Managment")) {
					Display.waterMark();
					System.exit(0);
				}
			} else
				Display.printMessage(RED + "\t\tError: Follow the names rules strictly!" + RESET);
		}
	}

	public static String checkEnrollment() {
		Display.printRules(2);
		while (true) {
			Display._printMessage("\n\t\tEnrollment of the student's: -> ");
			String enrollment = sc.nextLine();
			if (enrollment.matches("^24ENG4CSE[1-9]\\d{3}$"))
				return enrollment;
			else if (enrollment.toLowerCase().equals("\\-1") || enrollment.toLowerCase().equals("\\exit")) {
				if (confirm("to terminat MarkSheet-Managment")) {
					Display.waterMark();
					System.exit(0);
				}
			} else
				Display.printMessage(RED + "\t\tError: Follow the enrollments rules strictly!" + RESET);
		}
	}

	public static String checkEmail() {
		Display.printRules(3);
		while (true) {
			Display._printMessage("\n\t\tEmail of the student's: -> ");
			String email = sc.nextLine().toLowerCase();
			if (email.matches("^[a-zA-Z0-9\\.]{1,30}@[a-zA-Z]{3,12}\\.(com|in)$"))
				return email;
			else if (email.toLowerCase().equals("\\-1") || email.toLowerCase().equals("\\exit")) {
				if (confirm("to terminat MarkSheet-Managment")) {
					Display.waterMark();
					System.exit(0);
				}
			} else
				Display.printMessage(RED + "\t\tError: Follow the email rules strictly!" + RESET);
		}
	}

	public static char checkGender() {
		Display.printRules(4);
		while (true) {
			Display._printMessage("\n\t\tGender of the student's: -> ");
			String gender = sc.nextLine();
			if (gender.length() == 1 && (gender.charAt(0) == 'M' || gender.charAt(0) == 'F'))
				return gender.charAt(0);
			else if (gender.toLowerCase().equals("\\-1") || gender.toLowerCase().equals("\\exit")) {
				if (confirm("to terminat MarkSheet-Managment")) {
					Display.waterMark();
					System.exit(0);
				}
			} else
				Display.printMessage(RED + "\t\tError: Follow the gender rules strictly!" + RESET);
		}
	}

	public static LocalDate checkDOB() {
		Display.printRules(5);
		while (true) {
			Display._printMessage("\n\t\tDate of Brith of the student's: -> ");
			String dateString = sc.nextLine();
			if (dateString.toLowerCase().equals("\\-1") || dateString.toLowerCase().equals("\\exit")) {
				if (confirm("to terminat MarkSheet-Managment")) {
					Display.waterMark();
					System.exit(0);
				}
			}
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			try {
				LocalDate parseDate = LocalDate.parse(dateString, formatter);
				String reformattedDate = parseDate.format(formatter);
				if (reformattedDate.equals(dateString) && parseDate.getYear() >= 1999 && parseDate.getYear() <= 2005)
					return parseDate;
				else
					Display.printMessage(RED + "\t\tError: Follow the date rules strictly!" + RESET);
			} catch (DateTimeParseException | InputMismatchException e) {
				Display.printMessage(RED + "\t\tError: Follow the date rules strictly!" + RESET);
			}
		}
	}

	public static int checkMarks(String subject) {
		while (true) {
			Display._printMessage("\n\t\tMarks of " + subject + " subject -> ");
			String mString = sc.nextLine();
			try {
				if (Integer.parseInt(mString) == -1
						|| (Integer.parseInt(mString) >= 1 && Integer.parseInt(mString) <= 100))
					return Integer.parseInt(mString);
				else
					Display.printMessage(RED + "\t\tWarning: Marks out of range." + RESET);
			} catch (NumberFormatException e) {
				if (mString.toLowerCase().equals("\\-1") || mString.toLowerCase().equals("\\exit")) {
					if (confirm("to terminat MarkSheet-Managment")) {
						Display.waterMark();
						System.exit(0);
					}
				} else
					Display.printMessage(RED + "\t\tError: Follow the marks rules strictly!" + RESET);
			}
		}
	}

	public static boolean confirm(String message) {
		while (true) {
			Display.printMessage("\n\t\tConfirm " + message + " : `y` or `n`");
			Display._printMessage("\t\t-> ");
			String yesOrNo = sc.nextLine().toLowerCase();
			if (yesOrNo.equals("y"))
				return true;
			else if (yesOrNo.equals("n")) {
				Display.printMessage("\t\tSo Okey, then " + message + " opearation are not performed.");
				return false;
			} else
				Display.printMessage(RED + "\t\tError: Follow the confirm `y` or `n` rules strictly!" + RESET);
		}
	}

	public static int checkCommand(int range) {
		while (true) {
			Display._printMessage("\n\t\tCommand to select -> ");
			String commad = sc.nextLine();
			try {
				if (Integer.parseInt(commad) >= 1 && Integer.parseInt(commad) <= range)
					return Integer.parseInt(commad);
				else
					Display.printMessage(
							RED + "\t\tWarning: Command out of range. Please enter a number between 1 and " + range + RESET);
			} catch (NumberFormatException e) {
				if (commad.equals("\\-1") || commad.equals("\\exit")) {
					if (confirm("to terminat MarkSheet-Managment")) {
						Display.waterMark();
						System.exit(0);
					}
				} else
					Display.printMessage(RED + "\t\tError: Follow the confirm `y` or `n` rules strictly!" + RESET);
			}
		}
	}

	public static String checkTable(ArrayList<String> arrNames) {
		Display.printRules(7);
		while (true) {
			Display._printMessage("\n\t\tNew Table Name -> ");
			String tableName = sc.nextLine();

			if (tableName.matches("^[A-Za-z0-9_$]{5,19}$") && !arrNames.contains(tableName)) {
				return tableName.toLowerCase();
			} else if (tableName.equals("\\-1") || tableName.equals("\\exit")) {
				if (confirm("to terminat MarkSheet-Managment")) {
					Display.waterMark();
					System.exit(0);
				}
			} else
				Display.printMessage(RED + "\t\tError: Follow the tables rules strictly!" + RESET);
		}
	}
}
