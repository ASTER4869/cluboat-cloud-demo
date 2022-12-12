package com.cluboat.springcloud.Controller;


import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ActivityApplyEntity;
import com.cluboat.springcloud.entity.JoinApplyEntity;
import com.cluboat.springcloud.entity.param.ActivityApplyParam;
import com.cluboat.springcloud.entity.param.JoinApplyParam;
import com.cluboat.springcloud.service.ActivityApplyService;
import com.cluboat.springcloud.service.JoinApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/club-application")
public class ClubJoinApplyController {

    @Resource
    private JoinApplyService joinApplyService;

    /* 提交入社申请 */
    @PostMapping
    public CommonResult createJoinApply(@RequestBody JoinApplyParam joinApplyParam) {
        JoinApplyEntity joinApply = new JoinApplyEntity();
        joinApply.setFeedback(null);  //提交申请没有反馈！
        joinApply.setJoinApplyIsPass((byte) 0);  //初始未通过！

        joinApplyParam.joinApplyTime = new Timestamp(System.currentTimeMillis());
        joinApply.setJoinApply(joinApplyParam);
        try {
            joinApplyService.save(joinApply);
            return new CommonResult(200, "上传申请成功");
        } catch (Exception e) {
            return new CommonResult(400, "上传申请失败", e);
        }

    }

    /* 获得我提交的入社申请 */
    @GetMapping("/{userId}")
    public CommonResult getJoinApplyById(@PathVariable("userId") int id) {
        List<JoinApplyEntity> joinApplyList = joinApplyService.lambdaQuery()
                .eq(JoinApplyEntity::getUserId,id).list();
        log.info("****插入结果：{payment}");
        if (joinApplyList.size() > 0) {
            return new CommonResult(200, "查询成功", joinApplyList);
        } else {
            return new CommonResult(400, "无记录");
        }
    }

}
