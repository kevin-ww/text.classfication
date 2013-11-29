package com.zx.ci.tagcloud;

import java.util.List;

public interface FontSizeComputationStrategy {
  public void computeFontSize(List<TagCloudElement> elements);
}
