package com.cluboat.springcloud.entity.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Integer receiverId;
    @Basic
    @Column(name = "notification＿title")
    @TableField(value = "notification＿title")
    private String notificationTitle;
    @Basic
    @Column(name = "notification＿content")
    @TableField(value = "notification＿content")
    private String notificationContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp notificationTime;

    private byte senderType;
}
