package com.cluboat.springcloud.entity.param;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class NewsParam {
    public int userId;
    public int clubId;
    public String newsTitle;
    public Timestamp newsTime;
    public String newsContent;
}
