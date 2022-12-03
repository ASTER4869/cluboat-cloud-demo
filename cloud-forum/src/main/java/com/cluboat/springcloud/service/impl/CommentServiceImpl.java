package com.cluboat.springcloud.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.CommentEntity;
import com.cluboat.springcloud.entity.param.CommentDeleteParam;
import com.cluboat.springcloud.mapper.CommentMapper;
import com.cluboat.springcloud.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentEntity> implements CommentService {

    @Resource
    CommentMapper commentMapper;

    @Override
    public int DeleteComment(CommentDeleteParam param){
        LambdaQueryWrapper<CommentEntity> wrapper = new LambdaQueryWrapper<CommentEntity>()
                .eq(CommentEntity::getCommentId, param.getCommentId())
                .eq(CommentEntity::getPostId,param.getPostId());
        return commentMapper.delete(wrapper);
    }

}
