package com.marksheet.management;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.marksheet.UI.Colors;
import com.marksheet.UI.Display;

public class Validation implements Colors {
	public static Scanner sc = new Scanner(System.in);

	public static String checkName() {
		Display.printRules(1);
		while (true) {
			System.out.print("\n\t\tName of the Student: -> ");
			String name = sc.nextLine();
			if (name.length() >= 2 && name.length() <= 30 && name.matches("^[A-Z][a-z]*(\\s[A-Z][a-z]*){0,30}$"))
				return name;
			else if (name.toLowerCase().equals("\\-1") || name.toLowerCase().equals("\\exit")) {
				if (Validation.confirm("to terminat MarkSheet-Managment")) {
					Display.waterMark();
					System.exit(0);
				}
			} else
				System.err.println(RED + "\t\tError: Follow the names rules strictly!" + RESET);
		}
	}

	public static String checkEnrollment() {
		Display.printRules(2);
		while (true) {
			System.out.print("\n\t\tEnrollment of the student's: -> ");
			String enrollment = sc.nextLine();
			if (enrollment.matches("^24ENG4CSE[1-9]\\d{3}$"))
				return enrollment;
			else if (enrollment.toLowerCase().equals("\\-1") || enrollment.toLowerCase().equals("\\exit")) {
				if (Validation.confirm("to terminat MarkSheet-Managment")) {
					Display.waterMark();
					System.exit(0);
				}
			} else
				System.err.println(RED + "\t\tError: Follow the enrollments rules strictly!" + RESET);
		}
	}

	public static String checkEmail() {
		Display.printRules(3);
		while (true) {
			System.out.print("\n\t\tEmail of the student's: -> ");
			String email = sc.nextLine().toLowerCase();
			if (email.matches("^[a-zA-Z0-9\\.]{1,30}@[a-zA-Z]{3,12}\\.(com|in)$"))
				return email;
			else if (email.toLowerCase().equals("\\-1") || email.toLowerCase().equals("\\exit")) {
				if (Validation.confirm("to terminat MarkSheet-Managment")) {
					Display.waterMark();
					System.exit(0);
				}
			} else
				System.err.println(RED + "\t\tError: Follow the email rules strictly!" + RESET);
		}
	}

	public static char checkGender() {
		Display.printRules(4);
		while (true) {
			System.out.print("\n\t\tGender of the student's: -> ");
			String gender = sc.nextLine();
			if (gender.length() == 1 && (gender.charAt(0) == 'M' || gender.charAt(0) == 'F'))
				return gender.charAt(0);
			else if (gender.toLowerCase().equals("\\-1") || gender.toLowerCase().equals("\\exit")) {
				if (Validation.confirm("to terminat MarkSheet-Managment")) {
					Display.waterMark();
					System.exit(0);
				}
			} else
				System.err.println(RED + "\t\tError: Follow the gender rules strictly!" + RESET);
		}
	}

	public static LocalDate checkDOB() {
		Display.printRules(5);
		while (true) {
			System.out.print("\n\t\tDate of Brith of the student's: -> ");
			String dateString = sc.nextLine();
			if (dateString.toLowerCase().equals("\\-1") || dateString.toLowerCase().equals("\\exit")) {
				if (Validation.confirm("to terminat MarkSheet-Managment")) {
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
					System.err.println(RED + "\t\tError: Follow the date rules strictly!" + RESET);
			} catch (DateTimeParseException | InputMismatchException e) {
				System.err.println(RED + "\t\tError: Follow the date rules strictly!" + RESET);
			}
		}
	}

	public static int checkMarks(String subject) {
		while (true) {
			System.out.printf("\n\t\tMarks of %s subject -> ", subject);
			try {
				String mString = sc.nextLine();
				if (mString.toLowerCase().equals("\\-1") || mString.toLowerCase().equals("\\exit")) {
					if (Validation.confirm("to terminat MarkSheet-Managment")) {
						Display.waterMark();
						System.exit(0);
					}
				} else if (Integer.parseInt(mString) == -1
						|| (Integer.parseInt(mString) >= 1 && Integer.parseInt(mString) <= 100))
					return Integer.parseInt(mString);
				else
					System.err.println(RED + "\t\tError: Follow the marks rules strictly!" + RESET);
			} catch (InputMismatchException e) {
				System.err.println(RED + "\t\tError: Follow the marks rules strictly!" + RESET);
				sc.next();
			}
		}
	}

	public static boolean confirm(String message) {
		while (true) {
			System.out.println("\n\t\tConfirm " + message + " : `y` or `n`");
			System.out.print("\t\t-> ");
			String yesOrNo = sc.next().toLowerCase();
			sc.nextLine();
			if (yesOrNo.equals("y"))
				return true;
			else if (yesOrNo.equals("n")) {
				System.out.println("\t\tSo Okey, then " + message + " opearation are not performed.");
				return false;
			} else
				System.err.println(RED + "\t\tError: Follow the confirm `y` or `n` rules strictly!" + RESET);
		}
	}

	public static int checkCommand(int range) {
		while (true) {
			try {
				System.out.print("\n\t\tCommand to change -> ");
				int commad = sc.nextInt();
				sc.nextLine();
				if (commad >= 1 && commad <= range)
					return commad;
				else
					System.err.println(RED + "\t\tError: Follow the commands rules strictly!" + RESET);
			} catch (InputMismatchException e) {
				System.err.println(RED + "\t\tError: Follow the commands rules strictly!" + RESET);
				sc.next();
			}
		}
	}
}
