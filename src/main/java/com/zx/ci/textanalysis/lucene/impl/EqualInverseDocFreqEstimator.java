package com.zx.ci.textanalysis.lucene.impl;

import com.zx.ci.textanalysis.InverseDocFreqEstimator;
import com.zx.ci.textanalysis.Tag;

public class EqualInverseDocFreqEstimator implements InverseDocFreqEstimator {
  public double estimateInverseDocFreq(Tag tag) {
    return 1.0;
  }

  public void addCount(Tag tag) {

  }
}
