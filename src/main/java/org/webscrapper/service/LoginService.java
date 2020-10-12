package org.webscrapper.service;

import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class LoginService {
  private static LoginService service = null;

  private final String loginUrl;
  private final String username;
  private final String password;

  private LoginService() {
    Scanner scanner = new Scanner(System.in);
    loginUrl = "https://firstbd.ltd/work/index.php";

    System.out.print("Username: ");
    this.username = scanner.next();

    System.out.print("Password: ");
    this.password = scanner.next();
  }

  public static LoginService getInstance() {
    if (service == null) {
      service = new LoginService();
    }
    return service;
  }

  public Response login() throws IOException {
    Response loginPageResponse =
        Jsoup.connect(loginUrl)
            .followRedirects(true)
            .execute();
    return Jsoup.connect(loginUrl)
            .data("username", username)
            .data("password", password)
            .cookies(loginPageResponse.cookies())
            .followRedirects(true)
            .method(Method.POST)
            .execute();
  }
}
