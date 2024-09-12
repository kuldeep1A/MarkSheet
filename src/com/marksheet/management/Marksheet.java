package com.marksheet.management;

import java.time.LocalDate;

import com.marksheet.UI.Colors;
import com.marksheet.UI.Display;

public class Marksheet implements Colors {
	private String rollNo;
	private String name;
	private int math;
	private int chemistry;
	private int physics;
	private LocalDate DOB;
	private String email;
	private char gender;

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getChemistry() {
		return chemistry;
	}

	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}

	public int getPhysics() {
		return physics;
	}

	public void setPhysics(int physics) {
		this.physics = physics;
	}

	public LocalDate getDOB() {
		return DOB;
	}

	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public void displayDetails() {
		int boxWidth = 66;
		Display.printBorder(boxWidth);
		Display.printFormattedLine("Information of Student's", boxWidth);
		Display.printFormattedLine("Enrollment: " + BLUE + rollNo + RESET, boxWidth + 9);
		Display.printFormattedLine("Name: " + BLUE + name + RESET, boxWidth + 9);
		Display.printFormattedLine("Email: " + BLUE + email + RESET, boxWidth + 9);
		Display.printFormattedLine("Date of Brith: " + BLUE + DOB.toString() + RESET, boxWidth + 9);
		Display.printFormattedLine("Gender: " + BLUE + gender + RESET, boxWidth + 9);
		Display.printFormattedLine("Math: " + BLUE + math + RESET, boxWidth + 9);
		Display.printFormattedLine("Physics: " + BLUE + physics + RESET, boxWidth + 9);
		Display.printFormattedLine("Chemistry: " + BLUE + chemistry + RESET, boxWidth + 9);
		Display.printBorder(boxWidth);
	}
}
