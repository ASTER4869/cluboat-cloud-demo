package com.cluboat.springcloud.controller;



import com.alibaba.nacos.common.http.param.MediaType;
import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ActivityApplyEntity;
import com.cluboat.springcloud.entity.ActivityEntity;
import com.cluboat.springcloud.entity.ClubEntity;
import com.cluboat.springcloud.entity.UserInfoEntity;
import com.cluboat.springcloud.entity.param.NotificationParam;
import com.cluboat.springcloud.service.ActivityApplyService;
import com.cluboat.springcloud.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequestMapping("/activity-apply")
public class ActivityApplyController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ActivityApplyService activityApplyService;
    @Resource
    private ActivityService activityService;


    /* 获得本社团提交的活动申请 */
    @GetMapping("/{clubId}")
    public CommonResult getActivityApplyByClubId(@PathVariable("clubId") int clubId) {

        CommonResult result = restTemplate.getForObject("http://cloud-club-manage-service/activity-apply/" + clubId, CommonResult.class);
        return result;
    }

    /* 获得所有活动申请 */
    @GetMapping()
    public CommonResult getAllActivityApply() {

        CommonResult result = restTemplate.getForObject("http://cloud-club-manage-service/activity-apply/", CommonResult.class);
        return result;
    }

    @PutMapping
    public CommonResult updateById(@RequestBody String json) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.valueOf(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(json,headers);
        ResponseEntity<CommonResult> result = restTemplate.exchange("http://cloud-club-manage-service/activity-apply/", HttpMethod.PUT, entity, CommonResult.class);
        return result.getBody();

    }


    @DeleteMapping("/{id}")
    public CommonResult removeById(@PathVariable("id") int id) {
        boolean isSuccess = activityApplyService.removeById(id);
        if (isSuccess) {
            return new CommonResult(200, "删除成功");
        }
        else {
            return new CommonResult(400, "删除失败");
        }

    }

}
