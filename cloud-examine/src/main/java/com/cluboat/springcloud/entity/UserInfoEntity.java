package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("user_info")
@Table(name = "user_info", schema = "cluboat", catalog = "")
public class UserInfoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId("user_id")
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "user_name")
    private String userName;
    @Basic
    @Column(name = "user_phone")
    private String userPhone;
    @Basic
    @Column(name = "user_sexual")
    private String userSexual;
    @Basic
    @Column(name = "user_create_time")
    private Timestamp userCreateTime;
    @Basic
    @Column(name = "user_photo_url")
    private String userPhotoUrl;
    @Basic
    @Column(name = "user_sign")
    private Object userSign;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserSexual() {
        return userSexual;
    }

    public void setUserSexual(String userSexual) {
        this.userSexual = userSexual;
    }

    public Timestamp getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Timestamp userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
    }

    public Object getUserSign() {
        return userSign;
    }

    public void setUserSign(Object userSign) {
        this.userSign = userSign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfoEntity that = (UserInfoEntity) o;
        return userId == that.userId && Objects.equals(userName, that.userName) && Objects.equals(userPhone, that.userPhone) && Objects.equals(userSexual, that.userSexual) && Objects.equals(userCreateTime, that.userCreateTime) && Objects.equals(userPhotoUrl, that.userPhotoUrl) && Objects.equals(userSign, that.userSign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userPhone, userSexual, userCreateTime, userPhotoUrl, userSign);
    }
}
