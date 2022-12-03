package com.cluboat.springcloud.entity.param;

import com.cluboat.springcloud.entity.dto.PostTagDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostAddParam {
    private Integer userId;
    private Integer clubId;
    private String postTitle;
    private Timestamp postTime;
    private List<String> postTag;
}
