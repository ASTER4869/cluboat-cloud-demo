package com.cluboat.springcloud.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "activity_apply", schema = "cluboat", catalog = "")
public class ActivityApplyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
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
    @Column(name = "activity_apply_is_pass")
    private byte activityApplyIsPass;
    @Basic
    @Column(name = "activity_apply_time")
    private Timestamp activityApplyTime;

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

    public byte getActivityApplyIsPass() {
        return activityApplyIsPass;
    }

    public void setActivityApplyIsPass(byte activityApplyIsPass) {
        this.activityApplyIsPass = activityApplyIsPass;
    }

    public Timestamp getActivityApplyTime() {
        return activityApplyTime;
    }

    public void setActivityApplyTime(Timestamp activityApplyTime) {
        this.activityApplyTime = activityApplyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityApplyEntity that = (ActivityApplyEntity) o;
        return activityApplyId == that.activityApplyId && userId == that.userId && clubId == that.clubId && activityApplyIsPass == that.activityApplyIsPass && Objects.equals(activityApplyReason, that.activityApplyReason) && Objects.equals(activityApplyTime, that.activityApplyTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityApplyId, userId, clubId, activityApplyReason, activityApplyIsPass, activityApplyTime);
    }
}
