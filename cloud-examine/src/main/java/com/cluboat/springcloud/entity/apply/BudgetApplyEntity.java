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
@TableName("budget_apply")
@Table(name = "budget_apply", schema = "cluboat", catalog = "")
public class BudgetApplyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId(value="budget_apply_id",type = IdType.AUTO)
    @Column(name = "budget_apply_id")
    private int budgetApplyId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "club_id")
    private int clubId;
    @Basic
    @Column(name = "budget_apply_reason")
    private String budgetApplyReason;
    @Basic
    @Column(name = "budget_apply_is_pass")
    private int budgetApplyIsPass;
    @Basic
    @Column(name = "budget_apply_time")

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp budgetApplyTime;
    @TableField("feedback")
    private String feedback;
    public int getBudgetApplyId() {
        return budgetApplyId;
    }

    public void setBudgetApplyId(int budgetApplyId) {
        this.budgetApplyId = budgetApplyId;
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

    public String getBudgetApplyReason() {
        return budgetApplyReason;
    }

    public void setBudgetApplyReason(String budgetApplyReason) {
        this.budgetApplyReason = budgetApplyReason;
    }

    public int getBudgetApplyIsPass() {
        return budgetApplyIsPass;
    }

    public void setBudgetApplyIsPass(int budgetApplyIsPass) {
        this.budgetApplyIsPass = budgetApplyIsPass;
    }

    public Timestamp getBudgetApplyTime() {
        return budgetApplyTime;
    }

    public void setBudgetApplyTime(Timestamp budgetApplyTime) {
        this.budgetApplyTime = budgetApplyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetApplyEntity that = (BudgetApplyEntity) o;
        return budgetApplyId == that.budgetApplyId && userId == that.userId && clubId == that.clubId && budgetApplyIsPass == that.budgetApplyIsPass && Objects.equals(budgetApplyReason, that.budgetApplyReason) && Objects.equals(budgetApplyTime, that.budgetApplyTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(budgetApplyId, userId, clubId, budgetApplyReason, budgetApplyIsPass, budgetApplyTime);
    }
}
