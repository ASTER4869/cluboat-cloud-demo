package com.cluboat.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.entity.CollectionEntity;
import com.cluboat.springcloud.entity.PostHistoryEntity;
import com.cluboat.springcloud.entity.param.AddPostHistoryParam;
import com.cluboat.springcloud.service.PostHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;

@RestController
@Slf4j
@RequestMapping("/post-history")
public class PostHistoryController {
    @Resource
    PostHistoryService postHistoryService;

    @PostMapping()
    public CommonResult RecordPostHistory(@RequestBody AddPostHistoryParam postHistoryParam){
        try {
            LambdaQueryWrapper<PostHistoryEntity> wrapper = new LambdaQueryWrapper<PostHistoryEntity>()
                    .eq(PostHistoryEntity::getPostId, postHistoryParam.getPostId())
                    .eq(PostHistoryEntity::getUserId, postHistoryParam.getUserId());
            PostHistoryEntity postHistoryEntity = postHistoryService.getOne(wrapper, false);
            // 如果目前还没有记录
            if (postHistoryEntity == null){
                postHistoryEntity = new PostHistoryEntity();
                postHistoryEntity.setNum(1);
            }
            else {
                postHistoryEntity.setNum(postHistoryEntity.getNum() + 1);
            }
            postHistoryEntity.setWatchTime(new Timestamp(System.currentTimeMillis()));
            postHistoryEntity.setPostId(postHistoryParam.getPostId());
            postHistoryEntity.setUserId(postHistoryParam.getUserId());
            if(postHistoryService.saveOrUpdate(postHistoryEntity) == true){
                return new CommonResult(200, "记录成功");
            }
            else {
                return new CommonResult(401, "记录失败");
            }
        }
        catch (Exception e){
            return new CommonResult(400, "系统出现错误", e);
        }
    }
}
