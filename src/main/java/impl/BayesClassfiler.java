package impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BayesClassfiler {

  TrainingDataSet trainingDataSet;

  public BayesClassfiler(TrainingDataSet trainingDataSet) {
    this.trainingDataSet = trainingDataSet;
  }

  public String classifier(String text) {

    String[] terms = TextSpliter.splitExt(text);

    List<CLZ> clzs = trainingDataSet.getClzs();

    List<ClassfierResult> resultSet = new ArrayList<ClassfierResult>();

    float probability;

    for (CLZ clz : clzs) {

      probability = this.calculateProbability(terms, clz);

      ClassfierResult result = new ClassfierResult(clz.getName(), probability);

      LogUtil.debug("now getting p:" + result);

      resultSet.add(result);

    }

    return postProcess(resultSet);

  }

  private String postProcess(List<ClassfierResult> resultSet) {

    // find it ;
    java.util.Collections.sort(resultSet, new Comparator<ClassfierResult>() {

      @Override
      public int compare(ClassfierResult o1, ClassfierResult o2) {
        return (o1.p - o2.p) > 0 ? 1 : -1;
      }
    });

    return resultSet.get(0).cls;

  }

  private float calculateProbability(String[] terms, CLZ clz) {

    return clz.getConditionalProbability(terms);

  }

  class ClassfierResult {

    String cls;

    float p;

    public ClassfierResult(String cls, float p) {
      super();
      this.cls = cls;
      this.p = p;
    }

    @Override
    public String toString() {
      return "ClassfierResult [cls=" + cls + ", p=" + p + "]";
    }

  }

}
