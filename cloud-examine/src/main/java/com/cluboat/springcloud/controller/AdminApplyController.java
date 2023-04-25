package com.cluboat.springcloud.controller;

import com.alibaba.nacos.common.http.param.MediaType;
import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.AdminApplyEntity;
import com.cluboat.springcloud.entity.Belong;
import com.cluboat.springcloud.service.AdminApplyService;
import com.cluboat.springcloud.service.BelongService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/admin-apply")
public class AdminApplyController {

    @Resource
    private AdminApplyService adminApplyService;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public CommonResult getPaymentById(@PathVariable("id") int id){

        CommonResult result = restTemplate.getForObject("http://cloud-club-manage-service/admin-apply/" + id, CommonResult.class);
        return result;
    }
    @Resource
    private BelongService belongService;
    @PutMapping
    public CommonResult updateById(@RequestBody String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.valueOf(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(json,headers);
        ResponseEntity<CommonResult> result = restTemplate.exchange("http://cloud-club-manage-service/admin-apply/", HttpMethod.PUT, entity, CommonResult.class);
        return result.getBody();

    }
}
