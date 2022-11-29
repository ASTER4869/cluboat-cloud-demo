package com.cluboat.springcloud.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class NotReceiverEntityPK implements Serializable {
    @Column(name = "notification_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationId;
    @Column(name = "receiver_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int receiverId;

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotReceiverEntityPK that = (NotReceiverEntityPK) o;
        return notificationId == that.notificationId && receiverId == that.receiverId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationId, receiverId);
    }
}
