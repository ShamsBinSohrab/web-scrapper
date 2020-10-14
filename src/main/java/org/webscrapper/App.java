package org.webscrapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.jsoup.nodes.Document;
import org.webscrapper.service.FileService;
import org.webscrapper.service.HtmlParser;

/**
 * Hello world!
 */
public class App {

  private static final FileService fileService = new FileService();
  private static final HtmlParser htmlParser = new HtmlParser();

  public static void main(String[] args) {
    try {
      final File file = fileService.selectFile();
      System.out.println("Selected file: " + file.getName());
      final XSSFSheet sheet = fileService.getSheet(file);
      fileService.printSheet(sheet);
      System.out.print("To insert this data type 'confirm' and press enter: ");
      String confirm = new Scanner(System.in).next();
      if (confirm.equals("confirm")) {
        final Document document = htmlParser.login();
      }
      sheet.getWorkbook().close();
    } catch (RuntimeException | IOException | InvalidFormatException ex) {
      System.out.println("Error: " + ex.getMessage());
    } finally {
      System.exit(0);
    }
  }
}
