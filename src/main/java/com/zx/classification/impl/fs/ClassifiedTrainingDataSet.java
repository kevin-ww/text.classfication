package com.zx.classification.impl.fs;

import java.io.File;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zx.classification.DataSet;
import com.zx.classification.TextUtil;
import com.zx.classification.tokenizers.IKTokenizer;

public class ClassifiedTrainingDataSet implements DataSet {

  private final Logger Logger = LoggerFactory.getLogger(TrainningDataSet.class);

  // Reader reader;

  // Classification name;

  private String name;

  //
  private int dataSetSize = 0;

  /**
   * term:count of doc which contains such term;
   */
  private Map<String, Integer> termVector;

  public ClassifiedTrainingDataSet(String name) {
    this.name = name;
    this.termVector = new LinkedHashMap<String, Integer>();
  }

  @Override
  public int getDataSetSize() {
    return this.dataSetSize;
  }

  public void buildClassiedTrainingSet(File trainingDocDir) throws Exception {

    Logger.info("Now training text docs under " + trainingDocDir);

    File[] trainingDocs = trainingDocDir.listFiles();

    // set data set size;

    this.dataSetSize = trainingDocs.length;

    String content;

    Collection<String> terms;

    for (File trainingDoc : trainingDocs) {
      // read content ;
      content = TextUtil.readContent(trainingDoc.getAbsolutePath());

      // tokenize it ,stop word process already included;
      terms = IKTokenizer.tokenize(content);

      // stemming ,TODO;

      // build term vector;
      for (String term : terms) {
        buildTermVectors(content, term);
      }
    }
    Logger.info("Done training for " + this.toString());
    Logger.info("TermVector:" + this.termVector);
  }

  private void buildTermVectors(String content, String term) {

    Integer tf = this.termVector.get(term);

    if (content.contains(term)) {
      if (tf != null) {
        this.termVector.remove(term);
        this.termVector.put(term, tf.intValue() + 1);
      } else {
        this.termVector.put(term, 1);
      }

    }

  }

  public String getName() {
    return name;
  }

  public Map<String, Integer> getTermVector() {
    return termVector;
  }

  @Override
  public String toString() {
    return "ClassifiedTrainingDataSet [name=" + name + ", dataSetSize="
        + dataSetSize + "]";
  }

}
