package com.marksheet.management;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import com.marksheet.UI.Colors;
import com.marksheet.UI.Content;
import com.marksheet.UI.Display;
import com.marksheet.model.Connectivity;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

/**
 * Management all the Marksheet of all student's
 * 
 * @apiNote - Reliable, Easy
 */
public class Management implements Colors, Content {
  private Scanner sc = new Scanner(System.in);

  private boolean connect() throws ClassNotFoundException, SQLException {
    return Connectivity.connect();
  }

  private final void waterMark() {
    System.out.println("\n\t\tThanks for use Marksheet Management Application.\n\t\tCreated By: " + BLUE
        + "https://kuldeep1a.web.app/" + RESET);
  }

  private void operationByCommand() {
    MarkSheetOperation marksheetOperation = new MarkSheetOperation();
    outerLoop: while (true) {
      System.out.println(YELLOW + COMMANDS_DETAILS + RESET);
      System.out.println(CYAN + COMMANDS_RULES + RESET);
      System.out.print("\n\t\tCommand -> ");
      try {
        String command = sc.nextLine();
        switch (command) {
          case "1":
            marksheetOperation.add(new Marksheet());
            break;
          case "2":
            marksheetOperation.update(Validation.checkEnrollment());
            break;
          case "3":
            marksheetOperation.updateAll(new Marksheet());
            break;
          case "4":
            marksheetOperation.deleteByRollNo(Validation.checkEnrollment());
            break;
          case "5":
            marksheetOperation.deleteByEmailId(Validation.checkEmail());
            break;
          case "6":
            marksheetOperation.deleteAll();
            break;
          case "7":
            ArrayList<String> studentInfo = marksheetOperation.get(Validation.checkEnrollment());
            if (!studentInfo.isEmpty())
              Display.printInformation(studentInfo, HEADER);
            break;
          case "8":
            Set<ArrayList<String>> studentsInfo = marksheetOperation.getAll();
            if (!studentsInfo.isEmpty())
              Display.printInformation(studentsInfo);
            break;
          case "9":
            LinkedHashSet<ArrayList<String>> meritStudents = marksheetOperation.getMeritList();
            if (!meritStudents.isEmpty()) {
              Display.printMessage("\t\tThe Marit List :");
              Display.printInformation(meritStudents);
            }
            break;
          case "10":
            Display.printMessage("\t\tThe total number of student are: ", marksheetOperation.numberOfStudent());
            break;
          case "11":
            LinkedHashSet<ArrayList<String>> failedStudent = marksheetOperation.getFailedStudentsList();
            if (!failedStudent.isEmpty()) {
              Display.printMessage("\t\tThe List of students who Failed : " + failedStudent.size());
              Display.printInformation(failedStudent);
            } else
              Display.printMessage(BLUE + "\t\tAll student are hoshiyar :)");
            break;
          case "12":
            ArrayList<ArrayList<String>> absentStudents = marksheetOperation.getAbsenties();
            if (!absentStudents.isEmpty()) {
              Display.printMessage("\t\tThe List of students who absent : " + absentStudents.size());
              Display.printInformation(absentStudents, COLUMN_WIDTHS_MARKS, HEADER_MARKS);
            }
            break;
          case "13":
            ArrayList<ArrayList<String>> topperStudents = marksheetOperation.getTopper();
            if (!topperStudents.isEmpty()) {
              Display.printMessage("\t\tThe List of topper students : " + topperStudents.size());
              Display.printInformation(topperStudents, COLUMN_WIDTHS_TOPPER, HEADER_TOPPER);
            }
            break;
          case "19":
            Display.printMessage("\t\tThe total number of Boys students who Passed : ",
                marksheetOperation.getNumberOfBoysPass());
            break;
          case "20":
            Display.printMessage("\t\tThe total number of Girls students who Passed : ",
                marksheetOperation.getNumberOfGirlsPass());
            break;
          case "22":
            Display.printMessage("\t\tThe total number of Girls students are: ",
                marksheetOperation.getTotalNumberOfGirls());
            break;
          case "23":
            Display.printMessage("\t\tThe total number of Boys students are: ",
                marksheetOperation.getTotalNumberOfBoys());
            break;
          case "-1":
            waterMark();
            System.exit(0);
            break outerLoop;
          case "exit":
            waterMark();
            System.exit(0);
            break outerLoop;
          default:
            System.err.println(RED + "\t\tError: Follow the commands rules strictly!" + RESET);
            break;
        }
      } catch (InputMismatchException e) {
        System.err.println(RED + "\t\tError: Follow the commands rules strictly!" + RESET);
        sc.nextLine();
        e.printStackTrace();
      }
    }
  }

  public void start() {
    System.out.println(WELCOME);

    try {
      connect();
      operationByCommand();
    } catch (ClassNotFoundException e) {
      System.err.println(
          RED + "\n\t\tError DBMS: MySql Driver not found, please connect the connector for creating a brige." + RESET);
      e.printStackTrace();
    } catch (CommunicationsException e) {
      System.err.println(RED + "\n\t\tCommunications Exception: " + RESET
          + "It cause because dbms server may not start or dbms are not running.");
      waterMark();
      System.exit(0);
    } catch (SQLException e) {
      System.err.println(RED + "\n\t\tSQL Exception: " + RESET);
      waterMark();
      e.printStackTrace();
      System.exit(0);

    } finally {
      waterMark();
      try {
        sc.close();
        Connectivity.conn.close();
        Connectivity.stmt.close();
        Connectivity.pstmt.close();
      } catch (NullPointerException e) {
        System.err.println(RED + "\n\t\tNull Pointer Exception:" + RESET);
        System.exit(0);
      } catch (SQLException e) {
        System.err.println(RED + "\n\t\tSQL Exception: " + RESET);
        e.printStackTrace();
        System.exit(0);
      }
    }
  }
}
