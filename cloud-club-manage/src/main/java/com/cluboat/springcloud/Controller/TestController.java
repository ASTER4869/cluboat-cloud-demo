package com.cluboat.springcloud.Controller;

import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.service.NotReceiverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {
    @Resource
    NotReceiverService notReceiverService;
    @GetMapping
    public CommonResult test(){
        return new CommonResult(5555, "lll", notReceiverService.testMPJ());
    }
}
