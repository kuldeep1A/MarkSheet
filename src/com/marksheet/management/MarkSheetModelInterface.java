package com.marksheet.management;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public interface MarkSheetModelInterface {
	/**
	 * Adds the student's marksheet information to the result table
	 * 
	 * @param marksheet
	 *          - The marksheet object contains the student's details and marks.
	 * @return true if the marksheet was added successfully, false otherwise.
	 */
	public boolean add(Marksheet marksheet);

	/**
	 * Delete the specific data (information) of the student's by Enrollment
	 * 
	 * @param rollNo
	 *          Enrollment of the student's
	 * @return true if the specific student's data is deleted successfully by
	 *         Enrollment, false otherwise
	 */
	public boolean deleteByRollNo(String rollNo);

	/**
	 * Delete the specific data (information) of the student's by Email
	 * 
	 * @param emailId
	 *          - Email Id of the student's
	 * @return true if the specific student's data is deleted successfully by
	 *         Email Id, false otherwise
	 */
	public boolean deleteByEmailId(String emailId);

	/**
	 * Updates the specific details of the student's of marksheet in the result
	 * table
	 * 
	 * @param data
	 *          - The data is specific details of student's
	 * @return true if the marksheet was update successfully, false otherwise.
	 */
	public boolean update(String data);

	/**
	 * Update all details of the student's of marksheet in the result table
	 * 
	 * @param marksheet
	 *          object of markseet
	 * @return true if the marksheet was added successfully, false otherwise.
	 */
	public boolean updateAll(Marksheet marksheet);

	/**
	 * Detele the entire table of result
	 * 
	 * @return true if the entire successfully deleted, false otherwise
	 */
	public boolean deleteAll();

	/**
	 * Get the all information of the student's from the result table
	 * 
	 * 
	 * @param data
	 *          Enrollment of the student's
	 * @return {@code ArrayList} that contains the details of the student's
	 */
	public ArrayList<String> get(String data);

	/**
	 * Get the all student infromation from the result table
	 * 
	 * @return {@code ArrayList} that contains the details of the student's
	 */
	public LinkedHashSet<ArrayList<String>> getAll();

	/**
	 * Get the Merit student List from the result table
	 * 
	 * @return {@code LinkedHashSet} List of marit students
	 */
	public LinkedHashSet<ArrayList<String>> getMeritList();

	/**
	 * Get the total number of student in the result
	 * 
	 * @return {@code int} total number students
	 */
	public int numberOfStudent();

	/**
	 * Get the List of students's who failed, from the result
	 * 
	 * @return {@code LinkedHashSet} List of failed student
	 */
	public LinkedHashSet<ArrayList<String>> getFailedStudentsList();

	/**
	 * Get the Absent students List form the result
	 * 
	 * @return {@code ArrayList} List of Absenties
	 */
	public ArrayList<ArrayList<String>> getAbsenties();

	/**
	 * Get the multiple topper form the result
	 * 
	 * @return {@code ArrayList} List of toppers
	 */
	public ArrayList<ArrayList<String>> getTopper();

	/**
	 * Get the List of students who get the lowest marks from the result table
	 * 
	 * @return {@code String[][]} List of lowest marks students
	 */
	public String[][] getLowestMarkStudents();

	/**
	 * Get the List of students who passed out from the result table
	 * 
	 * @return {@code String[][]} List of passed student
	 */
	public String[][] getPassedStdents();

	/**
	 * Get the average of the result of class
	 * 
	 * @return {@code double} average result of class
	 */
	public double getAverageResultOfClass();

	/**
	 * Get the List of students who have ATKT(Allowed to keep terms)
	 * 
	 * @return {@code List} List of ATKT students
	 */
	public List<ArrayList<String>> getATKTStudents();

	/**
	 * Get the CutOff marks of the result
	 * 
	 * @return {@code dobule} return CutOff marks
	 */
	public double getCutOff();

	/**
	 * Get the number passed boys student
	 * 
	 * @return {@code int} return the total number of passed boys student
	 */
	public int getNumberOfBoysPass();

	/**
	 * Get the number passed girls student
	 * 
	 * @return {@code int} return the total number of passed girls student
	 */
	public int getNumberOfGirlsPass();

	/**
	 * Get the Grade marks of the student's
	 * 
	 * @param enrollment
	 *          - Enrollment of the student's
	 * @return {@code char} Grade marks
	 */
	public char getGradeOfStudent(String rollNo);

	/**
	 * Get the total number of girls
	 * 
	 * @return {@code int} return the total number of girls
	 */
	public int getTotalNumberOfGirls();

	/**
	 * Get the total number of boys
	 * 
	 * @return {@code int} return the total number of boys
	 */
	public int getTotalNumberOfBoys();

	/**
	 * Get the average result of the all grils
	 * 
	 * @return {@code int} return the total number of boys
	 */
	public double getAverageResultOfGirls();

	/**
	 * Get the average result of the all boys
	 * 
	 * @return {@code double} average result of all boys
	 */
	public double getAverageResultOfBoys();
}
