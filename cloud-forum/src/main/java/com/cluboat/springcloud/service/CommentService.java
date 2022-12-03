package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.CommentEntity;
import com.cluboat.springcloud.entity.param.CommentDeleteParam;

public interface CommentService extends IService<CommentEntity> {
    public int DeleteComment(CommentDeleteParam param);
}
