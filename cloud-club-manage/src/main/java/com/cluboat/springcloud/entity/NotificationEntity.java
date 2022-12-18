package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cluboat.springcloud.entity.param.NewsParam;
import com.cluboat.springcloud.entity.param.NotificationParam;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("notification")
@Table(name = "notification", schema = "cluboat", catalog = "")
public class NotificationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId(value="notification_id",type = IdType.AUTO)
    @Column(name = "notification_id")
    private int notificationId;
    @Basic
    @Column(name = "send_admin_id")
    private Integer sendAdminId;
    @Basic
    @Column(name = "send_user_id")
    private Integer sendUserId;
    @Basic
    @Column(name = "notification＿title")
    private String notificationTitle;
    @Basic
    @Column(name = "notification＿content")
    private String notificationContent;
    @Basic
    @Column(name = "notification_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp notificationTime;
    @Basic
    @Column(name = "is_admin")
    private byte isAdmin;

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotification(NotificationParam notificationParam) {
        this.isAdmin = 0;
        this.sendAdminId = 0;
        this.sendUserId = notificationParam.sendAdminId;
        this.notificationContent = notificationParam.notificationContent;
        this.notificationTitle = notificationParam.notificationTitle;
        this.notificationTime = notificationParam.notificationTime;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public Integer getSendAdminId() {
        return sendAdminId;
    }

    public void setSendAdminId(Integer sendAdminId) {
        this.sendAdminId = sendAdminId;
    }

    public Integer getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(Integer sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getNotification＿Title() {
        return notificationTitle;
    }

    public void setNotification＿Title(String notification＿Title) {
        this.notificationTitle = notification＿Title;
    }

    public String getNotification＿Content() {
        return notificationContent;
    }

    public void setNotification＿Content(String notification＿Content) {
        this.notificationContent = notification＿Content;
    }

    public Timestamp getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(Timestamp notificationTime) {
        this.notificationTime = notificationTime;
    }

    public byte getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(byte isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationEntity that = (NotificationEntity) o;
        return notificationId == that.notificationId && isAdmin == that.isAdmin && Objects.equals(sendAdminId, that.sendAdminId) && Objects.equals(sendUserId, that.sendUserId) && Objects.equals(notificationTitle, that.notificationTitle) && Objects.equals(notificationContent, that.notificationContent) && Objects.equals(notificationTime, that.notificationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationId, sendAdminId, sendUserId, notificationTitle, notificationContent, notificationTime, isAdmin);
    }
}
