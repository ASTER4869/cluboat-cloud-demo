package com.cluboat.springcloud.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "budget_apply", schema = "cluboat", catalog = "")
public class BudgetApplyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
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
    private byte budgetApplyIsPass;
    @Basic
    @Column(name = "budget_apply_time")
    private Timestamp budgetApplyTime;

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

    public byte getBudgetApplyIsPass() {
        return budgetApplyIsPass;
    }

    public void setBudgetApplyIsPass(byte budgetApplyIsPass) {
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
