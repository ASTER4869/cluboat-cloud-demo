package com.cluboat.springcloud.Controller;


import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.BudgetApplyEntity;
import com.cluboat.springcloud.entity.param.BudgetApplyParam;
import com.cluboat.springcloud.service.BudgetApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;

@RestController
@Slf4j
@RequestMapping("/budget-application")
public class ClubBudgetApplyController {

    @Resource
    private BudgetApplyService budgetApplyService;

    /* 增某一预算表 */
    @PostMapping
    public CommonResult createBudgetApply(@RequestBody BudgetApplyParam budgetApplyParam) {
        BudgetApplyEntity  budgetApplyEntity = new BudgetApplyEntity();
        budgetApplyParam.budgetApplyTime = new Timestamp(System.currentTimeMillis());
        budgetApplyEntity.setBudgetApply(budgetApplyParam);
        try {
            budgetApplyService.save(budgetApplyEntity);
            return new CommonResult(200, "申请成功");
        } catch (Exception e) {
            return new CommonResult(400, "申请失败",e);
        }
    }

}
