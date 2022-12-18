package com.cluboat.springcloud.Controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ActivityEntity;
import com.cluboat.springcloud.entity.FollowEntity;
import com.cluboat.springcloud.entity.param.FollowParam;
import com.cluboat.springcloud.mapper.FollowMapper;
import com.cluboat.springcloud.service.ClubNotificationService;
import com.cluboat.springcloud.service.FollowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@Slf4j
@RequestMapping("/my-activity")
public class FollowController {
    @Resource
    private FollowService followService;
    @Resource
    private  FollowMapper followMapper;

    /* 关注活动 */
    @PostMapping
    public CommonResult createFollow(@RequestBody FollowParam followParam) {
        FollowEntity follow = new FollowEntity();
        follow.setFollow(followParam);
        try {
            followMapper.insert(follow);
            return new CommonResult(200, "修改成功");
        } catch (Exception e) {
            return new CommonResult(400, "修改失败", e);
        }
    }

    /* 删除关注 */
    @DeleteMapping
    public CommonResult removeFollow(@RequestBody FollowParam followParam) {
        boolean isSuccess = followService.lambdaUpdate()
                .eq(FollowEntity::getActivityId, followParam.activityId)
                .eq(FollowEntity::getUserId,followParam.userId)
                .remove();
        if (isSuccess) {
            return new CommonResult(200, "删除成功");
        } else {
            return new CommonResult(400, "删除失败");
        }
    }

}
