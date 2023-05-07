package com.cluboat.springcloud.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.entity.LikesEntity;
import com.cluboat.springcloud.entity.param.LikesAddParam;
import com.cluboat.springcloud.entity.param.LikesDeleteParam;
import com.cluboat.springcloud.mapper.LikesMapper;
import com.cluboat.springcloud.service.LikesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/likes")
public class LikesController {
    @Resource
    LikesService likesService;

    @Resource
    LikesMapper likesMapper;

    @PostMapping
    public CommonResult AddLikes(@RequestBody LikesAddParam param){

        try {
            LikesEntity likes = new LikesEntity();
            likes.setPostId(param.getPostId());
            likes.setUserId(param.getUserId());
            int re = likesMapper.insert(likes);
            if(re==1){
                return new CommonResult(200, "添加成功");
            } else {
                return new CommonResult(400, "添加失败");
            }
        }catch (Exception e){
            return new CommonResult(400, "添加失败");
        }
    }

    @DeleteMapping
    public CommonResult DeleteLikes(@RequestBody LikesDeleteParam param){
        try {
            LikesEntity likes = new LikesEntity();
            likes.setPostId(param.getPostId());
            likes.setUserId(param.getUserId());
            LambdaQueryWrapper<LikesEntity> wrapper = new LambdaQueryWrapper<LikesEntity>()
                    .eq(LikesEntity::getPostId, param.getPostId())
                    .eq(LikesEntity::getUserId, param.getUserId());
            int re = likesMapper.delete(wrapper);
            if(re==1){
                return new CommonResult(200, "添加成功");
            } else {
                return new CommonResult(400, "添加失败");
            }
        }catch (Exception e){
            return new CommonResult(400, "添加失败");
        }
    }

}
