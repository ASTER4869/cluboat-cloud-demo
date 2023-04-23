package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("budgets")
@Table(name = "budgets", schema = "cluboat", catalog = "")
public class BudgetsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId("budget_id")
    @Column(name = "budget_id")
    private int budgetId;
    @Basic
    @Column(name = "club_id")
    private int clubId;
    @Basic
    @Column(name = "applicant_id")
    private int applicantId;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;
    @Basic
    @Column(name = "feedback")
    private String feedback;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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
        BudgetsEntity that = (BudgetsEntity) o;
        return budgetId == that.budgetId && clubId == that.clubId && applicantId == that.applicantId && Objects.equals(title, that.title) && Objects.equals(amount, that.amount) && Objects.equals(status, that.status) && Objects.equals(createTime, that.createTime) && Objects.equals(feedback, that.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(budgetId, clubId, applicantId, title, amount, status, createTime, feedback);
    }
}
