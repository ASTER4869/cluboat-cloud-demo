package com.cluboat.springcloud.entity.apply;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Data
@TableName("club_cancel_apply")
@Table(name = "club_cancel_apply", schema = "cluboat", catalog = "")
public class ClubCancelApplyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId(value="cancel_apply_id",type = IdType.AUTO)
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
    @Column(name = "cancel_apply_is_pass")
    private int cancelApplyIsPass;
    @Basic
    @Column(name = "cancel_apply_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp cancelApplyTime;
    @TableField("feedback")
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

    public int getCancelApplyIsPass() {
        return cancelApplyIsPass;
    }

    public void setCancelApplyIsPass(int cancelApplyIsPass) {
        this.cancelApplyIsPass = cancelApplyIsPass;
    }

    public Timestamp getCancelApplyTime() {
        return cancelApplyTime;
    }

    public void setCancelApplyTime(Timestamp cancelApplyTime) {
        this.cancelApplyTime = cancelApplyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubCancelApplyEntity that = (ClubCancelApplyEntity) o;
        return cancelApplyId == that.cancelApplyId && userId == that.userId && clubId == that.clubId && cancelApplyIsPass == that.cancelApplyIsPass && Objects.equals(cancelApplyReason, that.cancelApplyReason) && Objects.equals(cancelApplyTime, that.cancelApplyTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cancelApplyId, userId, clubId, cancelApplyReason, cancelApplyIsPass, cancelApplyTime);
    }
}
