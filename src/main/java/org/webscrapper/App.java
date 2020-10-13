package org.webscrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.webscrapper.service.FileService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException, InvalidFormatException {
        File file = FileService.getInstance().choose();
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getCellType().equals(CellType.NUMERIC)) {
                    System.out.print(cell.getNumericCellValue() + " ");
                } else if (cell.getCellType().equals(CellType.STRING)) {
                    System.out.print(cell.getStringCellValue() + " ");
                } else {
                    System.out.print("Other -> " + cell.getCellType().name() + " ");
                }
            }
            System.out.println("\n");
        }

//        Document doc = LoginService.getInstance().login().parse();
//        System.out.println(doc.getElementsByClass("navbar-brand").get(0).html());


    }
}
