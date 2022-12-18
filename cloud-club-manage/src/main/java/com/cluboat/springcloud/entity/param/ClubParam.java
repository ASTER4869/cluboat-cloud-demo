package com.cluboat.springcloud.entity.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ClubParam {

    public int clubId;
    public String clubName;

    public String clubInformation;
    public String clubImageUrl;
}
