package com.marksheet.model;

import com.marksheet.UI.Display;
import com.marksheet.management.Marksheet;
import com.marksheet.management.Validation;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Model Operation perform SQL operation to manipulation or intracted with
 * databases
 * 
 * @apiNote SQL Operation
 * @apiNote Model
 */
public class ModelOperation extends Connectivity {
  private static void addInto(ArrayList<String> info, ResultSet resultSet)
      throws SQLException {
    info.add(resultSet.getString("rollNo"));
    info.add(resultSet.getString("name"));
    info.add(Integer.toString(resultSet.getInt("math")));
    info.add(Integer.toString(resultSet.getInt("chemistry")));
    info.add(Integer.toString(resultSet.getInt("physics")));
    info.add(resultSet.getDate("DOB").toString());
    info.add(resultSet.getString("email"));
    info.add(resultSet.getString("gender"));
  }

  public static void insert(Marksheet marksheet) {
    String insertSQL = "INSERT INTO " + Connectivity.TABLE_NAME
        + " (rollNo, name, math, chemistry, physics, DOB, email, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try {
      pstmt = Connectivity.conn.prepareStatement(insertSQL);
      pstmt.setString(1, marksheet.getRollNo());
      pstmt.setString(2, marksheet.getName());
      pstmt.setInt(3, marksheet.getMath());
      pstmt.setInt(4, marksheet.getChemistry());
      pstmt.setInt(5, marksheet.getPhysics());
      pstmt.setDate(6, Date.valueOf(marksheet.getDOB()));
      pstmt.setString(7, marksheet.getEmail());
      pstmt.setString(8, String.valueOf(marksheet.getGender()));

      pstmt.executeUpdate();
      Display.printMessage(
          "\t\tThe data (Information) of the student's with Enrollment " + BLUE
              + "`" + marksheet.getRollNo() + "`" + RESET
              + " is successfully added to the result.");
    } catch (SQLIntegrityConstraintViolationException e) {
      String errorMessage = e.getMessage();
      if (errorMessage
          .contains("for key '" + Connectivity.TABLE_NAME + ".PRIMARY'")) {
        Display
            .printMessage("\t\t" + "Student is already exists with Enrollment "
                + BLUE + "`" + marksheet.getRollNo() + "`" + RESET
                + ", Please enter other Enrollment. and try again.");
      } else if (errorMessage
          .contains("for key '" + Connectivity.TABLE_NAME + ".email'")) {
        Display.printMessage("\t\t" + "Student is already exists with Email "
            + BLUE + "`" + marksheet.getEmail() + "`" + RESET
            + ", Please enter other Email. and try again.");
      } else {
        Display.printMessage(
            "\t\tAn Unkown constraint violation occurred: " + errorMessage);
      }
    } catch (SQLException e) {
      Display.printMessage("\t\tSQL Exception-> Developer Error");
      Display.printMessage("Eror-6");
      // e.printStackTrace();
    }

  }

  public static ResultSet find(String data, String field) {
    String checkQuery = "SELECT COUNT(*) FROM " + Connectivity.TABLE_NAME
        + " WHERE " + field + " = ?";
    try {
      pstmt = conn.prepareStatement(checkQuery);
      pstmt.setString(1, data);
      return pstmt.executeQuery();
    } catch (Exception e) {
      Display.printMessage(RED + "\t\tSQL Exception-> " + e.getMessage() + RESET
          + BLUE + " -> Developer Error" + RESET);
      Display.printMessage("Eror-7");
      // e.printStackTrace();
    }
    return null;
  }

  public static boolean isExists(String data, String field) {
    try {
      resultSet = find(data, field);
      if (resultSet != null && resultSet.next() && resultSet.getInt(1) > 0)
        return true;
    } catch (SQLException e) {
      Display.printMessage(RED + "\t\tSQL Exception-> " + e.getMessage() + RESET
          + BLUE + " -> Developer Error" + RESET);
      Display.printMessage("Eror-8.1");
      // e.printStackTrace();
    }
    return false;
  }

  public static void delete(String data, String field) {
    String deleteQuery = "DELETE FROM " + Connectivity.TABLE_NAME + " WHERE "
        + field + " = ?";
    try {
      resultSet = find(data, field);
      if (resultSet != null && resultSet.next() && resultSet.getInt(1) > 0) {
        pstmt = conn.prepareStatement(deleteQuery);
        pstmt.setString(1, data);
        if (Validation
            .confirm("to delete record who have " + field + " with " + data)) {
          if (pstmt.executeUpdate() > 0)
            Display.printMessage(
                "\t\tThe data (Information) of the student's with Enrollment "
                    + BLUE + "`" + data + "`" + RESET
                    + " is deleted successfully");
        }
      } else
        Display.printMessage(
            "\t\tThe data (Information) of the student's are not found in over database!!"
                + RESET);
    } catch (SQLException e) {
      Display.printMessage(RED + "\t\tSQL Exception-> " + e.getMessage() + RESET
          + BLUE + " -> Developer Error" + RESET);
      Display.printMessage("Eror-8");
      // e.printStackTrace();
    }
  }

  public static void deleteTableData() {
    // String deleteQuery = "DROP TABLE IF EXISTS " + Connectivity.table +"";
    String deleteQuery = "DELETE FROM " + Connectivity.TABLE_NAME + "";
    try {
      stmt.executeUpdate(deleteQuery);
    } catch (SQLException e) {
      Display.printMessage("delete table");
      Display.printMessage("Eror-9");
      // e.printStackTrace();
    }
  }

  public static ArrayList<String> get(String enrollment) {
    String getQuery = "SELECT * FROM " + Connectivity.TABLE_NAME
        + " WHERE rollNo = ?";
    ArrayList<String> info = new ArrayList<>();
    try {
      pstmt = conn.prepareStatement(getQuery);
      pstmt.setString(1, enrollment);
      resultSet = pstmt.executeQuery();
      if (resultSet.next())
        addInto(info, resultSet);
      else
        Display.printMessage(RED + "\t\tData not found: " + RESET
            + "Student's information with " + CYAN + "`" + enrollment + "`"
            + RESET + " is not found in over database.");
    } catch (SQLException e) {
      Display.printMessage(RED + "\t\tSQL Exception-> " + e.getMessage() + RESET
          + BLUE + " -> Developer Error" + RESET);
      Display.printMessage("Eror-10");
      // e.printStackTrace();
    }
    return info;
  }

  public static ArrayList<ArrayList<String>> get(String query, int column) {
    ArrayList<ArrayList<String>> students = new ArrayList<>();
    try {
      resultSet = stmt.executeQuery(query);
      while (resultSet.next()) {
        ArrayList<String> student = new ArrayList<>();
        addInto(student, resultSet);
        students.add(new ArrayList<>(student.subList(0, column)));
      }
    } catch (SQLException e) {
      Display.printMessage(RED + "\t\tSQL Exception-> " + e.getMessage() + RESET
          + BLUE + " -> Developer Error" + RESET);
      Display.printMessage("Eror-11");
      // e.printStackTrace();
    }
    return students;
  }

  public static ArrayList<ArrayList<String>> getSpecific(String query) {
    ArrayList<ArrayList<String>> students = new ArrayList<>();
    try {
      resultSet = stmt.executeQuery(query);

      while (resultSet.next()) {
        ArrayList<String> student = new ArrayList<>();
        addInto(student, resultSet);
        student.add(resultSet.getString("total_marks"));
        students.add(student);
      }
    } catch (SQLException e) {
      Display.printMessage(RED + "\t\tSQL Exception-> " + e.getMessage() + RESET
          + BLUE + " -> Developer Error" + RESET);
      Display.printMessage("Eror-11");
      // e.printStackTrace();
    }
    return students;
  }

  public static LinkedHashSet<ArrayList<String>> getAll() {
    LinkedHashSet<ArrayList<String>> students = new LinkedHashSet<>();
    String getAllQuery = "SELECT * FROM " + Connectivity.TABLE_NAME
        + " ORDER BY rollNo";
    try {
      resultSet = stmt.executeQuery(getAllQuery);
      while (resultSet.next()) {
        ArrayList<String> student = new ArrayList<>();
        addInto(student, resultSet);
        students.add(student);
      }
      return students;
    } catch (SQLException e) {
      Display.printMessage(RED + "\t\tSQL Exception-> " + e.getMessage() + RESET
          + BLUE + " -> Developer Error" + RESET);
      Display.printMessage("Eror-12");
      // e.printStackTrace();
    }
    return null;
  }

  public static <E> void update(String enrollment, String field, E data) {
    String dataStr = data.toString();
    String updateQuery = "UPDATE " + Connectivity.TABLE_NAME + " SET " + field
        + " = ? WHERE rollNo = ?";
    try {
      pstmt = conn.prepareStatement(updateQuery);
      pstmt.setString(1, dataStr);
      pstmt.setString(2, enrollment);
      pstmt.executeUpdate();
      Display.printMessage(
          GREEN + "\t\tUpdated: " + RESET + "Successfully update data `" + BLUE
              + data + RESET + "` of the student's with Enrollment `" + BLUE
              + enrollment + RESET + "`");
      Display.printInformation(get(enrollment), HEADER);
    } catch (Exception e) {
      Display.printMessage(RED + "\t\tSQL Exception-> " + e.getMessage() + RESET
          + BLUE + " -> Developer Error" + RESET);
      Display.printMessage("Eror-13");
      // e.printStackTrace();
    }
  }

  public static ArrayList<String> update(Marksheet marksheet) {
    String updateQuery = "UPDATE " + Connectivity.TABLE_NAME
        + " SET name = ?, email = ?, DOB = ?, gender = ?, math = ?, chemistry = ?, physics = ? WHERE rollNo = ?";
    try {
      pstmt = conn.prepareStatement(updateQuery);
      pstmt.setString(1, marksheet.getName());
      pstmt.setString(2, marksheet.getEmail());
      pstmt.setDate(3, Date.valueOf(marksheet.getDOB()));
      pstmt.setString(4, String.valueOf(marksheet.getGender()));
      pstmt.setInt(5, marksheet.getMath());
      pstmt.setInt(6, marksheet.getChemistry());
      pstmt.setInt(7, marksheet.getPhysics());
      pstmt.setString(8, marksheet.getRollNo());
      pstmt.executeUpdate();
      return get(marksheet.getRollNo());
    } catch (Exception e) {
      Display.printMessage(RED + "\t\tSQL Exception-> " + e.getMessage() + RESET
          + BLUE + " -> Developer Error" + RESET);
      Display.printMessage("Eror-14");
      // e.printStackTrace();
    }
    return null;
  }

  public static double getNumberInformation(String getQuery) {
    try {
      resultSet = stmt.executeQuery(getQuery);
      if (resultSet.next())
        return resultSet.getDouble(1);
      else {
        Display.printMessage(RED + "\t\tDate not found" + RESET);
      }

    } catch (SQLException e) {
      Display.printMessage(RED + "\t\tSQL Exception-> " + e.getMessage() + RESET
          + BLUE + " -> Developer Error" + RESET);
      Display.printMessage("Eror-15");
      // e.printStackTrace();
    }
    return 0;
  }
}
