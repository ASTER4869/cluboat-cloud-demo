package com.cluboat.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cluboat.springcloud.entity.FollowEntity;
import com.cluboat.springcloud.entity.dto.MyActivityDTO;
import com.cluboat.springcloud.entity.param.DeleteMyActivityParam;
import com.cluboat.springcloud.service.ActivityService;
import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.service.FollowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/my-activity")
public class MyActivityController {


    @Resource
    private ActivityService activityService;
    @Resource
    private FollowService followService;


    //查看关注的活动列表
    @GetMapping("/{userId}")
    public CommonResult getMyActivity(@PathVariable("userId") Integer userId) {
        try {
            List<MyActivityDTO> myActivityDTO = activityService.GetMyActivity(userId);
            if(myActivityDTO.isEmpty()==false){
                return new CommonResult(200,"查询成功", myActivityDTO);
            }
            else{
                return new CommonResult(444,"无记录");
            }

        }catch (Exception e){
            return new CommonResult(400,"系统出错", e);
        }

    }
//    @GetMapping("/{userId}")
//    public CommonResult findAll(@PathVariable String userId) {
//        return new CommonResult(200,"查询成功",activityService.list());
//
//    }

    //取消关注活动
    @DeleteMapping
    public CommonResult deleteMyActivity(@RequestBody DeleteMyActivityParam param) {
        if(param.getUserId() == null){
            return new CommonResult(401, "用户编号为空");
        }
        if(param.getActivityId() == null){
            return new CommonResult(402, "活动编号为空");
        }
        LambdaQueryWrapper<FollowEntity> wrapper = new LambdaQueryWrapper<FollowEntity>()
            .eq(FollowEntity::getUserId, param.getUserId())
            .eq(FollowEntity::getActivityId, param.getActivityId());
        boolean result = followService.remove(wrapper);
        if(result){
            return new CommonResult(200,"删除成功");
        }
        else{
            return new CommonResult(444,"无记录");
        }
    }
}
