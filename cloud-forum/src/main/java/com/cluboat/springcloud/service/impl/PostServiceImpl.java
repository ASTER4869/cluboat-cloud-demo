package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.*;
import com.cluboat.springcloud.entity.dto.PostListDTO;
import com.cluboat.springcloud.entity.param.PostAddParam;
import com.cluboat.springcloud.entity.param.PostGetByClubIdParam;
import com.cluboat.springcloud.entity.param.PostGetParam;
import com.cluboat.springcloud.mapper.PostMapper;
import com.cluboat.springcloud.service.PostService;
import com.cluboat.springcloud.service.PostTagService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, PostEntity> implements PostService{
    @Resource
    PostMapper postMapper;

    PostTagService postTagService;

    //有bug
//    @Override
//    public List<Integer> GetPostIdByBasicInfo(PostAddParam postAddParam){
//        List<PostListDTO> postList = postMapper.selectJoinList(PostListDTO.class,
//                new MPJLambdaWrapper<PostEntity>()
//                        .selectAll(PostEntity.class)
//                        .eq(PostEntity::getUserId, postAddParam.getUserId())
//                        .eq(PostEntity::getPostTime, postAddParam.getPostTime())
//                        .eq(PostEntity::getClubId, postAddParam.getClubId())
//        );
//        List<Integer> postIdList = new ArrayList<Integer>();
//        for(PostListDTO post:postList){
//            postIdList.add(post.getPostId());
//        }
//        return  postIdList;
//
//    }

    @Override
    public List<PostListDTO> GetPostListByParam(PostGetParam param){
        MPJLambdaWrapper<PostEntity> wrapper = new MPJLambdaWrapper<PostEntity>()
                .selectAll(PostEntity.class);
        if (param.getUserId() != null){
            wrapper = wrapper.eq(PostEntity::getUserId, param.getUserId());
        }
        if (param.getStatus() != null){
            wrapper = wrapper.eq(PostEntity::getStatus, param.getStatus());
        }
        List<PostListDTO> postList = postMapper.selectJoinList(PostListDTO.class, wrapper);
//        for(PostListDTO postListDTO:postList){
//            postListDTO.setPostTag(postTagService.GetPostTagListByPostId(postListDTO.getPostId()));
//        }

        return postList;
    }


    @Override
    public List<PostListDTO> GetPostListByClubId(PostGetByClubIdParam param){
        MPJLambdaWrapper<PostEntity> wrapper = new MPJLambdaWrapper<PostEntity>()
                .selectAll(PostEntity.class);
        if (param.getClubId() != null){
            wrapper = wrapper.eq(PostEntity::getClubId, param.getClubId());
        }
        if (param.getStatus() != null){
            wrapper = wrapper.eq(PostEntity::getStatus, param.getStatus());
        }
        List<PostListDTO> postList = postMapper.selectJoinList(PostListDTO.class, wrapper);
//        for(PostListDTO postListDTO:postList){
//            postListDTO.setPostTag(postTagService.GetPostTagListByPostId(postListDTO.getPostId()));
//        }

        return postList;
    }

    @Override
    public List<PostListDTO> GetAllPostList(){
        List<PostListDTO> postList = postMapper.selectJoinList(PostListDTO.class,
                new MPJLambdaWrapper<PostEntity>()
                        .selectAll(PostEntity.class)
        );

        return postList;
    }




}
