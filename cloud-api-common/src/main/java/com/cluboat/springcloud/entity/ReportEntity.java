package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.util.Objects;

@Entity
@TableName("report")
@Table(name = "report", schema = "cluboat", catalog = "")
@IdClass(ReportEntityPK.class)
public class ReportEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "post_id")
    private int postId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "comment_id")
    private Integer commentId;
    @Basic
    @Column(name = "report_reason")
    private String reportReason;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportEntity that = (ReportEntity) o;
        return postId == that.postId && userId == that.userId && Objects.equals(commentId, that.commentId) && Objects.equals(reportReason, that.reportReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, userId, commentId, reportReason);
    }
}
