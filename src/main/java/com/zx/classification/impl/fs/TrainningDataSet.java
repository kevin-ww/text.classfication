package com.zx.classification.impl.fs;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zx.classification.DataSet;

public class TrainningDataSet implements DataSet {

  private final Logger Logger = LoggerFactory.getLogger(TrainningDataSet.class);

  private File docsPath;

  private Collection<ClassifiedTrainingDataSet> classfiedTrainningDataSets;

  private int dataSetSize = 0;

  // priorProbabilities for each classification;

  private Map<String, Double> priorProbabilities;

  public TrainningDataSet(String docsPath) throws Exception {

    Logger.info("Now training text doc files under " + docsPath);

    this.docsPath = new File(docsPath);
    if (!this.docsPath.isDirectory()) {
      throw new IllegalArgumentException(
          "please ensure training docs are under " + docsPath);
    }

  }

  public void doTrain() throws Exception {

    File[] trainingDocDirs = this.docsPath.listFiles();

    classfiedTrainningDataSets = new ArrayList<ClassifiedTrainingDataSet>();

    for (File trainingDocDir : trainingDocDirs) {
      buildClassfiedTrainningDataSets(trainingDocDir);
    }

    //
    calculatePriorProbability();
  }

  protected void buildClassfiedTrainningDataSets(File trainingDocDir)
      throws Exception {

    if (!trainingDocDir.isDirectory()) {
      throw new IllegalArgumentException(
          "please ensure training docs is under " + trainingDocDir);
    }

    ClassifiedTrainingDataSet classfiedTrainingDataSet = new ClassifiedTrainingDataSet(
        trainingDocDir.getName());

    classfiedTrainingDataSet.buildClassiedTrainingSet(trainingDocDir);

    this.dataSetSize += classfiedTrainingDataSet.getDataSetSize();

    classfiedTrainningDataSets.add(classfiedTrainingDataSet);

  }

  @Override
  public int getDataSetSize() {
    return this.dataSetSize;
  }

  public int getClassficationSize() {
    return this.classfiedTrainningDataSets.size();
  }

  public Collection<ClassifiedTrainingDataSet> getClassfiedTrainningDataSets() {
    return classfiedTrainningDataSets;
  }

  public ClassifiedTrainingDataSet getClassifiedTrainingDataSetByName(
      String name) {

    for (ClassifiedTrainingDataSet cds : this.classfiedTrainningDataSets) {
      if (cds.getName().equalsIgnoreCase(name)) {
        return cds;
      }
    }
    return null;
  }

  // calculate PriorProbability for each classification;
  public void calculatePriorProbability() {

    this.priorProbabilities = new HashMap<String, Double>();

    Collection<ClassifiedTrainingDataSet> classifiedSets = this
        .getClassfiedTrainningDataSets();

    double nc, n;

    String classficationName;

    n = this.getDataSetSize();

    for (ClassifiedTrainingDataSet classifiedSet : classifiedSets) {

      classficationName = classifiedSet.getName();

      nc = classifiedSet.getDataSetSize();

      this.priorProbabilities.put(classficationName, nc / n);

      Logger.info(String.format("Prior probability:  [%s nc:%f n:%f pp:%f]",
          classficationName, nc, n, nc / n));

    }

  }

  public double getPriorPriorProbabilityByName(String classificationName) {
    return this.priorProbabilities.get(classificationName);
  }

  @Override
  public String toString() {
    return "TrainningDataSet [docsPath=" + docsPath
        + ", classfiedTrainningDataSetSize=" + this.getClassficationSize()
        + ", classfiedTrainningDataSets=" + classfiedTrainningDataSets
        + ", dataSetSize=" + dataSetSize + "]";
  }

}
