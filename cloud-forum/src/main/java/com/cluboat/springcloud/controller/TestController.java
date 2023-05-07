package com.cluboat.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
