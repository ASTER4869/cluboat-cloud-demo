package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.*;
import com.cluboat.springcloud.entity.dto.PostListDTO;
import com.cluboat.springcloud.mapper.PostMapper;
import com.cluboat.springcloud.service.PostService;
import com.cluboat.springcloud.service.PostTagService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, PostEntity> implements PostService{
    @Resource
    PostMapper postMapper;

    PostTagService postTagService;

    @Override
    public List<PostListDTO> GetPostListByUserId(Integer userId){
        List<PostListDTO> postList = postMapper.selectJoinList(PostListDTO.class,
                new MPJLambdaWrapper<PostEntity>()
                        .selectAll(PostEntity.class)
                        .eq(PostEntity::getUserId,userId)
        );
        for(PostListDTO postListDTO:postList){
            postListDTO.setPostTag(postTagService.GetPostTagListByPostId(postListDTO.getPostId()));
        }

        return postList;
    }


}
