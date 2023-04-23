package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cluboat.springcloud.entity.param.BudgetApplyParam;
import com.cluboat.springcloud.entity.param.ClubBuildApplyParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Data
@TableName("club_build_apply")
@Table(name = "club_build_apply", schema = "cluboat", catalog = "")
public class ClubBuildApplyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId(value="build_apply_id",type = IdType.AUTO)
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp buildApplyTime;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "admin_club_name")
    private String adminClubName;
    @TableField("feedback")
    private String feedback;

    public void setBuildApply(ClubBuildApplyParam clubBuildApplyParam) {
        this.buildApplyReason= clubBuildApplyParam.buildApplyReason;
        this.buildApplyTime = clubBuildApplyParam.buildApplyTime;
        this.adminClubName = clubBuildApplyParam.clubName;
        this.userId = clubBuildApplyParam.userId;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String inStatus) {
        this.status = inStatus;
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
        return buildApplyId == that.buildApplyId && userId == that.userId && status == that.status && Objects.equals(buildApplyReason, that.buildApplyReason) && Objects.equals(buildApplyTime, that.buildApplyTime) && Objects.equals(adminClubName, that.adminClubName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildApplyId, userId, buildApplyReason, buildApplyTime, status, adminClubName);
    }
}
