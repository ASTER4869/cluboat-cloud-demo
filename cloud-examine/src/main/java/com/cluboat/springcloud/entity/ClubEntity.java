package com.cluboat.springcloud.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "club", schema = "cluboat", catalog = "")
public class ClubEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "club_id")
    private int clubId;
    @Basic
    @Column(name = "club_name")
    private String clubName;
    @Basic
    @Column(name = "clun_information")
    private String clunInformation;
    @Basic
    @Column(name = "club_imageURL")
    private String clubImageUrl;
    @Basic
    @Column(name = "club_create_time")
    private Timestamp clubCreateTime;

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

    public String getClunInformation() {
        return clunInformation;
    }

    public void setClunInformation(String clunInformation) {
        this.clunInformation = clunInformation;
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
        return clubId == that.clubId && Objects.equals(clubName, that.clubName) && Objects.equals(clunInformation, that.clunInformation) && Objects.equals(clubImageUrl, that.clubImageUrl) && Objects.equals(clubCreateTime, that.clubCreateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubId, clubName, clunInformation, clubImageUrl, clubCreateTime);
    }
}
