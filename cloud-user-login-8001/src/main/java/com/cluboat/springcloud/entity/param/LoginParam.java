package com.cluboat.springcloud.entity.param;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class LoginParam {
    private int isAdmin;
    private String loginAccount;
    private String inputPassword;
}
