package com.cluboat.springcloud.Controller;


import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.AdminApplyEntity;
import com.cluboat.springcloud.entity.ClubBuildApplyEntity;
import com.cluboat.springcloud.entity.param.AdminApplyParam;
import com.cluboat.springcloud.entity.param.ClubBuildApplyParam;
import com.cluboat.springcloud.service.AdminApplyService;
import com.cluboat.springcloud.service.ClubBuildApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/club-creation-application")
public class ClubBuildApplyController {

    @Resource
    private ClubBuildApplyService clubBuildApplyService;

    /* 提交管理员  申请 */
    @PostMapping
    public CommonResult createBuildApply(@RequestBody ClubBuildApplyParam clubBuildApplyParam) {
        ClubBuildApplyEntity clubBuildApply = new ClubBuildApplyEntity();
        clubBuildApply.setFeedback(null);  //提交申请没有反馈！
        clubBuildApply.setBuildApplyIsPass((byte) 0);  //初始未通过！

        clubBuildApplyParam.buildApplyTime =  new Timestamp(System.currentTimeMillis());
        clubBuildApply.setBuildApply(clubBuildApplyParam);
        try {
            clubBuildApplyService.save(clubBuildApply);
            return new CommonResult(200, "上传申请成功");
        } catch (Exception e) {
            return new CommonResult(400, "上传申请失败", e);
        }

    }

}
