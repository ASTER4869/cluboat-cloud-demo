package com.cluboat.springcloud.entity.param;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Timestamp;
@Data
public class NotificationParam {
    public Integer sendAdminId;
    public Integer sendUserId;
    public String notification_Title;
    public String notification_Content;
    public Timestamp notificationTime;
    public byte isAdmin;
}
