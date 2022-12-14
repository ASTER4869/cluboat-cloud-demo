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
    private String userPhone;
    private String userPassword;
}


