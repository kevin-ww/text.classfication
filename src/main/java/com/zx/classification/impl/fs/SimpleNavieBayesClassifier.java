package com.zx.classification.impl.fs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zx.classification.ClassificationResult;
import com.zx.classification.Classifier;
import com.zx.classification.DataSet;
import com.zx.classification.tokenizers.IKTokenizer;

/**
 * all in-memory implementation; using plain text as training doc;
 * 
 */
public class SimpleNavieBayesClassifier implements Classifier<String> {

  private final Logger Logger = LoggerFactory
      .getLogger(SimpleNavieBayesClassifier.class);

  private TrainningDataSet trainedDataSet;

  @Override
  public Collection<ClassificationResult<String>> classify(String text)
      throws Exception {

    Collection<ClassifiedTrainingDataSet> cds = trainedDataSet
        .getClassfiedTrainningDataSets();

    Collection<String> terms = IKTokenizer.tokenize(text);

    Logger.info("Classify: " + text + "|" + terms);

    List<ClassificationResult<String>> resultSet = new ArrayList<ClassificationResult<String>>();

    double probability;

    for (ClassifiedTrainingDataSet cs : cds) {

      Logger.info("Calculating on " + cs.getName());

      probability = this.calculateConditionalProbability(cs.getName(), terms);
      resultSet
          .add(new ClassificationResult<String>(cs.getName(), probability));

      Logger.info("Done calculating on " + cs.getName() + ",got probability "
          + probability);
    }

    ClassificationResult<String> result = postProcess(resultSet);

    Logger.info("Classifiy Result: " + result.toString());

    // ClassificationResult<String> result = postProcess(resultSet);

    // currently return all ; further change needed;
    return resultSet;
  }

  @Override
  public void applyTrainSet(DataSet dataSet) {
    this.trainedDataSet = (TrainningDataSet) dataSet;
    Logger.info("applying trained dataset for {}", this);
  }

  //
  protected ClassificationResult<String> postProcess(
      List<ClassificationResult<String>> resultSet) {

    // java.util.Collections.sort(resultSet);

    java.util.Collections.sort(resultSet,
        new Comparator<ClassificationResult<String>>() {

          @Override
          public int compare(ClassificationResult<String> o1,
              ClassificationResult<String> o2) {
            return (o1.getScore() - o2.getScore()) > 0 ? -1 : 1;
          }

        });

    return resultSet.get(0);

  }

  // implementation;

  /**
   * @param classifiedTrainingSet
   * @param term
   * @return
   */
  public double calculateConditionalProbability(
      ClassifiedTrainingDataSet classifiedTrainingSet, String term) {

    Map<String, Integer> termVector = classifiedTrainingSet.getTermVector();

    Integer tf = termVector.get(term);

    double nxc, nc, v;

    nxc = (tf == null) ? 0.0 : tf;

    nc = classifiedTrainingSet.getDataSetSize();

    v = this.trainedDataSet.getClassficationSize();

    double cp = (nxc + 1) / (nc + 0.0 + v);

    Logger.info(String.format(
        "Conditional probability: [nxc:%f nc:%f v:%f cp:%f term %s]", nxc, nc,
        v, cp, term));

    return cp;
  }

  /**
   * @param classficationName
   * @param terms
   * @return
   */
  public double calculateConditionalProbability(String classficationName,
      Collection<String> terms) {

    ClassifiedTrainingDataSet classifiedTrainingSet = this.trainedDataSet
        .getClassifiedTrainingDataSetByName(classficationName);

    double cp = 1.0;

    for (String term : terms) {
      cp = (cp * this.calculateConditionalProbability(classifiedTrainingSet,
          term));
    }

    double pp = this.trainedDataSet
        .getPriorPriorProbabilityByName(classficationName);

    cp = (cp * pp);

    return cp;
  }

  @Override
  public String toString() {
    return "SimpleNavieBayesClassifier [trainedDataSet=" + trainedDataSet + "]";
  }

}
