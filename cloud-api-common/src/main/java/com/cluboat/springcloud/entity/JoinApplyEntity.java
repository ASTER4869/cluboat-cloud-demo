package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("join_apply")
@Table(name = "join_apply", schema = "cluboat", catalog = "")
public class JoinApplyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId("join_apply_id")
    @Column(name = "join_apply_id")
    private int joinApplyId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "join_club_id")
    private int joinClubId;
    @Basic
    @Column(name = "join_apply_content")
    private String joinApplyContent;
    @Basic
    @Column(name = "join_apply_time")
    private Timestamp joinApplyTime;
    @Basic
    @Column(name = "status")
    private String  status;
    @Basic
    @Column(name = "feedback")
    private String feedback;

    public int getJoinApplyId() {
        return joinApplyId;
    }

    public void setJoinApplyId(int joinApplyId) {
        this.joinApplyId = joinApplyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getJoinClubId() {
        return joinClubId;
    }

    public void setJoinClubId(int joinClubId) {
        this.joinClubId = joinClubId;
    }

    public String getJoinApplyContent() {
        return joinApplyContent;
    }

    public void setJoinApplyContent(String joinApplyContent) {
        this.joinApplyContent = joinApplyContent;
    }

    public Timestamp getJoinApplyTime() {
        return joinApplyTime;
    }

    public void setJoinApplyTime(Timestamp joinApplyTime) {
        this.joinApplyTime = joinApplyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
        JoinApplyEntity that = (JoinApplyEntity) o;
        return joinApplyId == that.joinApplyId && userId == that.userId && joinClubId == that.joinClubId && Objects.equals(joinApplyContent, that.joinApplyContent) && Objects.equals(joinApplyTime, that.joinApplyTime) && Objects.equals(status, that.status) && Objects.equals(feedback, that.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(joinApplyId, userId, joinClubId, joinApplyContent, joinApplyTime, status, feedback);
    }
}
