package kvn.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextUtil {

  public static String getText(String filePath) throws FileNotFoundException,
      IOException {

    InputStreamReader isReader = new InputStreamReader(new FileInputStream(
        filePath), "GBK");
    BufferedReader reader = new BufferedReader(isReader);
    String aline;
    StringBuilder sb = new StringBuilder();

    while ((aline = reader.readLine()) != null) {
      sb.append(aline + " ");
    }
    isReader.close();
    reader.close();
    return sb.toString();
  }

}
