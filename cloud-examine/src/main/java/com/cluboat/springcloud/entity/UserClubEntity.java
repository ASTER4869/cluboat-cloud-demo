package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_club", schema = "cluboat", catalog = "")
@TableName("user_club")
@IdClass(UserClubEntityPK.class)
public class UserClubEntity {

    @MppMultiId
    @TableField(value = "user_id")
    @Column(name = "user_id")
    private int userId;

    @MppMultiId
    @TableField(value = "club_id")
    @Column(name = "club_id")
    private int clubId;
    @Basic
    @TableField(value = "user_state")
    @Column(name = "user_state")
    private byte userState;

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

    public byte getUserState() {
        return userState;
    }

    public void setUserState(byte userState) {
        this.userState = userState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserClubEntity that = (UserClubEntity) o;
        return userId == that.userId && clubId == that.clubId && userState == that.userState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, clubId, userState);
    }
}
