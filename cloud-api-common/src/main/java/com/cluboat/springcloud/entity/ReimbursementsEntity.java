package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("reimbursements")
@Table(name = "reimbursements", schema = "cluboat", catalog = "")
public class ReimbursementsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId("reim_id")
    @Column(name = "reim_id")
    private int reimId;
    @Basic
    @Column(name = "club_id")
    private int clubId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "feedback")
    private String feedback;

    public int getReimId() {
        return reimId;
    }

    public void setReimId(int reimId) {
        this.reimId = reimId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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
        ReimbursementsEntity that = (ReimbursementsEntity) o;
        return reimId == that.reimId && clubId == that.clubId && userId == that.userId && Objects.equals(title, that.title) && Objects.equals(amount, that.amount) && Objects.equals(description, that.description) && Objects.equals(createTime, that.createTime) && Objects.equals(status, that.status) && Objects.equals(feedback, that.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimId, clubId, userId, title, amount, description, createTime, status, feedback);
    }
}
