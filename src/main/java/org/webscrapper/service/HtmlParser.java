package org.webscrapper.service;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlParser {

  private static final String loginUrl = "https://firstbd.ltd/work/index.php";
  private final Scanner scanner = new Scanner(System.in);

  public Document login() throws IOException {
    final Map<String, String> cookies =
        Jsoup.connect(loginUrl)
            .followRedirects(true)
            .execute()
            .cookies();
    final Document document =
        Jsoup.connect(loginUrl)
            .data("username", scanUsername())
            .data("password", scanPassword())
            .cookies(cookies)
            .followRedirects(true)
            .method(Method.POST)
            .execute().parse();
    checkIfLoggedInToWorkPage(document);
    return document;
  }

  private void checkIfLoggedInToWorkPage(Document document) {
    if (document.getElementsByAttributeValue("action", "insert.php").isEmpty()) {
      throw new RuntimeException("Unable to login");
    }
  }

  private String scanUsername() {
    System.out.print("Username: ");
    return scanner.next();
  }

  private String scanPassword() {
    System.out.print("Password: ");
    return scanner.next();
  }
}
