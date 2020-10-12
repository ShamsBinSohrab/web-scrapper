package org.webscrapper;

import java.io.IOException;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException {
        Response loginPageResponse =
            Jsoup.connect("https://firstbd.ltd/work/index.php")
                .followRedirects(true)
                .execute();

        Response response =
            Jsoup.connect("https://firstbd.ltd/work/index.php")
                .data("username", "nsi30")
                .data("password", "nsi#30")
                .cookies(loginPageResponse.cookies())
                .followRedirects(true)
                .method(Method.POST)
                .execute();

        Document doc = response.parse();
        System.out.println(doc.getElementsByClass("navbar-brand").get(0).html());
    }
}
