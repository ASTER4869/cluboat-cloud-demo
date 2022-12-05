package com.cluboat.springcloud.entity.param;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PutPersonInfoParam {
    private Integer userId;

    private String userName;

    private String userPhone;

    private String userSexual;

    private String userPhotoUrl;

    private String userSign;
}
