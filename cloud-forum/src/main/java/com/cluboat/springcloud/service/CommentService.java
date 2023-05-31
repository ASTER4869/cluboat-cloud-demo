package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.CommentEntity;
import com.cluboat.springcloud.entity.dto.CommentGetDTO;
import com.cluboat.springcloud.entity.dto.PopularPostDTO;
import com.cluboat.springcloud.entity.param.CommentDeleteParam;

import java.util.List;
import java.util.Map;

public interface CommentService extends IService<CommentEntity> {
    public int DeleteComment(CommentDeleteParam param);

    public List<CommentGetDTO> GetCommentByPostId(Integer postId);

//    public List<PopularPostDTO> GetPopularPost();
}
