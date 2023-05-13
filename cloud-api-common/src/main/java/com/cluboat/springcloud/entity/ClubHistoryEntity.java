package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("club_history")
@Table(name = "club_history", schema = "cluboat", catalog = "")
public class ClubHistoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "club_id")
    private int clubId;
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
        ClubHistoryEntity that = (ClubHistoryEntity) o;
        return id == that.id && clubId == that.clubId && userId == that.userId && num == that.num && Objects.equals(watchTime, that.watchTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clubId, userId, num, watchTime);
    }
}
