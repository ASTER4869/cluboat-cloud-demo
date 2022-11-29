package com.cluboat.springcloud.controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.apply.AdminApplyEntity;
import com.cluboat.springcloud.service.AdminApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Slf4j
@RequestMapping("/AdminApply")
public class AdminApplyController {

    @Resource
    private AdminApplyService adminApplyService;


    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") int id){
        AdminApplyEntity adminApply = adminApplyService.getById(id);
        log.info("****插入结果：{payment}");
        if(adminApply!=null){
            return new CommonResult(200,"查询成功",adminApply);
        }else {
            return new CommonResult(444,"无记录");
        }
    }
}
