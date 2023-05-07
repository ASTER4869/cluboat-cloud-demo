package com.cluboat.springcloud.Controller;


import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.entity.ClubBuildApplyEntity;
import com.cluboat.springcloud.entity.param.ClubBuildApplyParam;
import com.cluboat.springcloud.service.ClubBuildApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/club-build-apply")
public class BuildApplyController {

    @Resource
    private ClubBuildApplyService clubBuildApplyService;

    /* 提交管理员  申请 */
    @PostMapping
    public CommonResult createBuildApply(@RequestBody ClubBuildApplyParam clubBuildApplyParam) {
        ClubBuildApplyEntity clubBuildApply = new ClubBuildApplyEntity();
        clubBuildApply.setFeedback(null);  //提交申请没有反馈！
        clubBuildApply.setStatus("待审批");  //初始未通过！
        clubBuildApply.setBuildApply(clubBuildApplyParam);
        try {
            clubBuildApplyService.save(clubBuildApply);
            return new CommonResult(200, "上传申请成功");
        } catch (Exception e) {
            return new CommonResult(400, "上传申请失败", e);
        }

    }

}
