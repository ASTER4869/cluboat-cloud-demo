package com.cluboat.springcloud.controller;


import com.alibaba.nacos.common.http.param.MediaType;
import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.service.BudgetApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/budgets")
public class BudgetsController {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private BudgetApplyService budgetApplyService;

    //获取某社团提交的预算列表
    @GetMapping("/{id}")
    public CommonResult getBudgetApplyByClubId(@PathVariable("id") int id) {

        CommonResult result = restTemplate.getForObject("http://cloud-club-manage-service/budgets/" + id, CommonResult.class);

        return result;
    }

    //获取所有预算
    @GetMapping
    public CommonResult getAllBudgetApply() {

        CommonResult result = restTemplate.getForObject("http://cloud-club-manage-service/budgets/", CommonResult.class);

        return result;
    }

    //获取某一预算详情
    @GetMapping("/detail/{budgetId}")
    public CommonResult getBudgetsDetail(@PathVariable Integer budgetId){
        CommonResult result = restTemplate.getForObject("http://cloud-club-manage-service/budgets/detail/" + budgetId, CommonResult.class);

        return result;
    }

    @DeleteMapping("/{id}")
    public CommonResult removeById(@PathVariable("id") int id) {
        boolean isSuccess = budgetApplyService.removeById(id);
        if (isSuccess)
            return new CommonResult(200, "删除成功");
        else
            return new CommonResult(400, "删除失败");

    }

    @PutMapping
    public CommonResult updateById(@RequestBody String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.valueOf(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(json,headers);
        ResponseEntity<CommonResult> result = restTemplate.exchange("http://cloud-club-manage-service/budgets/", HttpMethod.PUT, entity, CommonResult.class);
        return result.getBody();

    }

}
