package impl;

import java.io.IOException;
import java.util.Vector;

import jeasy.analysis.MMAnalyzer;

public class TextSpliter {

  // public static final String DEFAULT_SPLIT_TOKEN = "|";

  public static final String DEFAULT_EMPTY_STRING_TOKEN = " ";

  public static String[] splitExt(String text) {
    String[] terms = split(text, DEFAULT_EMPTY_STRING_TOKEN).split(
        DEFAULT_EMPTY_STRING_TOKEN);

    Vector<String> vec = new Vector<String>();

    for (String term : terms) {
      if (!StopWordsFilter.IsStopWord(term)) {
        //
        vec.add(term);
      }
    }

    String[] newTerms = new String[vec.size()];

    vec.toArray(newTerms);

    return newTerms;
  }

  public static String split(String text) {
    return split(text, DEFAULT_EMPTY_STRING_TOKEN);
  }

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
