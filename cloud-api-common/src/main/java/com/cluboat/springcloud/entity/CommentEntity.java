package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("comment")
@Table(name = "comment", schema = "cluboat", catalog = "")
public class CommentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId(value = "comment_id", type = IdType.AUTO)
    @Column(name = "comment_id")
    private int commentId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "post_id")
    private int postId;
    @Basic
    @Column(name = "comment_content")
    private String commentContent;
    @Basic
    @Column(name = "comment_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp commentTime;
    @Basic
    @Column(name = "status")
    private String  status;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return commentId == that.commentId && userId == that.userId && postId == that.postId && Objects.equals(commentContent, that.commentContent) && Objects.equals(commentTime, that.commentTime) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, userId, postId, commentContent, commentTime, status);
    }
}
