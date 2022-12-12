package com.cluboat.springcloud.entity.param;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class NewsUpdateParam {
    public int newsId;
    public String newsTitle;
    public String newsContent;
}
