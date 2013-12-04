package com.zx.classification.impl;

import java.util.Arrays;
import java.util.Date;

public class Comment {

  public static Rating convertRating(String rating) {

    if (rating.equalsIgnoreCase("好评")) {
      return Rating.POSTIVE;
    } else if (rating.equalsIgnoreCase("中评")) {
      return Rating.NORMAL;
    } else if (rating.equalsIgnoreCase("差评")) {
      return Rating.NEGTIVE;
    }

    // not in scope? TODO

    return Rating.NORMAL;
  }

  public enum ECommercePlatform {
    TMALL, AMAZON_CN, AMAZON_US, JD, YIHAODIAN
  }

  public enum Rating {
    // VERYGOOD, GOOD, NORMAL, BAD, VERYBAD
    POSTIVE, NEGTIVE, NORMAL;

  }

  String id;

  ECommercePlatform ecommercePlatform;

  String brand;

  String comment;

  // Opinion opinion;
  Rating rating;

  String[] labels;

  Date timestamp;

  public Comment(String id, String comment) {
    this(id, null, null, null, comment, null, null);
  }

  public Comment(String id, String comment, String[] lables) {
    this(id, null, null, null, comment, lables, null);
  }

  public Comment(String id, String comment, Rating rating, String[] lables) {
    this(id, null, null, rating, comment, lables, null);
  }

  public Comment(String id, ECommercePlatform ecommercePlatform, String brand,
      Rating rating, String comment, String[] labels, Date timestamp) {
    this.id = id;
    this.ecommercePlatform = ecommercePlatform;
    this.brand = brand;
    this.rating = rating;
    this.comment = comment;
    this.labels = labels;
    this.timestamp = timestamp;
  }

  public String getId() {
    return id;
  }

  public ECommercePlatform getEcommercePlatform() {
    return ecommercePlatform;
  }

  public String getBrand() {
    return brand;
  }

  public String getComment() {
    return comment;
  }

  public Rating getRating() {
    return rating;
  }

  public String[] getLabels() {
    return labels;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return "Comment [id=" + id + ", ecommercePlatform=" + ecommercePlatform
        + ", brand=" + brand + ", rating=" + rating + ", comment=" + comment
        + ", labels=" + Arrays.toString(labels) + ", timestamp=" + timestamp
        + "]";
  }

}

// enum Opinion {
// Positive, Negative, NORMAL
// }