package org.webscrapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.lang.model.util.Elements;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.webscrapper.model.Record;
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
      final List<Record> records = fileService.extractRecords(file);
      fileService.printRecords(records);
      System.out.print("To insert this data type 'confirm' and press enter: ");
      String confirm = new Scanner(System.in).next();
      if (confirm.equals("confirm")) {
        final Response response = htmlParser.login();
        final Element form = htmlParser.getLoginForm(response);
        htmlParser.setFormData(records.get(0), form);
      }
    } catch (RuntimeException | IOException | InvalidFormatException ex) {
      System.out.println("Error: " + ex.getMessage());
    } finally {
      System.exit(0);
    }
  }
}
