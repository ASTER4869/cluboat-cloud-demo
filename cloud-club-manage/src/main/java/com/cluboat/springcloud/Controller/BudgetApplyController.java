package com.cluboat.springcloud.Controller;


import cn.hutool.json.JSONObject;
import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.NewsEntity;
import com.cluboat.springcloud.entity.apply.BudgetApplyEntity;
import com.cluboat.springcloud.entity.param.BudgetApplyParam;
import com.cluboat.springcloud.entity.param.NewsParam;
import com.cluboat.springcloud.service.BudgetApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/budget-application")
public class BudgetApplyController {

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

//    @PostMapping
//    public CommonResult updateById(@RequestBody String json) {
//        JSONObject jsonObject = new JSONObject(json);
//        int id = jsonObject.getInt("id");
//        int state = jsonObject.getInt("state");
//        String feedback =jsonObject.optString("feedback");
//        BudgetApplyEntity budgetApply = budgetApplyService.getById(id);
//        budgetApply.setBudgetApplyIsPass(state);
//        budgetApply.setFeedback(feedback);
//        boolean isSuccess = budgetApplyService.updateById(budgetApply);
//        if (isSuccess)
//            return new CommonResult(200, "修改成功");
//        else
//            return new CommonResult(400, "修改失败");
//
//    }

}
