package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.util.Objects;

@Entity
@TableName("user_club")
@Table(name = "user_club", schema = "cluboat", catalog = "")
@IdClass(UserClubEntityPK.class)
public class UserClubEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "club_id")
    private int clubId;
    @Basic
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
