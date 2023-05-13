package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("post_history")
@Table(name = "post_history", schema = "cluboat", catalog = "")
public class PostHistoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @Basic
    @Column(name = "post_id")
    private int postId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "num")
    private int num;
    @Basic
    @Column(name = "watch_time")
    private Timestamp watchTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Timestamp getWatchTime() {
        return watchTime;
    }

    public void setWatchTime(Timestamp watchTime) {
        this.watchTime = watchTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostHistoryEntity that = (PostHistoryEntity) o;
        return id == that.id && postId == that.postId && userId == that.userId && num == that.num && Objects.equals(watchTime, that.watchTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postId, userId, num, watchTime);
    }
}
