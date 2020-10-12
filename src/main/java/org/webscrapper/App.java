package org.webscrapper;

import java.io.File;
import java.io.IOException;
import org.jsoup.nodes.Document;
import org.webscrapper.service.FileService;
import org.webscrapper.service.LoginService;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException {
        File file = FileService.getInstance().choose();
        System.out.print(file.getName());

        Document doc = LoginService.getInstance().login().parse();
        System.out.println(doc.getElementsByClass("navbar-brand").get(0).html());
    }
}
