package com.zx.classification;

import java.util.Arrays;

public class Lable {

  public static final String[] ALREADY_DEFINED_LABLES = { "好评", "其他", "品牌口碑",
      "价格", "品牌问题", "产品质量" };

  private String[] lables;

  public Lable(String[] lables) {
    // super();
    this.lables = lables;
  }

  /**
   * @param notConvertedLables
   *          sample: "品牌口碑/产品口味/价格"
   */
  public Lable(String notConvertedLables) {
    this.lables = notConvertedLables.trim().split("/");
  }

  public String[] getLables() {
    return lables;
  }

  public static void main(String[] args) {

    String lables = "品牌口碑/产品口味/价格";

    Lable lable = new Lable(lables);

    System.out.println(Arrays.toString(lable.getLables()));

  }

}
