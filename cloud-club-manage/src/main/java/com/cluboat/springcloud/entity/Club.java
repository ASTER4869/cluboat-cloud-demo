    package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("club")
public class Club {

    /* === 数据成员 ===*/
    private long clubId;
    private String clubName;
    private String clubInformation;
    private String clubImageUrl;
    private java.sql.Timestamp clubCreateTime;

    /* === 方法 === */
    public long getClubId() {
        return clubId;
    }

    public void setClubId(long clubId) {
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


    public java.sql.Timestamp getClubCreateTime() {
        return clubCreateTime;
    }

    public void setClubCreateTime(java.sql.Timestamp clubCreateTime) {
        this.clubCreateTime = clubCreateTime;
    }

}
