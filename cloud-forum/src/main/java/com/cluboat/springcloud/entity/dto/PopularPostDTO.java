package com.cluboat.springcloud.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PopularPostDTO  implements Comparable<PopularPostDTO>{
    private Integer postId;
    private Integer commentCount;
    private Integer likesCount;
    private Integer score;


    private PostListDTO postInfo;



    @Override
    public int compareTo(PopularPostDTO o) {
        // 降序排序
        return o.getScore() - this.getScore();
    }
}
