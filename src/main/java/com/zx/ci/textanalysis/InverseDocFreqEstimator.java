package com.zx.ci.textanalysis;

public interface InverseDocFreqEstimator {
  public double estimateInverseDocFreq(Tag tag);

  public void addCount(Tag tag);
}
