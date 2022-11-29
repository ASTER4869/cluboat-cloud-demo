package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("activity")

public class Activity {

  private long activityId;
  private long clubId;
  private String activityName;
  private String activityArea;
  private java.sql.Timestamp activityStartTime;
  private java.sql.Timestamp activityEndTime;
  private long activityIsPass;
  private String activityContent;


  public long getActivityId() {
    return activityId;
  }

  public void setActivityId(long activityId) {
    this.activityId = activityId;
  }


  public long getClubId() {
    return clubId;
  }

  public void setClubId(long clubId) {
    this.clubId = clubId;
  }


  public String getActivityName() {
    return activityName;
  }

  public void setActivityName(String activityName) {
    this.activityName = activityName;
  }


  public String getActivityArea() {
    return activityArea;
  }

  public void setActivityArea(String activityArea) {
    this.activityArea = activityArea;
  }


  public java.sql.Timestamp getActivityStartTime() {
    return activityStartTime;
  }

  public void setActivityStartTime(java.sql.Timestamp activityStartTime) {
    this.activityStartTime = activityStartTime;
  }


  public java.sql.Timestamp getActivityEndTime() {
    return activityEndTime;
  }

  public void setActivityEndTime(java.sql.Timestamp activityEndTime) {
    this.activityEndTime = activityEndTime;
  }


  public long getActivityIsPass() {
    return activityIsPass;
  }

  public void setActivityIsPass(long activityIsPass) {
    this.activityIsPass = activityIsPass;
  }


  public String getActivityContent() {
    return activityContent;
  }

  public void setActivityContent(String activityContent) {
    this.activityContent = activityContent;
  }

}
