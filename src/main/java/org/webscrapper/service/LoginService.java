package org.webscrapper.service;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class LoginService {

  private static final String loginUrl = "https://firstbd.ltd/work/index.php";
  private final Scanner scanner = new Scanner(System.in);

  public Response login() throws IOException {
    final Map<String, String> cookies = Jsoup.connect(loginUrl)
        .followRedirects(true)
        .execute().cookies();

    System.out.print("Username: ");
    final String username = scanner.next();

    System.out.print("Password: ");
    final String password = scanner.next();

    return Jsoup.connect(loginUrl)
        .data("username", username)
        .data("password", password)
        .cookies(cookies)
        .followRedirects(true)
        .method(Method.POST)
        .execute();
  }
}
