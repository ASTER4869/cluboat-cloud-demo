package com.cluboat.springcloud.Controller;


import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ReimApplyEntity;
import com.cluboat.springcloud.entity.apply.BudgetApplyEntity;
import com.cluboat.springcloud.entity.param.BudgetApplyParam;
import com.cluboat.springcloud.entity.param.ReimApplyParam;
import com.cluboat.springcloud.service.BudgetApplyService;
import com.cluboat.springcloud.service.ReimApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;

@RestController
@Slf4j
@RequestMapping("/reim_application")
public class ReimApplyController {

    @Resource
    private ReimApplyService reimApplyService;

    /* 增某一报销表 */
    @PostMapping
    public CommonResult createReimApply(@RequestBody ReimApplyParam reimApplyParam) {
        ReimApplyEntity reimApplyEntity = new ReimApplyEntity();
        reimApplyParam.reimApplyTime = new Timestamp(System.currentTimeMillis());
        reimApplyEntity.setReimApply(reimApplyParam);
        try {
            reimApplyService.save(reimApplyEntity);
            return new CommonResult(200, "申请成功");
        } catch (Exception e) {
            return new CommonResult(400, "申请失败",e);
        }
    }


}
