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
    @Column(name = "notification＿title")
    @TableField("notification＿title")
    private String notification_Title;
    @Basic
    @TableField("notification＿content")
    @Column(name = "notification＿content")
    private String notification_Content;
    @Basic
    @TableField("notification_time")
    @Column(name = "notification_time")
    private Timestamp notificationTime;
    @Basic
    @TableField("is_admin")
    @Column(name = "is_admin")
    private byte isAdmin;
    public void setNotification(NotificationParam notificationParam){
        this.sendAdminId = notificationParam.sendAdminId;
        this.sendUserId = notificationParam.sendUserId;
        this.notification_Title = notificationParam.notification_Title;
        this.notification_Content = notificationParam.notification_Content;
        this.notificationTime = notificationParam.notificationTime;
        this.isAdmin = notificationParam.isAdmin;
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

    public String getNotification_Title() {
        return notification_Title;
    }

    public void setNotification_Title(String notification_Title) {
        this.notification_Title = notification_Title;
    }

    public String getNotification_Content() {
        return notification_Content;
    }

    public void setNotification_Content(String notification_Content) {
        this.notification_Content = notification_Content;
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
        return notificationId == that.notificationId && isAdmin == that.isAdmin && Objects.equals(sendAdminId, that.sendAdminId) && Objects.equals(sendUserId, that.sendUserId) && Objects.equals(notification_Title, that.notification_Title) && Objects.equals(notification_Content, that.notification_Content) && Objects.equals(notificationTime, that.notificationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationId, sendAdminId, sendUserId, notification_Title, notification_Content, notificationTime, isAdmin);
    }
}
