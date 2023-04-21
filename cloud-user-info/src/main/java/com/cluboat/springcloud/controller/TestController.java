package com.cluboat.springcloud.controller;

import cn.hutool.json.JSONObject;
import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.dto.PersonInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/{userId}")
    public CommonResult getPersonInfo(@PathVariable Integer userId) {
        CommonResult result = restTemplate.getForObject("http://cloud-user-info-service/person-info/" + userId, CommonResult.class);
        return result;
    }


}
