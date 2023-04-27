package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("activity")
@Table(name = "activity", schema = "cluboat", catalog = "")
public class ActivityEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId("activity_id")
    @Column(name = "activity_id")
    private int activityId;
    @Basic
    @Column(name = "club_id")
    private int clubId;
    @Basic
    @Column(name = "activity_title")
    private String activityTitle;
    @Basic
    @Column(name = "activity_date")
    private Date activityDate;
    @Basic
    @Column(name = "activity_time")
    private String activityTime;
    @Basic
    @Column(name = "activity_campus")
    private String activityCampus;
    @Basic
    @Column(name = "activity_location")
    private String activityLocation;
    @Basic
    @Column(name = "activity_people_num")
    private String activityPeopleNum;
    @Basic
    @Column(name = "activity_description")
    private String activityDescription;
    @Basic
    @Column(name = "user_id")
    private int userId;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getActivityCampus() {
        return activityCampus;
    }

    public void setActivityCampus(String activityCampus) {
        this.activityCampus = activityCampus;
    }

    public String getActivityLocation() {
        return activityLocation;
    }

    public void setActivityLocation(String activityLocation) {
        this.activityLocation = activityLocation;
    }

    public String getActivityPeopleNum() {
        return activityPeopleNum;
    }

    public void setActivityPeopleNum(String activityPeopleNum) {
        this.activityPeopleNum = activityPeopleNum;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityEntity that = (ActivityEntity) o;
        return activityId == that.activityId && clubId == that.clubId && userId == that.userId && Objects.equals(activityTitle, that.activityTitle) && Objects.equals(activityDate, that.activityDate) && Objects.equals(activityTime, that.activityTime) && Objects.equals(activityCampus, that.activityCampus) && Objects.equals(activityLocation, that.activityLocation) && Objects.equals(activityPeopleNum, that.activityPeopleNum) && Objects.equals(activityDescription, that.activityDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId, userId, clubId, activityTitle, activityDate, activityTime, activityCampus, activityLocation, activityPeopleNum, activityDescription);
    }
}
