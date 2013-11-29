package impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class CLZ {

  String name;

  int textfileCount;

  /**
   * term->count of doc which contains such term;
   */
  Map<String, Integer> termVector;

  public CLZ(File dir) {
    this.name = dir.getName();
    this.termVector = new LinkedHashMap<String, Integer>();
  }

  public void doProcess(File clzDir) {

    LogUtil.debug("now training text data files under " + clzDir);

    File[] files = clzDir.listFiles();

    // Inappropriate, change later;

    TrainingDataSet.allTextFileCount += files.length;

    //

    this.textfileCount = files.length;

    LogUtil.debug("there are all together  " + this.textfileCount
        + " under this ");

    String content;

    String[] terms;

    for (File file : files) {

      try {

        content = TextUtil.getText(file.getAbsolutePath());

        // LogUtil.debug("now getting " + content);

        terms = TextSpliter.splitExt(content);

        doProcessTerms(terms, content);

      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  private void doProcessTerms(String[] terms, String content) {

    if (content.contains("网")) {
      System.out.println(content);
      // System.out.println(term);
    }

    // do de-duplication;
    Set<String> s = new HashSet<String>(Arrays.asList(terms));
    // for (String term : terms) {
    // s.add(term);
    // // doProcessTerm(term, content);
    // }

    for (String x : s) {
      doProcessTerm(x, content);
    }

  }

  private void doProcessTerm(String term, String content) {

    // if (term.equals("网")) {
    // System.out.println(content);
    // System.out.println(term);
    // }

    Integer tf = this.termVector.get(term);

    if (content.contains(term)) {
      if (tf != null) {
        this.termVector.remove(term);
        this.termVector.put(term, tf.intValue() + 1);
      } else {
        this.termVector.put(term, 1);
      }

    }

    // something wrong here ;

    // Integer tf = this.termVector.get(term);
    // if (tf == null) {
    // this.termVector.put(term, new Integer(1));
    // } else {
    // this.termVector.remove(term);
    // this.termVector.put(term, tf.intValue() + 1);
    // }
  }

  public String getName() {
    return name;
  }

  public int getTextfileCount() {
    return textfileCount;
  }

  /**
   * term and it's associated frequency;
   * 
   * @return
   */
  public Map<String, Integer> getTermVector() {
    return termVector;
  }

  public float getPriorProbability() {
    float nc = this.getTextfileCount();
    float n = TrainingDataSet.getAllTextFileCount();
    return nc / n;
  }

  public float getConditionalProbability(String term) {

    Integer tf = this.termVector.get(term);

    float nxc = (tf == null) ? 0F : tf;
    float nc = this.getTextfileCount();
    float v = TrainingDataSet.getClzCount();
    //

    float cp = (nxc + 1) / (nc + 0F + v);

    String info = String.format(
        "calculating conditional p nxc:%f nc:%f v:%f cp:%f term:%s", nxc, nc,
        v, cp, term);

    LogUtil.debug(info);

    return cp;
  }

  public float getConditionalProbability(String[] terms) {

    float p = 1.0F;
    for (String term : terms) {
      p *= this.getConditionalProbability(term);
    }

    p = p * this.getPriorProbability();

    // LogUtil.debug("getting " + p);

    return p;
  }

}
