package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("post")
@Table(name = "post", schema = "cluboat", catalog = "")
public class PostEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId(value = "post_id", type = IdType.AUTO)
    @Column(name = "post_id")
    private int postId;
    @Basic
    @Column(name = "post_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp postTime;
    @Basic
    @Column(name = "is_top")
    private byte isTop;
    @Basic
    @Column(name = "post_title")
    private String postTitle;
    @Basic
    @Column(name = "club_id")
    private int clubId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "status")
    private String status;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    public byte getIsTop() {
        return isTop;
    }

    public void setIsTop(byte isTop) {
        this.isTop = isTop;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
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
        PostEntity that = (PostEntity) o;
        return postId == that.postId && isTop == that.isTop && clubId == that.clubId && userId == that.userId && Objects.equals(postTime, that.postTime) && Objects.equals(postTitle, that.postTitle) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, postTime, isTop, postTitle, clubId, userId, status);
    }
}
