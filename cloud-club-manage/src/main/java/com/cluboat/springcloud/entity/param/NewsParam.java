package com.cluboat.springcloud.entity.param;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class NewsParam {
    public int userId;
    public int clubId;
    public String newsTitle;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Timestamp newsTime;
    public String newsContent;
}
