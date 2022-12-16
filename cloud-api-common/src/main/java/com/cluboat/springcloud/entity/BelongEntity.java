package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "belong", schema = "cluboat", catalog = "")
@TableName("belong")
@IdClass(BelongEntityPK.class)
public class BelongEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId("club_id")
    @Id
    @Column(name = "club_id")
    private int clubId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "permission")
    private byte permission;
    @Basic
    @Column(name = "state")
    private byte state;

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

    public byte getPermission() {
        return permission;
    }

    public void setPermission(byte permission) {
        this.permission = permission;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BelongEntity that = (BelongEntity) o;
        return clubId == that.clubId && userId == that.userId && permission == that.permission && state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubId, userId, permission, state);
    }
}
