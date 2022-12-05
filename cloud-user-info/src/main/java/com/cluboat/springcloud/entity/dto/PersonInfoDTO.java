package com.cluboat.springcloud.entity.dto;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonInfoDTO {
    private Integer userId;

    private String userName;

    private String userPhone;

    private String userSexual;

    private Timestamp userCreateTime;

    private String userPhotoUrl;

    private String userSign;
}
