package com.zx.classification.tokenizers;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;

import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;

public class IKTokenizer{

  // 当为true时，分词器进行最大词长切分
  public static final boolean IS_USING_MAX_WORD_LENGTH = true;

  public static Collection<String> tokenize(String text) throws Exception {
    return tokenize(text, IS_USING_MAX_WORD_LENGTH);
  }

  public static Collection<String> tokenize(String text,
      boolean isUsingMaxWordLength) throws Exception {
    
    StringReader reader = new StringReader(text);

    IKSegmentation ik = new IKSegmentation(reader, isUsingMaxWordLength);

    Lexeme lexeme = null;

    Collection<String> tokens = new ArrayList<String>();

    while ((lexeme = ik.next()) != null) {
      tokens.add(lexeme.getLexemeText());
    }
    return tokens;
  }

  public static void main(String[] args) throws Exception {
    
    String testStr = "基于配置的词典扩充. 项目结构图如下";

    Collection<String> tokens = IKTokenizer.tokenize(testStr);

    System.out.println(tokens);

  }

}
