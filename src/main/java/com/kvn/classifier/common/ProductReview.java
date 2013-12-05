package com.kvn.classifier.common;

import java.util.Date;

public class ProductReview {

  private String uid;

  private ECommercePLATFORM platform;

  private String reviewText;

  private Date createDate;

  public ProductReview(String uid, String reviewText) {
    this(uid, null, reviewText, null);
  }

  public ProductReview(String uid, ECommercePLATFORM platform,
      String reviewText, Date createDate) {
    this.setUid(uid);
    this.setPlatform(platform);
    this.setReviewText(reviewText);
    this.setCreateDate(createDate);
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public ECommercePLATFORM getPlatform() {
    return platform;
  }

  public void setPlatform(ECommercePLATFORM platform) {
    this.platform = platform;
  }

  public String getReviewText() {
    return reviewText;
  }

  public void setReviewText(String reviewText) {
    this.reviewText = reviewText;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public enum ECommercePLATFORM {
    TMALL, AMAZON_CN
  }

}
