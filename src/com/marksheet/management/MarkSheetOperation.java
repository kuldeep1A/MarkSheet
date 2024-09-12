package com.marksheet.management;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.marksheet.UI.Colors;
import com.marksheet.UI.Content;
import com.marksheet.UI.Display;
import com.marksheet.model.ModelOperation;

public class MarkSheetOperation implements MarkSheetModelInterface, Colors, Content {
	private static void inputes(Marksheet marksheet) {
		marksheet.setEmail(Validation.checkEmail());
		marksheet.setGender(Validation.checkGender());
		marksheet.setDOB(Validation.checkDOB());
		System.out.print("\n\n\t\tMarks:");
		System.out.println(GREEN + """
				\n\t\tRules:
					\t\t1. Marks between 1 and 100
					\t\t2. -1 for absent
					""" + RESET);
		marksheet.setMath(Validation.checkMarks("Math"));
		marksheet.setChemistry(Validation.checkMarks("Chemistry"));
		marksheet.setPhysics(Validation.checkMarks("Physics"));

	}

	/**
	 * @apiNote Methods 1
	 */
	@Override
	public boolean add(Marksheet marksheet) {
		System.out.println("\n\t\tAdds the student's marksheet information to the result table");

		marksheet.setName(Validation.checkName());
		marksheet.setRollNo(Validation.checkEnrollment());

		inputes(marksheet);

		marksheet.displayDetails();
		if (Validation.confirm())
			ModelOperation.insert(marksheet);
		return true;
	}

	/**
	 * @apiNote Methods 2
	 */
	@Override
	public boolean update(String enrollment) {
		ArrayList<String> studentInfo = get(enrollment);
		if (!studentInfo.isEmpty()) {
			Display.printInformation(studentInfo, HEADER);
			System.out.println(SPECIFIC_COMMANDS_DETAIL);

			switch (Validation.checkCommand(7)) {
				case 1:
					ModelOperation.update(enrollment, "name", Validation.checkName());
					break;
				case 2:
					ModelOperation.update(enrollment, "math", Validation.checkMarks("Math"));
					break;
				case 3:
					ModelOperation.update(enrollment, "chemistry", Validation.checkMarks("Chemistry"));
					break;
				case 4:
					ModelOperation.update(enrollment, "physics", Validation.checkMarks("Physics"));
					break;
				case 5:
					ModelOperation.update(enrollment, "DOB", Validation.checkDOB());
					break;
				case 6:
					ModelOperation.update(enrollment, "email", Validation.checkEmail());
					break;
				case 7:
					ModelOperation.update(enrollment, "gender", Validation.checkGender());
					break;
				default:
					break;
			}
		}
		return true;
	}

	/**
	 * @apiNote Methods 3
	 */
	@Override
	public boolean updateAll(Marksheet marksheet) {
		System.out.println("\t\tWhich student's detail update enter Enrollment:");
		marksheet.setRollNo(Validation.checkEnrollment());
		ArrayList<String> student = get(marksheet.getRollNo());
		if (!student.isEmpty()) {
			Display.printInformation(student, HEADER);
			System.out.println("\t\t");
			marksheet.setName(Validation.checkName());
			inputes(marksheet);
			marksheet.displayDetails();
			if (Validation.confirm()) {
				student = ModelOperation.update(marksheet);
				if (student != null)
					Display.printInformation(student, HEADER);
			}
		}
		return true;
	}

	/**
	 * @apiNote Methods 4
	 */
	@Override
	public boolean deleteByRollNo(String enrollment) {
		ModelOperation.delete(enrollment, "rollNo");
		return true;
	}

	/**
	 * @apiNote Methods 5
	 */
	@Override
	public boolean deleteByEmailId(String emailId) {
		ModelOperation.delete(emailId, "email");
		return true;
	}

	/**
	 * @apiNote Methods 6
	 */
	@Override
	public boolean deleteAll() {
		ModelOperation.deleteTableData();
		return true;
	}

