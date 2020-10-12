package org.webscrapper.service;

import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class LoginService {

  public static Response login() throws IOException {
    final LoginCredentials credentials = new LoginCredentials();
    Response loginPageResponse =
        Jsoup.connect(credentials.getLoginUrl())
            .followRedirects(true)
            .execute();
    return Jsoup.connect("https://firstbd.ltd/work/index.php")
            .data("username", credentials.getUsername())
            .data("password", credentials.getPassword())
            .cookies(loginPageResponse.cookies())
            .followRedirects(true)
            .method(Method.POST)
            .execute();
  }

  private static class LoginCredentials {
    private final String loginUrl;
    private final String username;
    private final String password;

    public LoginCredentials() {
      Scanner scanner = new Scanner(System.in);
      loginUrl = "https://firstbd.ltd/work/index.php";

      System.out.print("Username: ");
      this.username = scanner.next();

      System.out.print("Password: ");
      this.password = scanner.next();
    }

    public String getLoginUrl() {
      return loginUrl;
    }

    public String getUsername() {
      return username;
    }

    public String getPassword() {
      return password;
    }
  }
}
