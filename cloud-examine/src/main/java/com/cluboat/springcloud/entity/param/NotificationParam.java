package com.cluboat.springcloud.entity.param;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Timestamp;
@Data
public class NotificationParam {
    public Integer sendAdminId;
    public Integer sendUserId;
    public String notificationTitle;
    public String notificationContent;
    public Timestamp notificationTime;
    public Integer isAdmin;
}
