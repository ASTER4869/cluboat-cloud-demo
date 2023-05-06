package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("report")
@Table(name = "report", schema = "cluboat", catalog = "")
public class ReportEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId(value = "report_id", type = IdType.AUTO)
    @Column(name = "report_id")
    private int reportId;
    @Basic
    @Column(name = "target_id")
    private int targetId;
    @Basic
    @Column(name = "report_reason")
    private String reportReason;
    @Basic
    @Column(name = "reporter_id")
    private Integer reporterId;
    @Basic
    @Column(name = "target_type")
    private String targetType;
    @Basic
    @Column(name = "report_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp reportTime;
    @Basic
    @Column(name = "status")
    private String  status;
    @Basic
    @Column(name = "punish")
    private String  punish;
    @Basic
    @Column(name = "feedback")
    private String feedback;

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public Integer getReporterId() {
        return reporterId;
    }

    public void setReporterId(Integer reporterId) {
        this.reporterId = reporterId;
    }

    public String getTargetType() {
        return targetType;
    }



    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public Timestamp getReportTime() {
        return reportTime;
    }

    public void setReportTime(Timestamp reportTime) {
        this.reportTime = reportTime;
    }

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public String getPunish() {
        return punish;
    }



    public void setPunish(String punish) {
        this.punish = punish;
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
        ReportEntity that = (ReportEntity) o;
        return reportId == that.reportId && targetId == that.targetId && Objects.equals(reportReason, that.reportReason) && Objects.equals(reporterId, that.reporterId) && Objects.equals(targetType, that.targetType) && Objects.equals(reportTime, that.reportTime) && Objects.equals(status, that.status) && Objects.equals(punish, that.punish) && Objects.equals(feedback, that.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportId, targetId, reportReason, reporterId, targetType, reportTime, status, punish, feedback);
    }
}
