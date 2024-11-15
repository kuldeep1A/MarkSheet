package com.marksheet.management;

import com.marksheet.UI.Colors;
import com.marksheet.UI.Content;
import com.marksheet.UI.Display;
import com.marksheet.model.Connectivity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Management all the Marksheet of all student's
 * 
 * @apiNote Reliable
 * @apiNote Easy
 */
public class Management implements Colors, Content {
  private Scanner sc = new Scanner(System.in);

  private boolean connect() {
    return Connectivity.connect();
  }

  private void isContinue() {
    if (!Validation.confirm("to continue this MarkSheet Management or not ?")) {
      Display.waterMark();
      System.exit(0);
    }
  }

  private void operationByCommand() {
    MarkSheetOperation marksheetOperation = new MarkSheetOperation();
    while (true) {
      Display.printRules(8);
      try {
        String command = sc.nextLine().toLowerCase().trim();
        switch (command) {
          case "1":
            marksheetOperation.add(new Marksheet());
            isContinue();
            break;
          case "2":
            marksheetOperation.update(Validation.checkEnrollment(false));
            isContinue();
            break;
          case "3":
            marksheetOperation.updateAll(new Marksheet());
            isContinue();
            break;
          case "4":
            marksheetOperation.deleteByRollNo(Validation.checkEnrollment(false));
            isContinue();
            break;
          case "5":
            marksheetOperation.deleteByEmailId(Validation.checkEmail());
            isContinue();
            break;
          case "6":
            if (marksheetOperation.deleteAll())
              start();
            isContinue();
            break;
          case "7":
            ArrayList<String> studentInfo = marksheetOperation
                .get(Validation.checkEnrollment(false));
            if (!studentInfo.isEmpty())
              Display.printInformation(studentInfo, HEADER);
            isContinue();
            break;
          case "8":
            Set<ArrayList<String>> studentsInfo = marksheetOperation.getAll();
            if (!studentsInfo.isEmpty()) {
              Display.printMessage("\t\tTotal Students are : ",
                  studentsInfo.size());
              Display.printInformation(studentsInfo);
            }
            isContinue();
            break;
          case "9":
            LinkedHashSet<ArrayList<String>> meritStudents = marksheetOperation
                .getMeritList();
            if (!meritStudents.isEmpty()) {
              Display.printMessage(
                  "\t\tThe Marit List of student who have 80% above percentile :");
              Display.printInformation(meritStudents, COLUMN_WIDTHS, HEADER);
            }
            isContinue();
            break;
          case "10":
            Display.printMessage("\t\tThe total number of student are: ",
                marksheetOperation.numberOfStudent());
            isContinue();
            break;
          case "11":
            LinkedHashSet<ArrayList<String>> failedStudent = marksheetOperation
                .getFailedStudentsList();
            if (!failedStudent.isEmpty()) {
              Display.printMessage("\t\tThe List of students who Failed : "
                  + failedStudent.size());
              Display.printInformation(failedStudent);
            } else
              Display.printMessage(BLUE + "\t\tAll student are hoshiyar :)");
            isContinue();
            break;
          case "12":
            ArrayList<ArrayList<String>> absentStudents = marksheetOperation
                .getAbsenties();
            if (!absentStudents.isEmpty()) {
              Display.printMessage("\t\tThe List of students who absent : "
                  + absentStudents.size());
              Display.printInformation(absentStudents, COLUMN_WIDTHS, HEADER);
            }
            isContinue();
            break;
          case "13":
            ArrayList<ArrayList<String>> topperStudents = marksheetOperation
                .getTopper();
            if (!topperStudents.isEmpty()) {
              Display.printMessage(
                  "\t\tThe List of topper students : " + topperStudents.size());
              Display.printInformation(topperStudents,
                  COLUMN_WIDTHS_TOTAL_MARKS, HEADER_TOTAL_MARKS);
            }
            isContinue();
            break;
          case "14":
            String[][] lowestStudents = marksheetOperation
                .getLowestMarkStudents();
            if (lowestStudents != null) {
              Display.printMessage(
                  "\t\tThe List of students who have lowest marks : "
                      + lowestStudents.length);
              Display.printInformation(lowestStudents,
                  COLUMN_WIDTHS_TOTAL_MARKS, HEADER_TOTAL_MARKS);
            } else
              Display.printMessage(BLUE + "\t\tAll student are hoshiyar :)");
            isContinue();
            break;
          case "15":
            String[][] passedStudents = marksheetOperation.getPassedStdents();
            if (passedStudents != null) {
              Display.printMessage(
                  "\t\tThe List of passed students: " + passedStudents.length);
              Display.printInformation(passedStudents,
                  COLUMN_WIDTHS_TOTAL_MARKS, HEADER_TOTAL_MARKS);
            } else
              Display.printMessage(BLUE + "\t\tAll student are hoshiyar :)");
            isContinue();
            break;
          case "16":
            Display.printMessage("\t\tThe average result of Class are : ",
                marksheetOperation.getAverageResultOfClass());
            isContinue();
            break;
          case "17":
            List<ArrayList<String>> atktStudents = marksheetOperation
                .getATKTStudents();
            if (!atktStudents.isEmpty()) {
              Display.printMessage(
                  "\t\tThe List of students who ATKT(Allowed to keep terms) : "
                      + atktStudents.size());
              Display.printInformation(atktStudents);
            } else
              Display.printMessage(BLUE + "\t\tAll student are hoshiyar :)");
            isContinue();
            break;
          case "18":
            Display.printMessage("", marksheetOperation.getCutOff());
            isContinue();
            break;
          case "19":
            Display.printMessage(
                "\t\tThe total number of Boys students who Passed : ",
                marksheetOperation.getNumberOfBoysPass());
            isContinue();
            break;
          case "20":
            Display.printMessage(
                "\t\tThe total number of Girls students who Passed : ",
                marksheetOperation.getNumberOfGirlsPass());
            isContinue();
            break;
          case "21":
            char Grade = marksheetOperation
                .getGradeOfStudent(Validation.checkEnrollment(false));
            if (Grade != ' ')
              Display.printMessage("\t\tGrade -> " + Grade);
            isContinue();
            break;
          case "22":
            Display.printMessage("\t\tThe total number of Girls students are: ",
                marksheetOperation.getTotalNumberOfGirls());
            isContinue();
            break;
          case "23":
            Display.printMessage("\t\tThe total number of Boys students are: ",
                marksheetOperation.getTotalNumberOfBoys());
            isContinue();
            break;
          case "24":
            Display.printMessage("\t\tThe average result of all Girls are: ",
                marksheetOperation.getAverageResultOfGirls());
            isContinue();
            break;
          case "25":
            Display.printMessage("\t\tThe average result of all Boys are: ",
                marksheetOperation.getAverageResultOfBoys());
            isContinue();
            break;
          case "26":
            Display.printMessage("\n\n");
            connect();
            break;
          case "\\-1":
            if (Validation.confirm("to terminat MarkSheet-Managment")) {
              Display.waterMark();
              System.exit(0);
            }
            break;
          case "\\exit":
            if (Validation.confirm("to terminat MarkSheet-Managment")) {
              Display.waterMark();
              System.exit(0);
            }
            break;
          default:
            Display.printMessage(
                RED + "\t\tError: Follow the commands rules strictly!" + RESET);
            break;
        }
      } catch (InputMismatchException e) {
        Display.printMessage(
            RED + "\t\tError: Follow the commands rules strictly!" + RESET);
        sc.nextLine();
        Display.printMessage("Eror-1");
        // e.printStackTrace();
      }
    }
  }

  public void start() {
    Display.printInformation(CYAN2 + WELCOME + RESET, 0, WELCOME.length() + 26);
    try {
      Connectivity.login();
      operationByCommand();
    } finally {
      Display.waterMark();
      try {
        sc.close();
        Connectivity.conn.close();
        Connectivity.stmt.close();
        Connectivity.pstmt.close();
        Connectivity.resultSet.close();
      } catch (NullPointerException e) {
        Display.printMessage(RED + "\n\t\tNull Pointer Exception:" + RESET);
        Display.printMessage("Eror-5.1");
        // e.printStackTrace();
        System.exit(0);
      } catch (SQLException e) {
        Display.printMessage(RED + "\n\t\tSQL Exception: " + RESET);
        Display.printMessage("Eror-6");
        // e.printStackTrace();
        System.exit(0);
      }
    }
  }
}
