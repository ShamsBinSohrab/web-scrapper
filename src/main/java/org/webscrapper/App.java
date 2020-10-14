package org.webscrapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.slf4j.LoggerFactory;
import org.webscrapper.service.FileService;
import org.webscrapper.service.LoginService;

/**
 * Hello world!
 */
public class App {

  private static final FileService fileService = new FileService();
  private static final LoginService loginService = new LoginService();

  public static void main(String[] args) {
    try {
      final File file = fileService.selectFile();
      System.out.println("Selected file: " + file.getName());
      final XSSFSheet sheet = fileService.getSheet(file);
      fileService.printSheet(sheet);
      System.out.print("To insert this data type 'confirm' and press enter: ");
      String confirm = new Scanner(System.in).next();
      if (confirm.equals("confirm")) {
        final Response response = loginService.login();
        System.out.println(response.parse().getElementsByClass("navbar-brand").get(0).html());
      }
      sheet.getWorkbook().close();
    } catch (RuntimeException | IOException | InvalidFormatException ex) {
      System.out.println("Error: " + ex.getMessage());
    } finally {
      System.exit(0);
    }
  }
}
