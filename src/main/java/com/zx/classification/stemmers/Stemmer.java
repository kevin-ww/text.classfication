package com.zx.classification.stemmers;

import java.io.Serializable;

public interface Stemmer extends Serializable {

  /**
   * Stems the given word and returns the stemmed version
   * 
   * @param word
   *          the unstemmed word
   * @return the stemmed word
   */
  public String stem(String word);

}
