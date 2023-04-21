package com.cluboat.springcloud.entity.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class NotificationParam {

    public Integer sendAdminId;
    public Integer sendUserId;

    public byte senderType;
    public byte receiverType;

    public Integer receiverId;
    public String notificationTitle;
    public String notificationContent;
}
