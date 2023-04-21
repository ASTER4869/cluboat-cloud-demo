package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("belong")
public class Belong {
    int clubId;
    int userId;
    int permission;
    int state;

    public int getClubId() {
        return clubId;
    }
    public int getUserId() {
        return userId;
    }
}
