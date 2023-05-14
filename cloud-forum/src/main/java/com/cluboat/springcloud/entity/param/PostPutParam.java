package com.cluboat.springcloud.entity.param;

import com.cluboat.springcloud.entity.dto.PostTagDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPutParam {
    private Integer postId;
    private String postTitle;
    private String postContent;
    private List<PostTagDTO> postTag;
    private Byte isTop;

}
