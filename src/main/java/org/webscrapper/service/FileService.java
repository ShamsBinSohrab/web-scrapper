package org.webscrapper.service;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.webscrapper.model.Record;

public class FileService {

  private static final List<String> headers =
      Arrays.asList("Record No.", "Policy Date", "Policy No.", "Medical Card No.", "First Name",
          "Last Name",
          "City", "State", "Phone", "Martial Status", "GP Code", "H/C Days", "Paid Amount",
          "Net Amount");
  private final JFileChooser fileChooser;

  public FileService() {
    fileChooser = new JFileChooser();
    fileChooser.setFileFilter(
        new FileNameExtensionFilter("Excel files", "xlsx", "xls"));
  }

  public File selectFile() throws IOException {
    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile();
    }
    throw new IOException("No file selected");
  }

  public XSSFSheet getSheet(File file) throws IOException, InvalidFormatException {
    return new XSSFWorkbook(file).getSheetAt(0);
  }

  public void printRecords(List<Record> records) {
    final AsciiTable table = new AsciiTable();
    table.addRule();
    table.addRow(headers);
    table.addRule();
    for (Record record : records) {
      table.addRow(record.print());
      table.addRule();
    }
    table.setTextAlignment(TextAlignment.CENTER);
    System.out.println(table.render(150));
  }
}