	/**
	 * @apiNote Methods 7
	 */
	@Override
	public ArrayList<String> get(String enrollment) {
		return ModelOperation.get(enrollment);
	}

	/**
	 * @apiNote Methods 8
	 */
	@Override
	public LinkedHashSet<ArrayList<String>> getAll() {
		return ModelOperation.getAll();
	}

	/**
	 * @apiNote Methods 9
	 */
	@Override
	public LinkedHashSet<ArrayList<String>> getMeritList() {
		Set<ArrayList<String>> students = getAll();
		List<ArrayList<String>> meritStudents1 = new ArrayList<>();
		students.stream().filter(student -> {
			int math = Integer.parseInt(student.get(2));
			int chemistry = Integer.parseInt(student.get(3));
			int physics = Integer.parseInt(student.get(4));
			double percent = (math + chemistry + physics) / 3.0;
			return percent >= 80;
		}).sorted((s1, s2) -> {
			int total1 = Integer.parseInt(s1.get(2)) + Integer.parseInt(s1.get(2)) + Integer.parseInt(s1.get(2));
			int total2 = Integer.parseInt(s1.get(2)) + Integer.parseInt(s1.get(2)) + Integer.parseInt(s1.get(2));
			return Integer.compare(total1, total2);
		}).forEach(meritStudents1::add);
		return new LinkedHashSet<>(meritStudents1);
	}

	/**
	 * @apiNote Methods 10
	 */
	@Override
	public int numberOfStudent() {
		return ModelOperation.getNumberInformation("SELECT COUNT(*) FROM result1");
	}

	/**
	 * @apiNote Methods 11
	 */
	@Override
	public LinkedHashSet<ArrayList<String>> getFailedStudentsList() {
		String query = "SELECT *, (math + chemistry + physics) AS total_marks FROM result1 WHERE math < 33 OR chemistry < 33 OR physics < 33 ORDER BY rollNo";
		/**
		 * For logically
		 * 
		 * Set<ArrayList<String>> students = getAll();
		 * List<ArrayList<String>> meritStudents1 = new ArrayList<>();
		 * students.stream().filter(student -> {
		 * int math = Integer.parseInt(student.get(2));
		 * int chemistry = Integer.parseInt(student.get(3));
		 * int physics = Integer.parseInt(student.get(4));
		 * return math < 33 || chemistry < 33 || physics < 33;
		 * }).sorted((s1, s2) -> {
		 * int total1 = Integer.parseInt(s1.get(2)) + Integer.parseInt(s1.get(2)) +
		 * Integer.parseInt(s1.get(2));
		 * int total2 = Integer.parseInt(s1.get(2)) + Integer.parseInt(s1.get(2)) +
		 * Integer.parseInt(s1.get(2));
		 * return Integer.compare(total1, total2);
		 * }).forEach(meritStudents1::add);
		 * 
		 */
		return new LinkedHashSet<>(ModelOperation.getSpecific(query));
	}

	/**
	 * @apiNote Methods 12
	 */
	@Override
	public ArrayList<ArrayList<String>> getAbsenties() {
		Set<ArrayList<String>> students = getAll();
		ArrayList<ArrayList<String>> absentStudent = new ArrayList<>();
		students.stream().filter(student -> {
			int math = Integer.parseInt(student.get(2));
			int chemistry = Integer.parseInt(student.get(3));
			int physics = Integer.parseInt(student.get(4));
			return math == -1 || chemistry == -1 || physics == -1;
		}).sorted((s1, s2) -> {
			int total1 = Integer.parseInt(s1.get(2)) + Integer.parseInt(s1.get(3)) + Integer.parseInt(s1.get(4));
			int total2 = Integer.parseInt(s1.get(2)) + Integer.parseInt(s1.get(3)) + Integer.parseInt(s1.get(4));
			return Integer.compare(total1, total2);
		}).forEach(s -> absentStudent.add(new ArrayList<>(s.subList(0, 5))));
		return absentStudent;
	}

