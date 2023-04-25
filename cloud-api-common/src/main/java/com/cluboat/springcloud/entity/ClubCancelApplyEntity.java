package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("club_cancel_apply")
@Table(name = "club_cancel_apply", schema = "cluboat", catalog = "")
public class ClubCancelApplyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId("cancel_apply_id")
    @Column(name = "cancel_apply_id")
    private int cancelApplyId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "club_id")
    private int clubId;
    @Basic
    @Column(name = "cancel_apply_reason")
    private String cancelApplyReason;

    @Basic
    @Column(name = "cancel_apply_time")
    private Timestamp cancelApplyTime;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "feedback")
    private String feedback;

    public int getCancelApplyId() {
        return cancelApplyId;
    }

    public void setCancelApplyId(int cancelApplyId) {
        this.cancelApplyId = cancelApplyId;
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

    public String getCancelApplyReason() {
        return cancelApplyReason;
    }

    public void setCancelApplyReason(String cancelApplyReason) {
        this.cancelApplyReason = cancelApplyReason;
    }

    public Timestamp getCancelApplyTime() {
        return cancelApplyTime;
    }

    public void setCancelApplyTime(Timestamp cancelApplyTime) {
        this.cancelApplyTime = cancelApplyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String  status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubCancelApplyEntity that = (ClubCancelApplyEntity) o;
        return cancelApplyId == that.cancelApplyId && userId == that.userId && clubId == that.clubId && Objects.equals(cancelApplyReason, that.cancelApplyReason) && Objects.equals(cancelApplyTime, that.cancelApplyTime) && Objects.equals(status, that.status) && Objects.equals(feedback, that.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cancelApplyId, userId, clubId, cancelApplyReason, status, cancelApplyTime, feedback);
    }
}
