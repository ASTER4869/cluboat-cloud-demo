package com.cluboat.springcloud.entity.param;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class NotificationParam {
    public Integer sendAdminId;
    public Integer sendUserId;
    public byte isAdmin;
    public String notificationTitle;
    public String notificationContent;
    public Timestamp notificationTime;
}
