package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.CollectionEntity;
import com.cluboat.springcloud.entity.PostEntity;
import com.cluboat.springcloud.entity.PostTagEntity;
import com.cluboat.springcloud.entity.dto.CollectionDTO;
import com.cluboat.springcloud.mapper.CollectionMapper;
import com.cluboat.springcloud.service.CollectionService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, CollectionEntity> implements CollectionService {
    @Resource
    CollectionMapper collectionMapper;

    //此时获取的是没有tagName的数据
    @Override
    public List<CollectionDTO> GetCollection(Integer userId){
        List<CollectionDTO> collectionList = collectionMapper.selectJoinList(CollectionDTO.class, new MPJLambdaWrapper<CollectionEntity>()
                .select(CollectionEntity::getPostId)
                .selectAll(PostEntity.class)
                .leftJoin(PostEntity.class, PostEntity::getPostId, CollectionEntity::getPostId)
                .eq(CollectionEntity::getUserId, userId));
        return collectionList;
    }
}
