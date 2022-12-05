package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.util.Objects;

@Entity
@TableName("not_receiver")
@Table(name = "not_receiver", schema = "cluboat", catalog = "")
@IdClass(NotReceiverEntityPK.class)
public class NotReceiverEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId
    @Column(name = "notification_id")
    private int notificationId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "receiver_id")
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
        NotReceiverEntity that = (NotReceiverEntity) o;
        return notificationId == that.notificationId && receiverId == that.receiverId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationId, receiverId);
    }
}
