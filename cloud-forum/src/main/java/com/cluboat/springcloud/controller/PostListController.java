package com.cluboat.springcloud.controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.dto.PostListDTO;
import com.cluboat.springcloud.service.PostService;
import com.cluboat.springcloud.service.PostTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/post-list")
public class PostListController {
    @Resource
    private PostService postService;


    @Resource
    private PostTagService postTagService;

    @GetMapping()
    public CommonResult getAllPostList() {
        List<PostListDTO> postList = postService.GetAllPostList();


        if(!postList.isEmpty()){
            //获取每个tag对应的tag名，并赋值
            for (PostListDTO post:postList) {
                post.setPostTag(postTagService.GetPostTagListByPostId(post.getPostId()));
            }
            return new CommonResult(200,"查询成功", postList);
        }
        else{
            return new CommonResult(444,"无记录");
        }
    }

    @GetMapping("/{id}")
    public CommonResult getPostListByUserId(@PathVariable("id") int id) {
        List<PostListDTO> postList = postService.GetPostListByUserId(id);
        if(!postList.isEmpty()){
            //获取每个tag对应的tag名，并赋值
            for (PostListDTO post:postList) {
                post.setPostTag(postTagService.GetPostTagListByPostId(post.getPostId()));
            }
            return new CommonResult(200,"查询成功", postList);
        }
        else{
            return new CommonResult(444,"无记录");
        }
    }
}
