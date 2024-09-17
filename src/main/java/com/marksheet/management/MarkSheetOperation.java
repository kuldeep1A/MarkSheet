package main.java.com.marksheet.management;

import main.java.com.marksheet.UI.Colors;
import main.java.com.marksheet.UI.Content;
import main.java.com.marksheet.UI.Display;
import main.java.com.marksheet.model.Connectivity;
import main.java.com.marksheet.model.ModelOperation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * MarkSheet Opeartion to perform a task
 * 
 * @apiNote Manipulation
 * @apiNote Upation
 * @apiNote Fetches
 */
public class MarkSheetOperation
		implements
		MarkSheetModelInterface,
		Colors,
		Content {
	private static void inputes(Marksheet marksheet) {
		marksheet.setEmail(Validation.checkEmail());
		marksheet.setGender(Validation.checkGender());
		marksheet.setDOB(Validation.checkDOB());
		Display.printRules(6);
		marksheet.setMath(Validation.checkMarks("Math"));
		marksheet.setChemistry(Validation.checkMarks("Chemistry"));
		marksheet.setPhysics(Validation.checkMarks("Physics"));
	}

	/**
	 * @apiNote Methods 1
	 */
	@Override
	public boolean add(Marksheet marksheet) {
		Display.printMessage(
				"\n\t\tAdds the student's marksheet information to the result table");

		marksheet.setName(Validation.checkName());
		marksheet.setRollNo(Validation.checkEnrollment(true));

		inputes(marksheet);

		marksheet.displayDetails();
		if (Validation.confirm("to insert record"))
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
			Display.printMessage(SPECIFIC_COMMANDS_DETAIL);

			switch (Validation.checkCommand(7)) {
				case 1:
					String name = Validation.checkName();
					if (Validation.confirm("to update name")) {
						ModelOperation.update(enrollment, "name", name);
					}
					break;
				case 2:
					int math = Validation.checkMarks("math");
					if (Validation.confirm("to update math marks")) {
						ModelOperation.update(enrollment, "math", math);
					}
					break;
				case 3:
					int chemistry = Validation.checkMarks("Chemistry");
					if (Validation.confirm("to update chemistry marks")) {
						ModelOperation.update(enrollment, "chemistry", chemistry);
					}
					break;
				case 4:
					int physics = Validation.checkMarks("Physics");
					if (Validation.confirm("to update physics marks")) {
						ModelOperation.update(enrollment, "physics", physics);
					}
					break;
				case 5:
					LocalDate DOB = Validation.checkDOB();
					if (Validation.confirm("to update DOB")) {
						ModelOperation.update(enrollment, "DOB", DOB);
					}
					break;
				case 6:
					String email = Validation.checkEmail();
					if (Validation.confirm("to update email")) {
						ModelOperation.update(enrollment, "email", email);
					}
					break;
				case 7:
					char gender = Validation.checkGender();
					if (Validation.confirm("to update gender")) {
						ModelOperation.update(enrollment, "gender", gender);
					}
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
		Display.printMessage("\t\tWhich student's detail update enter Enrollment:");
		marksheet.setRollNo(Validation.checkEnrollment(true));
		ArrayList<String> student = get(marksheet.getRollNo());
		if (!student.isEmpty()) {
			Display.printInformation(student, HEADER);
			Display.printMessage("\t\t");
			marksheet.setName(Validation.checkName());
			inputes(marksheet);
			marksheet.displayDetails();
			if (Validation.confirm("to update all details")) {
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
		if (Validation.confirm("to delete the entire record of the table")) {
			ModelOperation.deleteTableData();
		}
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
		String query = "SELECT * FROM " + Connectivity.TABLE_NAME
				+ " WHERE (math + chemistry + physics) / 3.0 >= 80 ORDER BY (math + chemistry + physics) DESC";
		ArrayList<ArrayList<String>> meritStudents = ModelOperation.get(query, 8);
		// Set<ArrayList<String>> students = getAll();
		// List<ArrayList<String>> meritStudents1 = new ArrayList<>();
		// students.stream().filter(student -> {
		// int math = Integer.parseInt(student.get(2));
		// int chemistry = Integer.parseInt(student.get(3));
		// int physics = Integer.parseInt(student.get(4));
		// double percent = (math + chemistry + physics) / 3.0;
		// return percent >= 80;
		// }).sorted((s1, s2) -> {
		// int total1 = Integer.parseInt(s1.get(2)) + Integer.parseInt(s1.get(2)) +
		// Integer.parseInt(s1.get(2));
		// int total2 = Integer.parseInt(s1.get(2)) + Integer.parseInt(s1.get(2)) +
		// Integer.parseInt(s1.get(2));
		// return Integer.compare(total1, total2);
		// }).forEach(meritStudents1::add);
		// return new LinkedHashSet<>(meritStudents1);
		return new LinkedHashSet<>(meritStudents);
	}

	/**
	 * @apiNote Methods 10
	 */
	@Override
	public int numberOfStudent() {
		return (int) ModelOperation.getNumberInformation(
				"SELECT COUNT(*) FROM " + Connectivity.TABLE_NAME + "");
	}

	/**
	 * @apiNote Methods 11
	 */
	@Override
	public LinkedHashSet<ArrayList<String>> getFailedStudentsList() {
		String query = "SELECT *, (math + chemistry + physics) AS total_marks FROM "
				+ Connectivity.TABLE_NAME
				+ " WHERE math < 33 OR chemistry < 33 OR physics < 33 ORDER BY rollNo";

		// For logically

		// Set<ArrayList<String>> students = getAll();
		// List<ArrayList<String>> meritStudents1 = new ArrayList<>();
		// students.stream().filter(student -> {
		// int math = Integer.parseInt(student.get(2));
		// int chemistry = Integer.parseInt(student.get(3));
		// int physics = Integer.parseInt(student.get(4));
		// return math < 33 || chemistry < 33 || physics < 33;
		// }).sorted((s1, s2) -> {
		// int total1 = Integer.parseInt(s1.get(2)) + Integer.parseInt(s1.get(2)) +
		// Integer.parseInt(s1.get(2));
		// int total2 = Integer.parseInt(s1.get(2)) + Integer.parseInt(s1.get(2)) +
		// Integer.parseInt(s1.get(2));
		// return Integer.compare(total1, total2);
		// }).forEach(meritStudents1::add);

		return new LinkedHashSet<>(ModelOperation.getSpecific(query));
	}

	/**
	 * @apiNote Methods 12
	 */
	@Override
	public ArrayList<ArrayList<String>> getAbsenties() {
		String query = "SELECT * FROM " + Connectivity.TABLE_NAME
				+ " WHERE math = -1 OR chemistry = -1 OR physics = -1 ORDER BY (math + chemistry + physics)";
		ArrayList<ArrayList<String>> absentStudents = ModelOperation.get(query, 5);

		// Set<ArrayList<String>> students = getAll();
		// ArrayList<ArrayList<String>> absentStudent = new ArrayList<>();
		// students.stream().filter(student -> {
		// int math = Integer.parseInt(student.get(2));
		// int chemistry = Integer.parseInt(student.get(3));
		// int physics = Integer.parseInt(student.get(4));
		// return math == -1 || chemistry == -1 || physics == -1;
		// }).sorted((s1, s2) -> {
		// int total1 = Integer.parseInt(s1.get(2)) + Integer.parseInt(s1.get(3)) +
		// Integer.parseInt(s1.get(4));
		// int total2 = Integer.parseInt(s1.get(2)) + Integer.parseInt(s1.get(3)) +
		// Integer.parseInt(s1.get(4));
		// return Integer.compare(total1, total2);
		// }).forEach(s -> absentStudent.add(new ArrayList<>(s.subList(0, 5))));
		// return absentStudent;

		return absentStudents;
	}

	/**
	 * @apiNote Methods 13
	 */
	@Override
	public ArrayList<ArrayList<String>> getTopper() {
		String query = "SELECT *, (math + chemistry + physics) AS total_marks FROM "
				+ Connectivity.TABLE_NAME
				+ " WHERE (math + chemistry + physics) = (SELECT MAX(math + chemistry + physics) FROM "
				+ Connectivity.TABLE_NAME + ")";
		return ModelOperation.getSpecific(query);
	}

	/**
	 * @apiNote Methods 14
	 */
	@Override
	public String[][] getLowestMarkStudents() {
		String query = "SELECT *, (math + chemistry + physics) AS total_marks FROM "
				+ Connectivity.TABLE_NAME
				+ " WHERE (math + chemistry + physics) = (SELECT MIN(math + chemistry + physics) FROM "
				+ Connectivity.TABLE_NAME + ")";
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
		String query = "SELECT *, (math + chemistry + physics) AS total_marks FROM "
				+ Connectivity.TABLE_NAME
				+ " WHERE math >= 33 AND chemistry >= 33 AND physics >= 33 ORDER BY rollNo";
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
		String query = "SELECT AVG((math + chemistry + physics) / 3.0) AS average_marks FROM "
				+ Connectivity.TABLE_NAME + ";";
		return ModelOperation.getNumberInformation(query);
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
		throw new UnsupportedOperationException("Unimplemented method 'getCutOff'");
	}

	/**
	 * @apiNote Methods 19
	 */
	@Override
	public int getNumberOfBoysPass() {
		return (int) ModelOperation.getNumberInformation("SELECT COUNT(*) FROM "
				+ Connectivity.TABLE_NAME
				+ " WHERE gender = 'M' AND math >= 33 AND chemistry >= 33 AND physics >= 33");
	}

	/**
	 * @apiNote Methods 20
	 */
	@Override
	public int getNumberOfGirlsPass() {
		return (int) ModelOperation.getNumberInformation("SELECT COUNT(*) FROM "
				+ Connectivity.TABLE_NAME
				+ " WHERE gender = 'F' AND math >= 33 AND chemistry >= 33 AND physics >= 33");
	}

	/**
	 * @apiNote Methods 21
	 */
	@Override
	public char getGradeOfStudent(String enrollment) {
		ArrayList<String> student = get(enrollment);

		if (!student.isEmpty()) {
			double averageMarks = (Integer.parseInt(student.get(2))
					+ Integer.parseInt(student.get(3)) + Integer.parseInt(student.get(4)))
					/ 3.0;
			if (averageMarks >= 90)
				return 'A';
			else if (averageMarks >= 80)
				return 'B';
			else if (averageMarks >= 70)
				return 'C';
			else if (averageMarks >= 60)
				return 'D';
			else
				return 'E';
		}
		return ' ';
	}

	/**
	 * @apiNote Methods 22
	 */
	@Override
	public int getTotalNumberOfGirls() {
		return (int) ModelOperation.getNumberInformation("SELECT COUNT(*) FROM "
				+ Connectivity.TABLE_NAME + " WHERE gender='F'");
	}

	/**
	 * @apiNote Methods 23
	 */
	@Override
	public int getTotalNumberOfBoys() {
		return (int) ModelOperation.getNumberInformation("SELECT COUNT(*) FROM "
				+ Connectivity.TABLE_NAME + " WHERE gender='M'");
	}

	/**
	 * @apiNote Methods 24
	 */
	@Override
	public double getAverageResultOfGirls() {
		String query = "SELECT AVG((math + chemistry + physics) / 3.0 ) AS average_marks FROM "
				+ Connectivity.TABLE_NAME + " WHERE gender = 'F';";
		return ModelOperation.getNumberInformation(query);
	}

	/**
	 * @apiNote Methods 25
	 */
	@Override
	public double getAverageResultOfBoys() {
		String query = "SELECT AVG((math + chemistry + physics) / 3.0 ) AS average_marks FROM "
				+ Connectivity.TABLE_NAME + " WHERE gender = 'M';";
		return ModelOperation.getNumberInformation(query);
	}

}
