package org.webscrapper.service;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileService {

  private static FileService service = null;

  private final JFileChooser fileChooser;

  private FileService() {
    fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new FileNameExtensionFilter("Excel files", "xlsx", "xls"));
  }

  public static FileService getInstance() {
    if (service == null) {
      service = new FileService();
    }
    return service;
  }

  public File choose() {
    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile();
    }
    return null;
  }
}
