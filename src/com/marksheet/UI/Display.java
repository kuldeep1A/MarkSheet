package com.marksheet.UI;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Display message or Document to the console
 */
public class Display implements Content, Colors {

  public static void printFormattedLine(String text, int boxWidth) {
    int padding = boxWidth - text.length() - 9;
    System.out.printf("\t\t|\t%s%" + padding + "s|\n", text, "");
  }

  public static void printBorder(String message) {
    System.out.println("\t\t+" + "-".repeat(message.length() + 6) + "+");
  }

  public static void printBorder(int width) {
    System.out.println("\t\t+" + "-".repeat(width - 2) + "+");
  }

  private static void printBorder(int[] columnWidths) {
    System.out.print("\t\t+");
    for (int width : columnWidths) {
      System.out.print("-".repeat(width + 2) + "+");
    }
    System.out.println();
  }

  private static void printRow(ArrayList<String> rowData, int[] columnWidths) {
    System.out.print("\t\t|");
    for (int i = 0; i < rowData.size(); i++) {
      String format = " %-" + columnWidths[i] + "s |";
      System.out.printf(format, rowData.get(i));
    }
    System.out.println();
  }

  private static void printRow(String[] rowData, int[] columnWidths) {
    System.out.print("\t\t|");
    for (int i = 0; i < rowData.length; i++) {
      String format = " %-" + columnWidths[i] + "s |";
      System.out.printf(format, rowData[i]);
    }
    System.out.println();
  }

  private static void printHeader(int[] columnWidths, ArrayList<String> header) {
    printBorder(columnWidths);
    printRow(header, columnWidths);
    printBorder(columnWidths);
  }

  public static void printInformation(String message, int boxWidth) {
    printBorder(message);
    printFormattedLine(message, boxWidth);
    printBorder(message);
  }

  public static void printInformation(ArrayList<String> information, ArrayList<String> header) {
    printHeader(COLUMN_WIDTHS, HEADER);
    printRow(information, COLUMN_WIDTHS);
    printBorder(COLUMN_WIDTHS);
  }

  public static void printInformation(ArrayList<ArrayList<String>> informations, int[] columnWidths,
      ArrayList<String> header) {
    printHeader(columnWidths, header);
    for (ArrayList<String> student : informations) {
      printRow(student, columnWidths);
    }
    printBorder(columnWidths);
  }

  public static void printInformation(LinkedHashSet<ArrayList<String>> data) {
    printHeader(COLUMN_WIDTHS_TOTAL_MARKS, HEADER_TOTAL_MARKS);
    for (ArrayList<String> student : data) {
      printRow(student, COLUMN_WIDTHS_TOTAL_MARKS);
    }
    printBorder(COLUMN_WIDTHS_TOTAL_MARKS);
  }

  public static void printInformation(List<ArrayList<String>> data) {
    printHeader(COLUMN_WIDTHS_TOTAL_MARKS, HEADER_TOTAL_MARKS);
    for (ArrayList<String> student : data) {
      printRow(student, COLUMN_WIDTHS_TOTAL_MARKS);
    }
    printBorder(COLUMN_WIDTHS_TOTAL_MARKS);
  }

  public static void printInformation(LinkedHashSet<ArrayList<String>> data, int[] columnWidths,
      ArrayList<String> header) {
    printHeader(columnWidths, header);
    for (ArrayList<String> student : data) {
      printRow(student, columnWidths);
    }
    printBorder(columnWidths);
  }

  public static void printInformation(Set<ArrayList<String>> informations) {
    int[] columnWidths = { 15, 30, 6, 10, 8, 12, 40, 6 };
    printHeader(columnWidths, HEADER);
    for (ArrayList<String> rowData : informations) {
      printRow(rowData, columnWidths);
    }
    printBorder(columnWidths);
  }

  public static void printInformation(String[][] data, int[] columnWidths, ArrayList<String> header) {
    printHeader(columnWidths, header);
    for (int i = 0; i < data.length; i++) {
      printRow(data[i], columnWidths);
    }
    printBorder(columnWidths);
  }

  public static void printMessage(String message, int count) {
    System.out.println(message + BLUE + count + RESET);
  }

  public static void printMessage(String message, double count) {
    System.out.println(message + BLUE + count + RESET);
  }

  public static void printMessage(String message) {
    System.out.println(message);
  }

  public static void printRules(int c) {
    switch (c) {
      case 1:
        System.out.println(GREEN + """
            \n\n\t\tRules:
            	\t\t1. Length: The name should be between 2 and 30 characters.
            	\t\t2. Allowed characters: Only alphabets and spaces are allowed.
            	\t\t3. Capitalization: The first character of each word must be an uppercase letter.
            	\t\t4. Spacing: Each word should be separated by a single space.
            	\t\t5. No leading/trailing spaces: The name should not start or end with a space.
            	\t\t6. No special characters: Only letters and spaces are allowed.
            					""" + RESET);
        break;
      case 2:
        System.out.println(GREEN + """
            \n\n\t\tRules:
            	\t\t1. Example of enrollment type `24ENG4CSE1323`
            	\t\t2. The enrollment number must start with the exact sequence 24ENG4CSE (without any spaces).
            	\t\t3. After the sequence 24ENG4CSE, you must write a four-digit number.
            	\t\t4. The number should start from 1000 and can go higher (e.g., 1000, 1001, 1234, 5678, etc.).
            	\t\t5. Do not start the number with a zero. Numbers like 0999 or 0123 are not valid.
            	""" + RESET);
        break;
      case 3:
        System.out.println(GREEN
            + """
                \n\t\tRules:
                  \t\t1. <<first part>>@<<second part>><<third part>>
                  \t\t2. First part can contain alphabets (should accept both lower case and upper case) and numbers and Length should be at least 1 and not greate than 30.
                  \t\t3. Second part can contain only upper case and lower case alphabets. Length should be at least 3 and not greate than 12.
                  \t\t4. Third part should be either '.com' or '.in'.
                """
            + RESET);
        break;
      case 4:
        System.out.println(GREEN + """
            \n\t\tRules: `M` for Male or `F` for Female
                  """ + RESET);
        break;
      case 5:
        System.out.println(GREEN + """
            \n\t\tRules:
            	\t\t1. Date formate: `DD/MM/YYYY`, ex: `02/02/2003`
            	\t\t2. Date should be valid or leap year with calender
            	\t\t3. Birth year valid only for between 1999 to 2005
                  	""" + RESET);
        break;
      case 6:
        System.out.print("\n\n\t\tMarks:");
        System.out.println(GREEN + """
            \n\t\tRules:
              \t\t1. Marks between 1 and 100
              \t\t2. -1 for absent
              """ + RESET);
        break;

      default:
        break;
    }
  }

  public static void waterMark() {
    System.out.println("\n\n\n");
    String _waterMark = WHITE + "Thanks for use Marksheet Management Application.";
    String _signature = "Created By: " + BLUE2 + "https://kuldeep1a.web.app/      " + RESET;
    String _signature2 = "Created By: " + BLUE2 + "https://kuldeep-dhangar.web.app/      " + RESET;
    Display.printInformation(_waterMark, _waterMark.length() + 13);
    Display.printInformation(_signature, _waterMark.length() + 17);
    Display.printInformation(_signature2, _signature2.length() + 17);
  }
}
