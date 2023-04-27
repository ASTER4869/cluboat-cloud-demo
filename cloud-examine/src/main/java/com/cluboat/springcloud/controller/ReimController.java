package com.cluboat.springcloud.controller;

import com.alibaba.nacos.common.http.param.MediaType;
import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ReimApplyEntity;
import com.cluboat.springcloud.service.ReimApplyService;
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
@RequestMapping("/reimbursements")
public class ReimController {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private ReimApplyService reimApplyService;
    @GetMapping("/{id}")
    public CommonResult getReimApplyById(@PathVariable("id") int id) {
        CommonResult result = restTemplate.getForObject("http://cloud-club-manage-service/reimbursements/" + id, CommonResult.class);

        return result;
    }
    @GetMapping
    public CommonResult getReimApplyById() {
        CommonResult result = restTemplate.getForObject("http://cloud-club-manage-service/reimbursements", CommonResult.class);

        return result;
    }
    @DeleteMapping("/{id}")
    public CommonResult removeById(@PathVariable("id") int id) {
        boolean isSuccess = reimApplyService.removeById(id);
        if (isSuccess) {
            return new CommonResult(200, "删除成功");
        }
        else {
            return new CommonResult(400, "删除失败");
        }

    }

    @PutMapping
    public CommonResult updateById(@RequestBody String json) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.valueOf(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(json,headers);
        ResponseEntity<CommonResult> result = restTemplate.exchange("http://cloud-club-manage-service/reimbursements/", HttpMethod.PUT, entity, CommonResult.class);
        return result.getBody();
    }
}
