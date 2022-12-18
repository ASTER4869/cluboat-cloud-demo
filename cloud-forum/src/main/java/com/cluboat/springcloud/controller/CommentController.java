package com.cluboat.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.CollectionEntity;
import com.cluboat.springcloud.entity.CommentEntity;
import com.cluboat.springcloud.entity.param.CommentAddParam;
import com.cluboat.springcloud.entity.param.CommentDeleteParam;
import com.cluboat.springcloud.mapper.CommentMapper;
import com.cluboat.springcloud.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private CommentMapper commentMapper;

    @PostMapping
    public CommonResult InsertComment(@RequestBody CommentAddParam param){
        //统计该post有几个评论
        LambdaQueryWrapper<CommentEntity> wrapper = new LambdaQueryWrapper<CommentEntity>()
                .eq(CommentEntity::getPostId, param.getPostId());
        Long commentNum = commentService.count(wrapper);

        CommentEntity comment = new CommentEntity();
        comment.setCommentId((int) (commentNum + 1));
        comment.setPostId(param.getPostId());
        comment.setUserId(param.getUserId());
        comment.setCommentContent(param.getCommentText());
        comment.setCommentTime(param.getCommentTime());

        try {
            int re = commentMapper.insert(comment);
            if(re==1){
                return new CommonResult(200, "添加成功", comment.getCommentId());
            } else {
                return new CommonResult(400, "添加失败");
            }
        }catch (Exception e){
            return new CommonResult(400, "添加失败");
        }
    }

    @DeleteMapping
    public CommonResult DeleteComment(@RequestBody CommentDeleteParam param){
        int commentNum = commentService.DeleteComment(param);
        if(commentNum != 0){
            return new CommonResult(200, "删除成功", commentNum);
        } else {
            return new CommonResult(444, "无记录");
        }
    }
}
