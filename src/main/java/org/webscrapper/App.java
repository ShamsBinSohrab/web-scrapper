package org.webscrapper;

import java.io.IOException;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException {
        Response response =
            Jsoup.connect("https://firstbd.ltd/work/index.php")
                .data("username", "nsi30")
                .data("password", "nsi#20").method(Method.POST).execute();
        System.out.println(response.body());
    }
}
