package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.*;
import com.cluboat.springcloud.entity.dto.PostListDTO;
import com.cluboat.springcloud.entity.param.PostAddParam;
import com.cluboat.springcloud.entity.param.PostGetByClubIdParam;
import com.cluboat.springcloud.entity.param.PostGetParam;
import com.cluboat.springcloud.mapper.ClubMapper;
import com.cluboat.springcloud.mapper.PostMapper;
import com.cluboat.springcloud.mapper.UserInfoMapper;
import com.cluboat.springcloud.service.ClubService;
import com.cluboat.springcloud.service.PostService;
import com.cluboat.springcloud.service.PostTagService;
import com.cluboat.springcloud.service.UserInfoService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, PostEntity> implements PostService{

    private String blacklist = "~!@#$%^&*()_+|`-=\\{}[]:\";'<>/";
    @Resource
    PostMapper postMapper;
    @Resource
    ClubService clubService;
    @Resource
    UserInfoService userInfoService;

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
        List<PostListDTO> postList = new ArrayList<>();
        PostListDTO resultPost = new PostListDTO();

        if(clubService.getById(param.getClubId()) == null){
            resultPost.setPostTitle("查询社团不存在");
            postList.add(resultPost);
        }
        else{
            MPJLambdaWrapper<PostEntity> lambdaWrapper = new MPJLambdaWrapper<PostEntity>()
                    .selectAll(PostEntity.class);
            if (param.getClubId() != null){
                lambdaWrapper = lambdaWrapper.eq(PostEntity::getClubId, param.getClubId());
            }
            if (param.getStatus() != null){
                lambdaWrapper = lambdaWrapper.eq(PostEntity::getStatus, param.getStatus());
            }

            if(list(lambdaWrapper).isEmpty()){
                resultPost.setPostTitle("社团不存在满足查询条件的帖子");
                postList.add(resultPost);
            }
            else{
                resultPost.setPostTitle("帖子存在");
                postList.add(resultPost);
                //postList = postMapper.selectJoinList(PostListDTO.class, lambdaWrapper);
            }
        }
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

    @Override
    public String addPost(PostEntity postEntity){
        if(userInfoService.getById(postEntity.getUserId()) == null){
            return "用户不存在";
        }

        if(clubService.getById(postEntity.getClubId()) == null){
            return "帖子所属社团不存在";
        }

        for(char c : blacklist.toCharArray()){
            if(postEntity.getPostTitle() == "" || postEntity.getPostTitle().contains(String.valueOf(c))){
                return "帖子标题包含非法字符";
            }
        }

        return "创建成功";

    }




}
