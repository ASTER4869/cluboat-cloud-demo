package com.cluboat.springcloud.controller;


import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.service.BudgetApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.cluboat.springcloud.entity.apply.BudgetApplyEntity;
import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/budgetapply")
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
            return new CommonResult(444, "无记录");
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

    @PostMapping("/{id}")
    public CommonResult updateById(@PathVariable("id") int id,@RequestParam byte state) {
        BudgetApplyEntity budgetApply = budgetApplyService.getById(id);
        budgetApply.setBudgetApplyIsPass(state);
        boolean isSuccess = budgetApplyService.updateById(budgetApply);
        if (isSuccess)
            return new CommonResult(200, "修改成功");
        else
            return new CommonResult(400, "修改失败");

    }

}
