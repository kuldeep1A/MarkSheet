package com.marksheet.UI;

import com.marksheet.model.Connectivity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Display message or Document to the console
 * 
 * @apiNote Functions to Display data user friendly
 */
public class Display extends Thread implements Content, Colors {

  public static void printFormattedLine(String text, int boxWidth) {
    int padding = boxWidth - text.length() - 9;
    System.out.printf("\t\t|\t%s%" + padding + "s|\n", text, "");
  }

  public static void printFormattedLine(String text, int range, int boxWidth) {
    String message = String.format(text, range);
    int padding = boxWidth - text.length() - 9;
    System.out.printf("\t\t|\t%s%" + padding + "s|\n", message, "");
  }

  public static void printBorder(String message) {
    System.out.println("\t\t+" + "-".repeat(message.length() + 6) + "+");
  }

  public static void printBorder(int width) {
    System.out.println("\t\t+" + "-".repeat(width - 2) + "+");
  }

  private static void printBorder(int[] columnWidths) {
    Display._printMessage("\t\t+");
    for (int width : columnWidths) {
      Display._printMessage("-".repeat(width + 2) + "+");
    }
    System.out.println();
  }

  private static void printRow(ArrayList<String> rowData, int[] columnWidths) {
    Display._printMessage("\t\t|");
    for (int i = 0; i < rowData.size(); i++) {
      String format = " %-" + columnWidths[i] + "s |";
      System.out.printf(format, rowData.get(i));
    }
    System.out.println();
  }

  private static void printRow(String[] rowData, int[] columnWidths) {
    Display._printMessage("\t\t|");
    for (int i = 0; i < rowData.length; i++) {
      String format = " %-" + columnWidths[i] + "s |";
      System.out.printf(format, rowData[i]);
    }
    System.out.println();
  }

  private static void printHeader(int[] columnWidths,
      ArrayList<String> header) {
    printBorder(columnWidths);
    printRow(header, columnWidths);
    printBorder(columnWidths);
  }

  public static void printInformation(String message, int range, int boxWidth) {
    printBorder(message);
    if (range == 0)
      printFormattedLine(message, boxWidth);
    else
      printFormattedLine(message, range, boxWidth);
    printBorder(message);
  }

  public static void printInformation(ArrayList<String> information,
      ArrayList<String> header) {
    printHeader(COLUMN_WIDTHS, HEADER);
    printRow(information, COLUMN_WIDTHS);
    printBorder(COLUMN_WIDTHS);
  }

  public static void printInformation(ArrayList<ArrayList<String>> informations,
      int[] columnWidths, ArrayList<String> header) {
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

  public static void printInformation(LinkedHashSet<ArrayList<String>> data,
      int[] columnWidths, ArrayList<String> header) {
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

  public static void printInformation(String[][] data, int[] columnWidths,
      ArrayList<String> header) {
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

  public static void _printMessage(String message) {
    System.out.print(message);
  }

  public static void printRules(int c) {
    switch (c) {
      case 1:
        System.out.println(GREEN
            + """
                \n\n\t\tRules:
                	\t\t1. Length: The name should be between 2 and 30 characters.
                	\t\t2. Allowed characters: Only alphabets and spaces are allowed.
                	\t\t3. Capitalization: The first character of each word must be an uppercase letter.
                	\t\t4. Spacing: Each word should be separated by a single space.
                	\t\t5. No leading/trailing spaces: The name should not start or end with a space.
                	\t\t6. No special characters: Only letters and spaces are allowed.
                					"""
            + RESET);
        break;
      case 2:
        System.out.println(GREEN
            + """
                \n\n\t\tRules:
                	\t\t1. Example of enrollment type `24ENG4CSE1323`
                	\t\t2. The enrollment number must start with the exact sequence 24ENG4CSE (without any spaces).
                	\t\t3. After the sequence 24ENG4CSE, you must write a four-digit number.
                	\t\t4. The number should start from 1000 and can go higher (e.g., 1000, 1001, 1234, 5678, etc.).
                	\t\t5. Do not start the number with a zero. Numbers like 0999 or 0123 are not valid.
                	"""
            + RESET);
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
        Display._printMessage("\n\n\t\tMarks:");
        System.out.println(GREEN + """
            \n\t\tRules:
              \t\t1. Marks between 1 and 100
              \t\t2. -1 for absent
              """ + RESET);
        break;
      case 7:
        System.out.println(GREEN + """
            \n\t\tRules:
              \t\t\t1. Use Lowercase Letters
              \t\t\t2. Use Underscores for Separating Words
              \t\t\t3. Be Descriptive
              \t\t\t4. Use Singular or Plural Consistently
              \t\t\t5. Avoid Reserved Keywords
              \t\t\t6. No Special Characters
              \t\t\t7. Use Prefixes for Clarity (Optional)
              \t\t\t8. Limit the Length
              \t\t\t9. Avoid Abbreviations (Unless Well-Known)
              \t\t\t10. Do not use a previously created name
              """ + RESET);

        break;
      case 8:
        String m1 = CYAN2 + TABLE_NAME_USE + RESET + Connectivity.TABLE_NAME
            + CYAN2 + " " + "-".repeat(24) + RESET;
        printInformation(m1, 0, m1.length() + 26);
        printMessage(YELLOW2 + COMMANDS_DETAILS + RESET);
        printInformation(CYAN2 + COMMANDS_RULES + RESET, 26,
            COMMANDS_RULES.length() + 26);
        _printMessage("\n\t\tCommand -> ");
        break;
      case 9:
        System.out.println(GREEN + """
            \n\t\tRules:
              \t\t\t1. Percentage between 57.50 to 93.50
              \t\t\t2. Only Valid Actual decimal digit
              """ + RESET);
      default:
        break;
    }
  }

  public static void waterMark() {
    System.out.println("\n\n\n");
    String _waterMark = WHITE
        + "Thanks for use Marksheet Management Application.";
    String _signature = "Created By: " + BLUE2
        + "https://kuldeep1a.web.app/      " + RESET;
    String _signature2 = "Created By: " + BLUE2
        + "https://kuldeep-dhangar.web.app/      " + RESET;
    Display.printInformation(_waterMark, 0, _waterMark.length() + 13);
    Display.printInformation(_signature, 0, _waterMark.length() + 17);
    Display.printInformation(_signature2, 0, _signature2.length() + 17);
  }

  public static void loading(String message, int range) {
    Display._printMessage(HIDE_CURSOR);
    Display._printMessage("\t\t" + message);

    for (int i = 0; i < Math.ceil(Math.random() * (range - 8) + 8); i++) {
      Display._printMessage(".");
      System.out.flush();
      try {
        sleep(100);
      } catch (InterruptedException e) {
        System.out.println(e.getMessage());
      }
    }

    System.out.println();
    Display._printMessage(SHOW_CURSOR);
    System.out.flush();
  }
}
