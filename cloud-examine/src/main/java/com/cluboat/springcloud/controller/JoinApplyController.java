package com.cluboat.springcloud.controller;

import com.alibaba.nacos.common.http.param.MediaType;
import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.service.BelongService;
import com.cluboat.springcloud.service.JoinApplyService;
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
@RequestMapping("/join-apply")
public class JoinApplyController {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private JoinApplyService joinApplyService;

    @GetMapping
    public CommonResult getAllJoinApply() {
        CommonResult result = restTemplate.getForObject("http://cloud-club-manage-service/join-apply/", CommonResult.class);
        return result;
    }

    @Resource
    private BelongService belongService;
    @PutMapping
    public CommonResult updateById(@RequestBody String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.valueOf(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(json,headers);
        ResponseEntity<CommonResult> result = restTemplate.exchange("http://cloud-club-manage-service/join-apply/", HttpMethod.PUT, entity, CommonResult.class);
        return result.getBody();
    }
    @GetMapping("/{id}")
    public CommonResult getByClubId(@PathVariable("id") int id) {

        CommonResult result = restTemplate.getForObject("http://cloud-club-manage-service/join-apply/" + id, CommonResult.class);
        return result;

    }
}
