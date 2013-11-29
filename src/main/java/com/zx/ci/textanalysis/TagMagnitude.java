package com.zx.ci.textanalysis;

public interface TagMagnitude extends Tag, Comparable<TagMagnitude> {
  public double getMagnitude();

  public double getMagnitudeSqd();

  public Tag getTag();
}
