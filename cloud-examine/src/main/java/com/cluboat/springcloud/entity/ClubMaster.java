package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.cluboat.springcloud.entity.param.NotificationParam;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Timestamp;

public class ClubMaster {

    public int userId;

    public String userName;

    public String userPhotoUrl;

    public int clubId;

    public String clubName;
}

