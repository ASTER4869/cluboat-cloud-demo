package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class BelongEntityPK implements Serializable {
    @Column(name = "club_id")
    @TableId("club_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clubId;
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BelongEntityPK that = (BelongEntityPK) o;
        return clubId == that.clubId && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubId, userId);
    }
}
