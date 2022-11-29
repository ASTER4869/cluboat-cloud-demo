package com.cluboat.springcloud.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "notification", schema = "cluboat", catalog = "")
public class NotificationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
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
    private String notification＿Title;
    @Basic
    @Column(name = "notification＿content")
    private String notification＿Content;
    @Basic
    @Column(name = "notification_time")
    private Timestamp notificationTime;
    @Basic
    @Column(name = "is_admin")
    private byte isAdmin;

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

    public String getNotification＿Title() {
        return notification＿Title;
    }

    public void setNotification＿Title(String notification＿Title) {
        this.notification＿Title = notification＿Title;
    }

    public String getNotification＿Content() {
        return notification＿Content;
    }

    public void setNotification＿Content(String notification＿Content) {
        this.notification＿Content = notification＿Content;
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
        return notificationId == that.notificationId && isAdmin == that.isAdmin && Objects.equals(sendAdminId, that.sendAdminId) && Objects.equals(sendUserId, that.sendUserId) && Objects.equals(notification＿Title, that.notification＿Title) && Objects.equals(notification＿Content, that.notification＿Content) && Objects.equals(notificationTime, that.notificationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationId, sendAdminId, sendUserId, notification＿Title, notification＿Content, notificationTime, isAdmin);
    }
}
