package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("activity_apply")
@Table(name = "activity_apply", schema = "cluboat", catalog = "")
public class ActivityApplyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId(value = "activity_apply_id", type = IdType.AUTO)
    @Column(name = "activity_apply_id")
    private int activityApplyId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "club_id")
    private int clubId;
    @Basic
    @Column(name = "activity_apply_reason")
    private String activityApplyReason;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "activity_apply_time")
    private Timestamp activityApplyTime;
    @Basic
    @Column(name = "feedback")
    private String feedback;
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

    public int getActivityApplyId() {
        return activityApplyId;
    }

    public void setActivityApplyId(int activityApplyId) {
        this.activityApplyId = activityApplyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getActivityApplyReason() {
        return activityApplyReason;
    }

    public void setActivityApplyReason(String activityApplyReason) {
        this.activityApplyReason = activityApplyReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getActivityApplyTime() {
        return activityApplyTime;
    }

    public void setActivityApplyTime(Timestamp activityApplyTime) {
        this.activityApplyTime = activityApplyTime;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityApplyEntity that = (ActivityApplyEntity) o;
        return activityApplyId == that.activityApplyId && userId == that.userId && clubId == that.clubId && Objects.equals(activityApplyReason, that.activityApplyReason) && Objects.equals(status, that.status) && Objects.equals(activityApplyTime, that.activityApplyTime) && Objects.equals(feedback, that.feedback) && Objects.equals(activityTitle, that.activityTitle) && Objects.equals(activityDate, that.activityDate) && Objects.equals(activityTime, that.activityTime) && Objects.equals(activityCampus, that.activityCampus) && Objects.equals(activityLocation, that.activityLocation) && Objects.equals(activityPeopleNum, that.activityPeopleNum) && Objects.equals(activityDescription, that.activityDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityApplyId, userId, clubId, activityApplyReason, status, activityApplyTime, feedback, activityTitle, activityDate, activityTime, activityCampus, activityLocation, activityPeopleNum, activityDescription);
    }
}