	/**
	 * @apiNote Methods 13
	 */
	@Override
	public ArrayList<ArrayList<String>> getTopper() {
		String query = "SELECT *, (math + chemistry + physics) AS total_marks FROM result1 WHERE (math + chemistry + physics) = (SELECT MAX(math + chemistry + physics) FROM result1)";
		return ModelOperation.getSpecific(query);
	}

	/**
	 * @apiNote Methods 14
	 */
	@Override
	public String[][] getLowestMarkStudents() {
		String query = "SELECT *, (math + chemistry + physics) AS total_marks FROM result1 WHERE (math + chemistry + physics) = (SELECT MIN(math + chemistry + physics) FROM result1)";
		ArrayList<ArrayList<String>> students = ModelOperation.getSpecific(query);
		String[][] result = new String[students.size()][];

		for (int i = 0; i < students.size(); i++) {
			ArrayList<String> student = students.get(i);
			result[i] = student.toArray(new String[student.size()]);
		}
		return result;
	}

	/**
	 * @apiNote Methods 15
	 */
	@Override
	public String[][] getPassedStdents() {
		String query = "SELECT *, (math + chemistry + physics) AS total_marks FROM result1 WHERE math >= 33 AND chemistry >= 33 AND physics >= 33 ORDER BY rollNo";
		ArrayList<ArrayList<String>> students = ModelOperation.getSpecific(query);
		String[][] result = new String[students.size()][];
		for (int i = 0; i < students.size(); i++) {
			ArrayList<String> student = students.get(i);
			result[i] = student.toArray(new String[student.size()]);
		}
		return result;
	}

	/**
	 * @apiNote Methods 16
	 */
	@Override
	public double getAverageResultOfClass() {
		throw new UnsupportedOperationException("Unimplemented method 'getAverageResultOfClass'");
	}

	/**
	 * @apiNote Methods 17
	 */
	@Override
	public List<ArrayList<String>> getATKTStudents() {
		LinkedHashSet<ArrayList<String>> res = getFailedStudentsList();
		return new ArrayList<>(res);
	}

	/**
	 * @apiNote Methods 18
	 */
	@Override
	public double getCutOff() {
		LinkedHashSet<ArrayList<String>> data = getAll();

		throw new UnsupportedOperationException("Unimplemented method 'getCutOff'");
	}

	/**
	 * @apiNote Methods 19
	 */
	@Override
	public int getNumberOfBoysPass() {
		return ModelOperation.getNumberInformation(
				"SELECT COUNT(*) FROM result1 WHERE gender = 'M' AND math >= 33 AND chemistry >= 33 AND physics >= 33");
	}

	/**
	 * @apiNote Methods 20
	 */
	@Override
	public int getNumberOfGirlsPass() {
		return ModelOperation.getNumberInformation(
				"SELECT COUNT(*) FROM result1 WHERE gender = 'F' AND math >= 33 AND chemistry >= 33 AND physics >= 33");
	}

	/**
	 * @apiNote Methods 21
	 */
	@Override
	public char getGradeOfStudent(String enrollment) {

		throw new UnsupportedOperationException("Unimplemented method 'getGradeOfStudent'");
	}

	/**
	 * @apiNote Methods 22
	 */
	@Override
	public int getTotalNumberOfGirls() {
		return ModelOperation.getNumberInformation("SELECT COUNT(*) FROM result1 WHERE gender='F'");
	}

	/**
	 * @apiNote Methods 23
	 */
	@Override
	public int getTotalNumberOfBoys() {
		return ModelOperation.getNumberInformation("SELECT COUNT(*) FROM result1 WHERE gender='M'");
	}

	/**
	 * @apiNote Methods 24
	 */
	@Override
	public double getAverageResultOfGirls() {

		throw new UnsupportedOperationException("Unimplemented method 'getAverageResultOfGirls'");
	}

	/**
	 * @apiNote Methods 25
	 */
	@Override
	public double getAverageResultOfBoys() {

		throw new UnsupportedOperationException("Unimplemented method 'getAverageResultOfBoys'");
	}

}
