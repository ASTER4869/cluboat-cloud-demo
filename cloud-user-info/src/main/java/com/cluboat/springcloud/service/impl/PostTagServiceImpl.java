package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.FollowEntity;
import com.cluboat.springcloud.entity.NotReceiverEntity;
import com.cluboat.springcloud.entity.NotificationEntity;
import com.cluboat.springcloud.entity.PostTagEntity;
import com.cluboat.springcloud.entity.dto.NotificationDTO;
import com.cluboat.springcloud.entity.dto.PostTagDTO;
import com.cluboat.springcloud.mapper.FollowMapper;
import com.cluboat.springcloud.mapper.PostTagMapper;
import com.cluboat.springcloud.service.FollowService;
import com.cluboat.springcloud.service.PostTagService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PostTagServiceImpl  extends ServiceImpl<PostTagMapper, PostTagEntity> implements PostTagService {
    @Resource
    PostTagMapper postTagMapper;

    @Override
    public List<PostTagDTO> GetPostTagList(Integer postId){
        List<PostTagDTO> postTagList = postTagMapper.selectJoinList(PostTagDTO.class, new MPJLambdaWrapper<PostTagEntity>()
                .selectAll(PostTagEntity.class)
                .eq(PostTagEntity::getPostId, postId));
        return postTagList;
    }
}
