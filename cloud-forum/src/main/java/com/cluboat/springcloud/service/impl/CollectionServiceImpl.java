package com.cluboat.springcloud.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.CollectionEntity;
import com.cluboat.springcloud.entity.CommentEntity;
import com.cluboat.springcloud.entity.param.CollectionDeleteParam;
import com.cluboat.springcloud.mapper.CollectionMapper;
import com.cluboat.springcloud.service.CollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, CollectionEntity> implements CollectionService {
    @Resource
    CollectionMapper collectionMapper;

    @Override
    public int DeleteCollection(CollectionDeleteParam param){
        LambdaQueryWrapper<CollectionEntity> wrapper = new LambdaQueryWrapper<CollectionEntity>()
                .eq(CollectionEntity::getPostId,param.getPostId())
                .eq(CollectionEntity::getUserId,param.getUserId());
        return collectionMapper.delete(wrapper);
    }
}
