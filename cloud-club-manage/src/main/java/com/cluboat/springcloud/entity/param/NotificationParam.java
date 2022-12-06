package com.cluboat.springcloud.entity.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class NotificationParam {
    public Integer sendAdminId;
    public Integer sendUserId;
    public byte isAdmin;
    public String notificationTitle;
    public String notificationContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Timestamp notificationTime;
}
