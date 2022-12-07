package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cluboat.springcloud.entity.param.NotificationParam;

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
    @TableField("notification_title")
    @Column(name = "notification_title")
    private String notificationTitle;
    @Basic
    @Column(name = "notification_content")
    @TableField("notification_content")
    private String notificationContent;
    @Basic
    @Column(name = "notification_time")
    private Timestamp notificationTime;
    @Basic
    @Column(name = "is_admin")
    private int isAdmin;
    public void setNotification(NotificationParam notificationParam)
    {
        this.sendAdminId = notificationParam.sendAdminId;
        this.sendUserId = notificationParam.sendUserId;
        this.notificationTitle = notificationParam.notificationTitle;
        this.notificationContent = notificationParam.notificationContent;
        this.notificationTime = notificationParam.notificationTime;
        this.isAdmin= notificationParam.isAdmin;
    }
    public int getNotificationId() {
        return notificationId;
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

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public Timestamp getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(Timestamp notificationTime) {
        this.notificationTime = notificationTime;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
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
