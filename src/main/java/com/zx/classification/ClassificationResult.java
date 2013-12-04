package com.zx.classification;

import org.apache.lucene.classification.Classifier;

public class ClassificationResult<T> implements Comparable<T> {

  private T assignedClass;
  private double score;

  /**
   * Constructor
   * 
   * @param assignedClass
   *          the class <code>T</code> assigned by a {@link Classifier}
   * @param score
   *          the score for the assignedClass as a <code>double</code>
   */
  public ClassificationResult(T assignedClass, double score) {
    this.assignedClass = assignedClass;
    this.score = score;
  }

  /**
   * retrieve the result class
   * 
   * @return a <code>T</code> representing an assigned class
   */
  public T getAssignedClass() {
    return assignedClass;
  }

  /**
   * retrieve the result score
   * 
   * @return a <code>double</code> representing a result score
   */
  public double getScore() {
    return score;
  }

  @Override
  public String toString() {
    return "ClassificationResult [assignedClass=" + assignedClass + ", score="
        + score + "]";
  }

  @Override
  public int compareTo(T o) {
    // TODO Auto-generated method stub
    return 0;
  }

}
