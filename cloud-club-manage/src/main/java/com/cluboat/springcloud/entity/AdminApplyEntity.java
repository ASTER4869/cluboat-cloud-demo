package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cluboat.springcloud.entity.param.ActivityApplyParam;
import com.cluboat.springcloud.entity.param.AdminApplyParam;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("admin_apply")
@Table(name = "admin_apply", schema = "cluboat", catalog = "")
public class AdminApplyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId("admin_apply_id")
    @Column(name = "admin_apply_id")
    private int adminApplyId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "admin_club_id")
    private int adminClubId;
    @Basic
    @Column(name = "admin_apply_reason")
    private String adminApplyReason;
    @Basic
    @Column(name = "admin_apply_is_pass")
    private int adminApplyIsPass;
    @Basic
    @Column(name = "admin_apply_time")
    private Timestamp adminApplyTime;

    @Basic
    @Column(name = "feedback")
    private String feedback;

    public void setAdminApply(AdminApplyParam adminApplyParam) {
        this.adminApplyReason= adminApplyParam.adminApplyReason;
        this.adminApplyTime = adminApplyParam.adminApplyTime;
        this.adminClubId = adminApplyParam.adminClubId;
        this.userId = adminApplyParam.userId;
    }


    public int getAdminApplyId() {
        return adminApplyId;
    }

    public void setAdminApplyId(int adminApplyId) {
        this.adminApplyId = adminApplyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAdminClubId() {
        return adminClubId;
    }

    public void setAdminClubId(int adminClubId) {
        this.adminClubId = adminClubId;
    }

    public String getAdminApplyReason() {
        return adminApplyReason;
    }


    public void setAdminApplyReason(String adminApplyReason) {
        this.adminApplyReason = adminApplyReason;
    }

    public int getAdminApplyIsPass() {
        return adminApplyIsPass;
    }

    public void setAdminApplyIsPass(int adminApplyIsPass) {
        this.adminApplyIsPass = adminApplyIsPass;
    }

    public Timestamp getAdminApplyTime() {
        return adminApplyTime;
    }

    public void setAdminApplyTime(Timestamp adminApplyTime) {
        this.adminApplyTime = adminApplyTime;
    }

    public String  getFeedback() {return feedback;}

    public void setFeedback(String feedback){this.feedback = feedback;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminApplyEntity that = (AdminApplyEntity) o;
        return adminApplyId == that.adminApplyId && userId == that.userId && adminClubId == that.adminClubId && adminApplyIsPass == that.adminApplyIsPass && Objects.equals(adminApplyReason, that.adminApplyReason) && Objects.equals(adminApplyTime, that.adminApplyTime) && Objects.equals(feedback,that.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminApplyId, userId, adminClubId, adminApplyReason, adminApplyIsPass, adminApplyTime ,feedback);
    }
}
