package com.cluboat.springcloud.controller;


import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.service.BudgetApplyService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import com.cluboat.springcloud.entity.apply.BudgetApplyEntity;
import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/budget-apply")
public class BudgetApplyController {

    @Resource
    private BudgetApplyService budgetApplyService;
    @GetMapping("/{id}")
    public CommonResult getBudgetApplyById(@PathVariable("id") int id) {
        BudgetApplyEntity budgetApply = budgetApplyService.getById(id);
        log.info("****插入结果：{payment}");
        if (budgetApply != null) {
            return new CommonResult(200, "查询成功", budgetApply);
        } else {
            return new CommonResult(400, "无记录");
        }
    }
    @GetMapping
    public CommonResult getBudgetApplyById() {
        List<BudgetApplyEntity> budgetApply = budgetApplyService.list();
        log.info("****插入结果：{payment}");
        if (!budgetApply.isEmpty()) {
            return new CommonResult(200, "查询成功", budgetApply);
        } else {
            return new CommonResult(400, "无记录");
        }
    }
    @DeleteMapping("/{id}")
    public CommonResult removeById(@PathVariable("id") int id) {
        boolean isSuccess = budgetApplyService.removeById(id);
        if (isSuccess)
            return new CommonResult(200, "删除成功");
        else
            return new CommonResult(400, "删除失败");

    }

    @PostMapping
    public CommonResult updateById(@RequestBody String json) {
        JSONObject jsonObject = new JSONObject(json);
        int id = jsonObject.getInt("id");
        int state = jsonObject.getInt("state");
        String feedback =jsonObject.optString("feedback");
        BudgetApplyEntity budgetApply = budgetApplyService.getById(id);
        budgetApply.setBudgetApplyIsPass(state);
        budgetApply.setFeedback(feedback);
        boolean isSuccess = budgetApplyService.updateById(budgetApply);
        if (isSuccess)
            return new CommonResult(200, "修改成功");
        else
            return new CommonResult(400, "修改失败");

    }

}
