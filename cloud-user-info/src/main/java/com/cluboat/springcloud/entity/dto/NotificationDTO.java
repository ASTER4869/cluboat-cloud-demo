package com.cluboat.springcloud.entity.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
    private Integer notificationId;

    private Integer sendAdminId;
    private Integer sendUserId;
    @Basic
    @Column(name = "notification＿title")
    @TableField(value = "notification＿title")
    private String notificationTitle;
    @Basic
    @Column(name = "notification＿content")
    @TableField(value = "notification＿content")
    private String notificationContent;
    private Timestamp notificationTime;
}
