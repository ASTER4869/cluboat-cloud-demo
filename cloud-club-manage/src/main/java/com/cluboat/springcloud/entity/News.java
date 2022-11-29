package com.sample;


public class News {

  private long newsId;
  private long userId;
  private long clubId;
  private String newsTitle;
  private java.sql.Timestamp newsTime;
  private String newsContent;


  public long getNewsId() {
    return newsId;
  }

  public void setNewsId(long newsId) {
    this.newsId = newsId;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getClubId() {
    return clubId;
  }

  public void setClubId(long clubId) {
    this.clubId = clubId;
  }


  public String getNewsTitle() {
    return newsTitle;
  }

  public void setNewsTitle(String newsTitle) {
    this.newsTitle = newsTitle;
  }


  public java.sql.Timestamp getNewsTime() {
    return newsTime;
  }

  public void setNewsTime(java.sql.Timestamp newsTime) {
    this.newsTime = newsTime;
  }


  public String getNewsContent() {
    return newsContent;
  }

  public void setNewsContent(String newsContent) {
    this.newsContent = newsContent;
  }

}
