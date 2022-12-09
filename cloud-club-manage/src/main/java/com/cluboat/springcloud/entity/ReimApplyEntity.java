package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cluboat.springcloud.entity.param.BudgetApplyParam;
import com.cluboat.springcloud.entity.param.ReimApplyParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Data
@TableName("reim_apply")
@Table(name = "reim_apply", schema = "cluboat", catalog = "")
public class ReimApplyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId(value="reim_apply_id",type = IdType.AUTO)
    @Column(name = "reim_apply_id")
    private int reimApplyId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "club_id")
    private int clubId;
    @Basic
    @Column(name = "reim_apply_reason")
    private String reimApplyReason;
    @Basic
    @Column(name = "reim_apply_is_pass")
    private int reimApplyIsPass;
    @Basic
    @Column(name = "reim_apply_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp reimApplyTime;
    @TableField("feedback")
    private String feedback;

    public void setReimApply(ReimApplyParam reimApplyParam) {
        this.reimApplyReason= reimApplyParam.reimApplyReason;
        this.reimApplyTime = reimApplyParam.reimApplyTime;
        this.clubId = reimApplyParam.clubId;
        this.userId = reimApplyParam.userId;
    }

    public int getReimApplyId() {
        return reimApplyId;
    }

    public void setReimApplyId(int reimApplyId) {
        this.reimApplyId = reimApplyId;
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

    public String getReimApplyReason() {
        return reimApplyReason;
    }

    public void setReimApplyReason(String reimApplyReason) {
        this.reimApplyReason = reimApplyReason;
    }

    public int getReimApplyIsPass() {
        return reimApplyIsPass;
    }

    public void setReimApplyIsPass(int reimApplyIsPass) {
        this.reimApplyIsPass = reimApplyIsPass;
    }

    public Timestamp getReimApplyTime() {
        return reimApplyTime;
    }

    public void setReimApplyTime(Timestamp reimApplyTime) {
        this.reimApplyTime = reimApplyTime;
    }

    public String  getFeedback() {return feedback;}

    public void setFeedback(String feedback){this.feedback = feedback;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimApplyEntity that = (ReimApplyEntity) o;
        return reimApplyId == that.reimApplyId && userId == that.userId && clubId == that.clubId && reimApplyIsPass == that.reimApplyIsPass && Objects.equals(reimApplyReason, that.reimApplyReason) && Objects.equals(reimApplyTime, that.reimApplyTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimApplyId, userId, clubId, reimApplyReason, reimApplyIsPass, reimApplyTime);
    }
}
