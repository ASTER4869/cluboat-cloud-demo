package com.cluboat.springcloud.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDTO {
    private Integer postId;
    private Integer clubId;
    private String postTitle;
    private Timestamp postTime;
    private List<PostTagDTO> postTag;
}
