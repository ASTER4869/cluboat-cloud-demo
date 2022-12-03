package com.cluboat.springcloud.entity.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentAddParam {
    private Integer userId;
    private Integer postId;
    private String commentText;
    private Timestamp commentTime;
}
