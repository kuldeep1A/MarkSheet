package com.marksheet.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import com.marksheet.UI.Content;
import com.marksheet.UI.Display;
import com.marksheet.management.Marksheet;

/**
 * Model Operation perform SQL operation to manipulation or intracted with
 * databases
 * 
 * @apiNote SQL Operation
 * @apiNote Model
 */
public class ModelOperation extends Connectivity implements Content {
  private static void addInto(ArrayList<String> info, ResultSet resultSet) throws SQLException {
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
    String insertSQL = "INSERT INTO result1 (rollNo, name, math, chemistry, physics, DOB, email, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
      System.out.println(
          "\t\tThe data (Information) of the student's with Enrollment " + BLUE + "`" + marksheet.getRollNo() + "`"
              + RESET + " is successfully added to the result.");
    } catch (SQLIntegrityConstraintViolationException e) {
      System.out.println("\t\t" + "Student is already exists with Enrollment " + BLUE + "`"
          + marksheet.getRollNo() + "`" + RESET + ", Please enter other Enrollment. and try again.");
    } catch (SQLException e) {
      System.out.println("\t\tSQL Exception-> Developer Error");
      e.printStackTrace();
    }
  }

  public static ResultSet find(String data, String field) {
    String checkQuery = "SELECT COUNT(*) FROM result1 WHERE " + field + " = ?";
    try {
      pstmt = conn.prepareStatement(checkQuery);
      pstmt.setString(1, data);
      return pstmt.executeQuery();
    } catch (Exception e) {
      System.err.println(RED + "\t\tSQL Exception-> " + e.getMessage() + RESET + BLUE + " -> Developer Error" + RESET);
      e.printStackTrace();
    }
    return null;
  }

  public static void delete(String data, String field) {
    String deleteQuery = "DELETE FROM result1 WHERE " + field + " = ?";
    try {
      resultSet = find(data, field);
      if (resultSet != null && resultSet.next() && resultSet.getInt(1) > 0) {
        pstmt = conn.prepareStatement(deleteQuery);
        pstmt.setString(1, data);
        if (pstmt.executeUpdate() > 0)
          System.out.println("\t\tThe data (Information) of the student's with Enrollment " + BLUE + "`" + data + "`"
              + RESET + " is deleted successfully");
      } else
        System.out.println("\t\tThe data (Information) of the student's are not found in over database!!" + RESET);
    } catch (SQLException e) {
      System.err.println(RED + "\t\tSQL Exception-> " + e.getMessage() + RESET + BLUE + " -> Developer Error" + RESET);
      e.printStackTrace();
    }
  }

  public static void deleteTableData() {
    // String deleteQuery = "DROP TABLE IF EXISTS result1";
    String deleteQuery = "DELETE FROM result1";
    try {
      stmt.executeUpdate(deleteQuery);
    } catch (SQLException e) {
      System.err.println("delete table");
      e.printStackTrace();
    }
  }

  public static ArrayList<String> get(String enrollment) {
    String getQuery = "SELECT * FROM result1 WHERE rollNo = ?";
    ArrayList<String> info = new ArrayList<>();
    try {
      pstmt = conn.prepareStatement(getQuery);
      pstmt.setString(1, enrollment);
      resultSet = pstmt.executeQuery();
      if (resultSet.next()) {
        addInto(info, resultSet);
      } else {
        System.out
            .println(
                RED + "\t\tData not found: " + RESET + "Student's information with " + CYAN + "`" + enrollment + "`"
                    + RESET + " is not found in over database.");
      }
    } catch (SQLException e) {
      System.err.println(RED + "\t\tSQL Exception-> " + e.getMessage() + RESET + BLUE + " -> Developer Error" + RESET);
      e.printStackTrace();
    }
    return info;
  }

  public static ArrayList<ArrayList<String>> getSpecific() {
    String query = "SELECT *, (math + chemistry + physics) AS total_marks FROM result1 WHERE (math + chemistry + physics) = (SELECT MAX(math + chemistry + physics) FROM result1)";
    ArrayList<ArrayList<String>> students = new ArrayList<>();
    try {
      resultSet = stmt.executeQuery(query);
      resultSetMetaData = resultSet.getMetaData();

      while (resultSet.next()) {
        ArrayList<String> student = new ArrayList<>();
        addInto(student, resultSet);
        student.add(resultSet.getString("total_marks"));
        students.add(student);
      }
    } catch (SQLException e) {
      System.err.println(RED + "\t\tSQL Exception-> " + e.getMessage() + RESET + BLUE + " -> Developer Error" + RESET);
      e.printStackTrace();
    }
    return students;
  }

  public static LinkedHashSet<ArrayList<String>> getAll() {
    LinkedHashSet<ArrayList<String>> students = new LinkedHashSet<>();
    String getAllQuery = "SELECT * FROM result1 ORDER BY rollNo";
    try {
      resultSet = stmt.executeQuery(getAllQuery);
      resultSetMetaData = resultSet.getMetaData();
      while (resultSet.next()) {
        ArrayList<String> student = new ArrayList<>();
        addInto(student, resultSet);
        students.add(student);
      }
      return students;
    } catch (SQLException e) {
      System.err.println(RED + "\t\tSQL Exception-> " + e.getMessage() + RESET + BLUE + " -> Developer Error" + RESET);
      e.printStackTrace();
    }
    return null;
  }

  public static <E> void update(String enrollment, String field, E data) {
    String dataStr = data.toString();
    String updateQuery = "UPDATE result1 SET " + field + " = ? WHERE rollNo = ?";
    try {
      pstmt = conn.prepareStatement(updateQuery);
      pstmt.setString(1, dataStr);
      pstmt.setString(2, enrollment);
      pstmt.executeUpdate();
      System.out.println(GREEN + "\t\tUpdated: " + RESET + "Successfully update data `" + BLUE + data + RESET
          + "` of the student's with Enrollment `" + BLUE + enrollment + RESET + "`");
      Display.printInformation(get(enrollment), HEADER);
    } catch (Exception e) {
      System.err.println(RED + "\t\tSQL Exception-> " + e.getMessage() + RESET + BLUE + " -> Developer Error" + RESET);
      e.printStackTrace();
    }
  }

  public static ArrayList<String> update(Marksheet marksheet) {
    String updateQuery = "UPDATE result1 SET name = ?, email = ?, DOB = ?, gender = ?, math = ?, chemistry = ?, physics = ? WHERE rollNo = ?";
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
      System.err.println(RED + "\t\tSQL Exception-> " + e.getMessage() + RESET + BLUE + " -> Developer Error" + RESET);
      e.printStackTrace();
    }
    return null;
  }

  public static int getNumberInformation(String getQuery) {
    try {
      resultSet = stmt.executeQuery(getQuery);
      if (resultSet.next())
        return resultSet.getInt(1);
      else {
        System.err.println(RED + "\t\tDate not found" + RESET);
      }

    } catch (SQLException e) {
      System.err.println(RED + "\t\tSQL Exception-> " + e.getMessage() + RESET + BLUE + " -> Developer Error" + RESET);
      e.printStackTrace();
    }
    return 0;
  }
}
