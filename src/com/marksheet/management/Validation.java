package com.marksheet.management;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.marksheet.UI.Colors;

public class Validation implements Colors {
	public static Scanner sc = new Scanner(System.in);

	public static String checkName() {
		System.out.println(GREEN + """
				\n\n\t\tRules:
					\t\t1. Length: The name should be between 2 and 30 characters.
					\t\t2. Allowed characters: Only alphabets and spaces are allowed.
					\t\t3. Capitalization: The first character of each word must be an uppercase letter.
					\t\t4. Spacing: Each word should be separated by a single space.
					\t\t5. No leading/trailing spaces: The name should not start or end with a space.
					\t\t6. No special characters: Only letters and spaces are allowed.
									""" + RESET);

		while (true) {
			System.out.print("\n\t\tName of the Student: -> ");
			String name = sc.nextLine();
			if (name.length() >= 2 && name.length() <= 30 && name.matches("^[A-Z][a-z]*(\\s[A-Z][a-z]*){0,30}$"))
				return name;
			else
				System.err.println(RED + "\t\tError: Follow the names rules strictly!" + RESET);
		}
	}

	public static String checkEnrollment() {
		System.out.println(GREEN + """
				\n\n\t\tRules:
					\t\t1. Example of enrollment type `24ENG4CSE1323`
					\t\t2. The enrollment number must start with the exact sequence 24ENG4CSE (without any spaces).
					\t\t3. After the sequence 24ENG4CSE, you must write a four-digit number.
					\t\t4. The number should start from 1000 and can go higher (e.g., 1000, 1001, 1234, 5678, etc.).
					\t\t5. Do not start the number with a zero. Numbers like 0999 or 0123 are not valid.
					""" + RESET);
		while (true) {
			System.out.print("\n\t\tEnrollment of the student's: -> ");
			String enrollment = sc.nextLine();
			if (enrollment.matches("^24ENG4CSE[1-9]\\d{3}$"))
				return enrollment;
			else
				System.err.println(RED + "\t\tError: Follow the enrollments rules strictly!" + RESET);
		}
	}

	public static String checkEmail() {
		System.out.println(GREEN
				+ """
						\n\t\tRules:
							\t\t1. <<first part>>@<<second part>><<third part>>
							\t\t2. First part can contain alphabets (should accept both lower case and upper case) and numbers and Length should be at least 1 and not greate than 30.
							\t\t3. Second part can contain only upper case and lower case alphabets. Length should be at least 3 and not greate than 12.
							\t\t4. Third part should be either '.com' or '.in'.
						"""
				+ RESET);
		while (true) {
			System.out.print("\n\t\tEmail of the student's: -> ");
			String email = sc.nextLine().toLowerCase();
			if (email.matches("^[a-zA-Z0-9\\.]{1,30}@[a-zA-Z]{3,12}\\.(com|in)$"))
				return email;
			else
				System.err.println(RED + "\t\tError: Follow the email rules strictly!" + RESET);
		}
	}

	public static char checkGender() {
		System.out.println(GREEN + """
				\n\t\tRules: `M` for Male or `F` for Female
				""" + RESET);
		while (true) {
			System.out.print("\n\t\tGender of the student's: -> ");
			String gender = sc.nextLine();
			if (gender.length() == 1 && (gender.charAt(0) == 'M' || gender.charAt(0) == 'F'))
				return gender.charAt(0);
			else
				System.err.println(RED + "\t\tError: Follow the gender rules strictly!" + RESET);
		}
	}

	public static LocalDate checkDOB() {
		System.out.println(GREEN + """
				\n\t\tRules:
					\t\t1. Date formate: `DD/MM/YYYY`, ex: `02/02/2003`
					\t\t2. Date should be valid or leap year with calender
					\t\t3. Birth year valid only for between 1999 to 2005
					""" + RESET);

		while (true) {
			System.out.print("\n\t\tDate of Brith of the student's: -> ");
			String dateString = sc.nextLine();
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
				int marks = sc.nextInt();
				sc.nextLine();
				if (marks == -1 || (marks >= 1 && marks <= 100))
					return marks;
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
