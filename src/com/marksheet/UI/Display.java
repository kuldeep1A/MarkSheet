package com.marksheet.UI;

import java.util.ArrayList;
import java.util.Set;

/**
 * Display message or Document to the console
 */
public class Display implements Content, Colors {
  public static void printBorder(int width) {
    System.out.println("\t\t+" + "-".repeat(width - 2) + "+");
  }

  public static void printFormattedLine(String text, int boxWidth) {
    int padding = boxWidth - text.length() - 9;
    System.out.printf("\t\t|\t%s%" + padding + "s|\n", text, "");
  }

  public static void printBorder(int[] columnWidths) {
    System.out.print("\t\t+");
    for (int width : columnWidths) {
      System.out.print("-".repeat(width + 2) + "+");
    }
    System.out.println();
  }

  public static void printRow(ArrayList<String> rowData, int[] columnWidths) {
    System.out.print("\t\t|");
    for (int i = 0; i < rowData.size(); i++) {
      String format = " %-" + columnWidths[i] + "s |";
      System.out.printf(format, rowData.get(i));
    }
    System.out.println();
  }

  public static void printHeader(int[] columnWidths, ArrayList<String> header) {
    printBorder(columnWidths);
    printRow(header, columnWidths);
    printBorder(columnWidths);
  }

  public static <E> void printInformation(E data, ArrayList<String> header) {
    @SuppressWarnings(value = { "unchecked" })
    ArrayList<String> information = (ArrayList<String>) data;
    printHeader(COLUMN_WIDTHS, HEADER);
    printRow(information, COLUMN_WIDTHS);
    printBorder(COLUMN_WIDTHS);
  }

  public static <E> void printInformation(E data, int[] columnWidths, ArrayList<String> header) {
    @SuppressWarnings(value = { "unchecked" })
    ArrayList<ArrayList<String>> informations = (ArrayList<ArrayList<String>>) data;
    printHeader(columnWidths, header);
    for (ArrayList<String> student : informations) {
      printRow(student, columnWidths);
    }
    printBorder(columnWidths);
  }

  public static <E> void printInformation(E data) {
    @SuppressWarnings(value = { "unchecked" })
    Set<ArrayList<String>> informations = (Set<ArrayList<String>>) data;
    int[] columnWidths = { 15, 30, 6, 10, 8, 12, 40, 6 };
    printHeader(columnWidths, HEADER);
    for (ArrayList<String> rowData : informations) {
      printRow(rowData, columnWidths);
    }
    printBorder(columnWidths);
  }

  public static void printMessage(String message, int count) {
    System.out.println(message + BLUE + count + RESET);
  }

  public static void printMessage(String message) {
    System.out.println(message);
  }
}
