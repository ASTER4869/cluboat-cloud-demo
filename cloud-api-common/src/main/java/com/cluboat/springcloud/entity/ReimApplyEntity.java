package com.cluboat.springcloud.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "reim_apply", schema = "cluboat", catalog = "")
public class ReimApplyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
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
    private byte reimApplyIsPass;
    @Basic
    @Column(name = "reim_apply_time")
    private Timestamp reimApplyTime;

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

    public byte getReimApplyIsPass() {
        return reimApplyIsPass;
    }

    public void setReimApplyIsPass(byte reimApplyIsPass) {
        this.reimApplyIsPass = reimApplyIsPass;
    }

    public Timestamp getReimApplyTime() {
        return reimApplyTime;
    }

    public void setReimApplyTime(Timestamp reimApplyTime) {
        this.reimApplyTime = reimApplyTime;
    }

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
