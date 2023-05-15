package com.cluboat.springcloud.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentGetDTO {
    private int commentId;
    private int userId;
    private int postId;
    private String commentContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp commentTime;
    private String  status;

    private String userName;
    private String userPhotoUrl;
}
