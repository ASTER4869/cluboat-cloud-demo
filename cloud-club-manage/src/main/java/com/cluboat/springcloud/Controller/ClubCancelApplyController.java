package com.cluboat.springcloud.Controller;


import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ClubCancelApplyEntity;
import com.cluboat.springcloud.entity.param.ClubCancelApplyParam;
import com.cluboat.springcloud.service.ClubCancelApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.Timestamp;

@RestController
@Slf4j
@RequestMapping("/club-cancel-apply")
public class ClubCancelApplyController {

    @Resource
    private RestTemplate restTemplate;

    /* 提交社团注销  申请 */
    @PostMapping
    public CommonResult createCancelApply(@RequestBody ClubCancelApplyParam clubCancelApplyParam) {
        CommonResult result = restTemplate.postForObject("http://cloud-examine-service/club-cancel-apply", clubCancelApplyParam, CommonResult.class);

        return result;
    }

}
