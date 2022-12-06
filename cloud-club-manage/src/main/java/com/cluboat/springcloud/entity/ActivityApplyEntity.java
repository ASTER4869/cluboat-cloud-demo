package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cluboat.springcloud.entity.param.ActivityApplyParam;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("activity_apply")
@Table(name = "activity_apply", schema = "cluboat", catalog = "")
public class ActivityApplyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId(value="activity_apply_id",type = IdType.AUTO)
    @Column(name = "activity_apply_id")
    private  int activityApplyId;
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
    @Basic
    @Column(name = "feedback")
    private String feedback;

    public void setActivityApply(ActivityApplyParam activityApplyParam) {
        this.activityApplyReason = activityApplyParam.activityApplyReason;
        this.activityApplyTime = activityApplyParam.activityApplyTime;
        this.clubId = activityApplyParam.clubId;
        this.userId = activityApplyParam.userId;
    }


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

    public String  getFeedback() {return feedback;}

    public void setFeedback(String feedback){this.feedback = feedback;}

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
        return activityApplyId == that.activityApplyId && userId == that.userId && clubId == that.clubId && activityApplyIsPass == that.activityApplyIsPass && Objects.equals(activityApplyReason, that.activityApplyReason) && Objects.equals(activityApplyTime, that.activityApplyTime ) && Objects.equals(feedback,that.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityApplyId, userId, clubId, activityApplyReason, activityApplyIsPass, activityApplyTime,feedback);
    }
}
