package com.cluboat.springcloud.controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.dto.PostListDTO;
import com.cluboat.springcloud.service.PostService;
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

    @GetMapping("/{id}")
    public CommonResult getPostListByUserId(@PathVariable("id") int id) {
        List<PostListDTO> postList = postService.GetPostListByUserId(id);
        if (postList != null) {
            return new CommonResult(200, "查询成功", postList);
        } else {
            return new CommonResult(400, "查询失败");
        }
    }
}
