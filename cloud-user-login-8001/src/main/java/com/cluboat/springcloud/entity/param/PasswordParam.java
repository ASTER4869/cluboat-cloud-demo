package com.cluboat.springcloud.entity.param;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PasswordParam {
    private int userId;
    private String inputPassword;
    private String newPassword;
}