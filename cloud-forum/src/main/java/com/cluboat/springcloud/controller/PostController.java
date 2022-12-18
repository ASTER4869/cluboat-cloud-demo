package com.cluboat.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.CommentEntity;
import com.cluboat.springcloud.entity.PostEntity;
import com.cluboat.springcloud.entity.PostTagEntity;
import com.cluboat.springcloud.entity.dto.PostTagDTO;
import com.cluboat.springcloud.entity.param.PostAddParam;
import com.cluboat.springcloud.entity.param.PostPutParam;
import com.cluboat.springcloud.service.PostService;
import com.cluboat.springcloud.service.PostTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/post")
public class PostController {
    @Resource
    private PostService postService;

    @Resource
    private PostTagService postTagService;

//    Put中没有判断加的tag是否是该club下有的tag
    @PostMapping
    public CommonResult InsertPost(@RequestBody PostAddParam param) {
        PostEntity post = new PostEntity();
        post.setPostTime(param.getPostTime());
        post.setIsTop((byte)0);
        post.setPostTitle(param.getPostTitle());
        post.setClubId(param.getClubId());
        post.setUserId(param.getUserId());
        boolean re = postService.save(post);


        //找到同一时间同一社团下同一个人所发的帖子的postId
        LambdaQueryWrapper<PostEntity> wrapper = new LambdaQueryWrapper<PostEntity>()
                .eq(PostEntity::getUserId, param.getUserId())
                .eq(PostEntity::getPostTime, param.getPostTime())
                .eq(PostEntity::getClubId, param.getClubId());
        List<PostEntity> postList = postService.list(wrapper);
        List<Integer> postIdList = new ArrayList<Integer>();
        for(PostEntity postItem:postList){
            postIdList.add(postItem.getPostId());
        }

        if(postIdList.isEmpty()==true){
            return new CommonResult(400, "添加失败");
        }
        List<String> tagList = param.getPostTag();

        for(Integer postId:postIdList){
            for(String tag:tagList){
                PostTagEntity postTag = new PostTagEntity();
                postTag.setPostId(postId);
                postTag.setTagName(tag);
                postTagService.save(postTag);
            }
        }
        return new CommonResult(200, "添加成功", post.getPostId());
    }

    @DeleteMapping("/{id}")
    public CommonResult DeletePostById(@PathVariable("id") int id) {
        boolean state = postService.removeById(id);
        postTagService.DeleteByPostId(id);
        if (state) {
            return new CommonResult(200, "删除成功", state);
        } else {
            return new CommonResult(444, "无记录");
        }
    }

    @PutMapping
    public CommonResult PutPostById(@RequestBody PostPutParam param){
        try {
            PostEntity post = postService.getById(param.getPostId());
            if(param.getPostTitle()!=null){
                post.setPostTitle(param.getPostTitle());
            }
            //byte无法用是否是空来判断，所以这里必须有这个参数
            post.setIsTop(param.getIsTop());
            boolean re = postService.updateById(post);

            //先把tag全删了
            postTagService.DeleteByPostId(post.getPostId());

            for(PostTagDTO tag:param.getPostTag()){
                postTagService.SavePostTag(post.getPostId(), tag.getTagName());
            }
            return new CommonResult(200, "修改成功", post.getPostId());
        }
        catch (Exception e){
            e.printStackTrace();
            return new CommonResult(400, "修改失败");
        }

    }
}
