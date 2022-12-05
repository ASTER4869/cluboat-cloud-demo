package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.util.Objects;

@Entity
@TableName("collection")
@Table(name = "collection", schema = "cluboat", catalog = "")
@IdClass(CollectionEntityPK.class)
public class CollectionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId
    @Id
    @Column(name = "post_id")
    private int postId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionEntity that = (CollectionEntity) o;
        return postId == that.postId && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, userId);
    }
}
