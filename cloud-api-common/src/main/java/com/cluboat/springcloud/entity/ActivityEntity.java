package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("activity")
@Table(name = "activity", schema = "cluboat", catalog = "")
public class ActivityEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId
    @Id
    @TableId("activity_id")
    @Column(name = "activity_id")
    private int activityId;
    @Basic
    @Column(name = "club_id")
    private int clubId;
    @Basic
    @Column(name = "activity_name")
    private String activityName;
    @Basic
    @Column(name = "activity_area")
    private String activityArea;
    @Basic
    @Column(name = "activity_start_time")
    private Timestamp activityStartTime;
    @Basic
    @Column(name = "activity_end_time")
    private Timestamp activityEndTime;
    @Basic
    @Column(name = "activity_is_pass")
    private int activityIsPass;
    @Basic
    @Column(name = "activity_content")
    private String activityContent;

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

    public Timestamp getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Timestamp activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public Timestamp getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Timestamp activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public int getActivityIsPass() {
        return activityIsPass;
    }

    public void setActivityIsPass(int activityIsPass) {
        this.activityIsPass = activityIsPass;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityEntity that = (ActivityEntity) o;
        return activityId == that.activityId && clubId == that.clubId && activityIsPass == that.activityIsPass && Objects.equals(activityName, that.activityName) && Objects.equals(activityArea, that.activityArea) && Objects.equals(activityStartTime, that.activityStartTime) && Objects.equals(activityEndTime, that.activityEndTime) && Objects.equals(activityContent, that.activityContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId, clubId, activityName, activityArea, activityStartTime, activityEndTime, activityIsPass, activityContent);
    }
}
