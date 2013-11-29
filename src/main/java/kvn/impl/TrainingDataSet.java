package kvn.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TrainingDataSet {

  File dir;

  List<CLZ> clzs;

  static int clzCount = 0;

  static int allTextFileCount = 0;

  public TrainingDataSet(String dir) {

    this.dir = new File(dir);
    if (!this.dir.isDirectory()) {
      throw new IllegalArgumentException("to verify" + dir);
    }
    // static ,non-static, using patter to resolve
    // get clz count;
    TrainingDataSet.clzCount = this.dir.list().length;
    this.clzs = new ArrayList<CLZ>();
  }

  public void doTraining() {

    File[] clzDirs = dir.listFiles();

    CLZ clz = null;

    for (File clzDir : clzDirs) {
      clz = new CLZ(clzDir);
      this.clzs.add(clz);
      clz.doProcess(clzDir);
    }

  }

  public List<CLZ> getClzs() {
    return this.clzs;
  };

  public static int getClzCount() {
    return clzCount;
  }

  public static float getAllTextFileCount() {
    return allTextFileCount;
  }

}
