package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cluboat.springcloud.entity.param.ClubParam;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("club")
@Table(name = "club", schema = "cluboat", catalog = "")
public class ClubEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId(value="club_id",type = IdType.AUTO)
    @Column(name = "club_id")
    private int clubId;
    @Basic
    @Column(name = "club_name")
    private String clubName;
    @Basic
    @Column(name = "club_information")
    @TableField(value="club_information")
    private String clubInformation;
    @Basic
    @Column(name = "club_imageURL")
    @TableField(value="club_imageURL")
    private String clubImageUrl;
    @Basic
    @Column(name = "club_create_time")
    private Timestamp clubCreateTime;

    public void setClub(ClubParam clubParam) {
        this.clubName = clubParam.clubName;
        this.clubInformation = clubParam.clubInformation;
        this.clubImageUrl = clubParam.clubImageUrl;
        this.clubCreateTime = clubParam.clubCreateTime;
    }
    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubInformation() {
        return clubInformation;
    }

    public void setClubInformation(String clunInformation) {
        this.clubInformation = clunInformation;
    }

    public String getClubImageUrl() {
        return clubImageUrl;
    }

    public void setClubImageUrl(String clubImageUrl) {
        this.clubImageUrl = clubImageUrl;
    }

    public Timestamp getClubCreateTime() {
        return clubCreateTime;
    }

    public void setClubCreateTime(Timestamp clubCreateTime) {
        this.clubCreateTime = clubCreateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubEntity that = (ClubEntity) o;
        return clubId == that.clubId && Objects.equals(clubName, that.clubName) && Objects.equals(clubInformation, that.clubInformation) && Objects.equals(clubImageUrl, that.clubImageUrl) && Objects.equals(clubCreateTime, that.clubCreateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubId, clubName, clubInformation, clubImageUrl, clubCreateTime);
    }
}
