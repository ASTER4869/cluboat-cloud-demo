package com.cluboat.springcloud.entity.param;

//import net.sf.jsqlparser.expression.TimestampValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserParam {
    private int userPhone;
    private String userPassword;
//    private String userName;
//    private String userSexual;
//    private String userPhone;
//    private Timestamp userCreateTime;
//    private String userPhotoURL;
//    private String userSign;
}


