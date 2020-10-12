package org.webscrapper;

import java.io.IOException;
import org.jsoup.nodes.Document;
import org.webscrapper.service.LoginService;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException {
        Document doc = LoginService.login().parse();
        System.out.println(doc.getElementsByClass("navbar-brand").get(0).html());
    }
}
