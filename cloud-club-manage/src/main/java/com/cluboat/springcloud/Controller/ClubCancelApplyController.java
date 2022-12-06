package com.cluboat.springcloud.Controller;


import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ClubCancelApplyEntity;
import com.cluboat.springcloud.entity.param.ClubCancelApplyParam;
import com.cluboat.springcloud.service.ClubCancelApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;

@RestController
@Slf4j
@RequestMapping("/club-cancellation-application")
public class ClubCancelApplyController {

    @Resource
    private ClubCancelApplyService clubCancelApplyService;

    /* 提交管理员  申请 */
    @PostMapping
    public CommonResult createCancelApply(@RequestBody ClubCancelApplyParam clubCancelApplyParam) {
        ClubCancelApplyEntity clubCancelApply = new ClubCancelApplyEntity();
        clubCancelApply.setFeedback(null);  //提交申请没有反馈！
        clubCancelApply.setCancelApplyIsPass((byte) 0);  //初始未通过！

        clubCancelApplyParam.cancelApplyTime =  new Timestamp(System.currentTimeMillis());
        clubCancelApply.setCancelApply(clubCancelApplyParam);
        try {
            clubCancelApplyService.save(clubCancelApply);
            return new CommonResult(200, "上传申请成功");
        } catch (Exception e) {
            return new CommonResult(400, "上传申请失败", e);
        }

    }

}
