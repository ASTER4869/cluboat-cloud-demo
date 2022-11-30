package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("club_build_apply")
@Table(name = "club_build_apply", schema = "cluboat", catalog = "")
public class ClubBuildApplyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId("build_apply_id")
    @Column(name = "build_apply_id")
    private int buildApplyId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "build_apply_reason")
    private String buildApplyReason;
    @Basic
    @Column(name = "build_apply_time")
    private Timestamp buildApplyTime;
    @Basic
    @Column(name = "build_apply_is_pass")
    private byte buildApplyIsPass;
    @Basic
    @Column(name = "admin_club_name")
    private String adminClubName;

    public int getBuildApplyId() {
        return buildApplyId;
    }

    public void setBuildApplyId(int buildApplyId) {
        this.buildApplyId = buildApplyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBuildApplyReason() {
        return buildApplyReason;
    }

    public void setBuildApplyReason(String buildApplyReason) {
        this.buildApplyReason = buildApplyReason;
    }

    public Timestamp getBuildApplyTime() {
        return buildApplyTime;
    }

    public void setBuildApplyTime(Timestamp buildApplyTime) {
        this.buildApplyTime = buildApplyTime;
    }

    public byte getBuildApplyIsPass() {
        return buildApplyIsPass;
    }

    public void setBuildApplyIsPass(byte buildApplyIsPass) {
        this.buildApplyIsPass = buildApplyIsPass;
    }

    public String getAdminClubName() {
        return adminClubName;
    }

    public void setAdminClubName(String adminClubName) {
        this.adminClubName = adminClubName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubBuildApplyEntity that = (ClubBuildApplyEntity) o;
        return buildApplyId == that.buildApplyId && userId == that.userId && buildApplyIsPass == that.buildApplyIsPass && Objects.equals(buildApplyReason, that.buildApplyReason) && Objects.equals(buildApplyTime, that.buildApplyTime) && Objects.equals(adminClubName, that.adminClubName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildApplyId, userId, buildApplyReason, buildApplyTime, buildApplyIsPass, adminClubName);
    }
}
