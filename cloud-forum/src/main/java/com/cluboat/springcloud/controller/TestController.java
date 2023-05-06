package com.cluboat.springcloud.controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ReportEntity;
import com.cluboat.springcloud.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {
//    @Resource
//    ReportService reportService;
//    @GetMapping
//    public CommonResult Test(){
//        ReportEntity reportEntity = new ReportEntity();
//        reportEntity.setReportReason("111");
//        reportEntity.setReporterId(5);
//        reportEntity.setTargetType("帖子");
//        reportEntity.setReportTime(new Timestamp(System.currentTimeMillis()));
//        reportService.save(reportEntity);
//
//        return new CommonResult(400, "happy", reportEntity.getReportId());
//
//
//    }
}
