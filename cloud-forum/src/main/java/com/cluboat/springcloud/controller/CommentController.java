package com.cluboat.springcloud.controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.CommentEntity;
import com.cluboat.springcloud.entity.param.CommentAddParam;
import com.cluboat.springcloud.entity.param.CommentDeleteParam;
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

    @PostMapping
    public CommonResult InsertComment(@RequestBody CommentAddParam param){
        CommentEntity comment = new CommentEntity();
        comment.setPostId(param.getPostId());
        comment.setUserId(param.getUserId());
        comment.setCommentContent(param.getCommentText());
        comment.setCommentTime(param.getCommentTime());
        boolean re = commentService.save(comment);
        if(re){
            return new CommonResult(200, "添加成功", comment.getCommentId());
        } else {
            return new CommonResult(400, "添加失败");
        }
    }

    @DeleteMapping
    public CommonResult DeleteComment(@RequestBody CommentDeleteParam param){
        int commentNum = commentService.DeleteComment(param);
        if(commentNum != 0){
            return new CommonResult(200, "删除成功", commentNum);
        } else {
            return new CommonResult(400, "删除失败");
        }
    }
}
