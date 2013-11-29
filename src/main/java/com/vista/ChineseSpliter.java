package com.vista;

import java.io.IOException;
import jeasy.analysis.MMAnalyzer;

/**
 * 中文分词器
 */
public class ChineseSpliter {
  /**
   * 对给定的文本进行中文分词
   * 
   * @param text
   *          给定的文本
   * @param splitToken
   *          用于分割的标记,如"|"
   * @return 分词完毕的文本
   */
  public static String split(String text, String splitToken) {
    String result = null;
    MMAnalyzer analyzer = new MMAnalyzer();
    try {
      result = analyzer.segment(text, splitToken);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }
}
