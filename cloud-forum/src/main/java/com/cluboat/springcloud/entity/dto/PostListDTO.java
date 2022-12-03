package com.cluboat.springcloud.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostListDTO {
    private Integer postId;
    private Integer userId;
    private Integer clubId;
    private String postTitle;
    private Timestamp postTime;
    private List<PostTagDTO> postTag;
}
