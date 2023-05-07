package com.cluboat.springcloud.Controller;


import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.entity.param.ClubCancelApplyParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/club-cancel-apply")
public class CancelApplyController {

    @Resource
    private RestTemplate restTemplate;

    /* 提交社团注销  申请 */
    @PostMapping
    public CommonResult createCancelApply(@RequestBody ClubCancelApplyParam clubCancelApplyParam) {
        CommonResult result = restTemplate.postForObject("http://cloud-examine-service/club-cancel-apply", clubCancelApplyParam, CommonResult.class);

        return result;
    }

}
