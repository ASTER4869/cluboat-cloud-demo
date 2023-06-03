package com.cluboat.springcloud.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PopularPostDTO  implements Comparable<PopularPostDTO>{
    private Integer postId;
    private Integer commentCount;
    private Integer likesCount;
    private Integer score;


    private Integer userId;
    private Integer clubId;
    private String postTitle;
    private String postContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp postTime;
    private List<PostTagDTO> postTag;



    @Override
    public int compareTo(PopularPostDTO o) {
        // 降序排序
        return o.getScore() - this.getScore();
    }
}
