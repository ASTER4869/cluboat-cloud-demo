package com.cluboat.springcloud.controller;

import com.cluboat.springcloud.entities.CommonResult;
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
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/post")
public class PostController {
    @Resource
    private PostService postService;

    @Resource
    private PostTagService postTagService;

//    增的Post半完成，改的Put存在细节问题，如tag的改法
    @PostMapping
    public CommonResult InsertPost(@RequestBody PostAddParam param) {
        PostEntity post = new PostEntity();
        post.setPostTime(param.getPostTime());
        post.setIsTop((byte)0);
        post.setPostTitle(param.getPostTitle());
        post.setClubId(param.getClubId());
        post.setUserId(param.getUserId());
        boolean re = postService.save(post);

        List<String> tagList = param.getPostTag();

        for(String tag:tagList){
            PostTagEntity postTag = new PostTagEntity();
            postTag.setPostId(post.getPostId());
            postTag.setTagName(tag);
            postTagService.save(postTag);
        }
        if (re) {
            return new CommonResult(200, "添加成功", post.getPostId());
        } else {
            return new CommonResult(400, "添加失败");
        }
    }

    @DeleteMapping("/{id}")
    public CommonResult DeletePostById(@PathVariable("id") int id) {
        boolean state = postService.removeById(id);
        postTagService.DeleteByPostId(id);
        if (state) {
            return new CommonResult(200, "删除成功", state);
        } else {
            return new CommonResult(400, "删除失败");
        }
    }

    @PutMapping
    public CommonResult PutPostById(@RequestBody PostPutParam param){
        PostEntity post = postService.getById(param.getPostId());
        post.setPostTitle(param.getPostTitle());
        post.setIsTop(param.getIsTop());
        boolean re = postService.save(post);

        for(PostTagDTO tag:param.getPostTag()){
            PostTagEntity postTag = new PostTagEntity();
            postTag.setPostId(post.getPostId());
            postTag.setTagName(tag.getTagName());
            postTagService.save(postTag);
        }

        if (re) {
            return new CommonResult(200, "修改成功", post.getPostId());
        } else {
            return new CommonResult(400, "修改失败");
        }
    }
}
