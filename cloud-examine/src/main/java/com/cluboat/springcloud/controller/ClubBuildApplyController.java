package com.cluboat.springcloud.controller;

import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.entity.ClubEntity;
import com.cluboat.springcloud.entity.UserInfoEntity;
import com.cluboat.springcloud.entity.apply.ClubBuildApplyEntity;
import com.cluboat.springcloud.entity.dto.ClubBuildApplyDTO;
import com.cluboat.springcloud.entity.param.NotificationParam;
import com.cluboat.springcloud.service.ClubBuildApplyService;
import com.cluboat.springcloud.service.ClubService;
import com.cluboat.springcloud.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/club-build-apply")
public class ClubBuildApplyController {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private ClubBuildApplyService clubBuildApplyService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private ClubService clubService;
    @GetMapping("/{id}")
    public CommonResult getClubBuildApplyById(@PathVariable("id") int id) {
        ClubBuildApplyEntity clubBuildApply = clubBuildApplyService.getById(id);
        if (clubBuildApply != null) {
            //查找申请人name
            UserInfoEntity userInfoEntity = userInfoService.getById(clubBuildApply.getUserId());
            String userName = userInfoEntity.getUserName();
            ClubBuildApplyDTO clubBuildApplyDTO = new ClubBuildApplyDTO();
            clubBuildApplyDTO.setBuildApplyId(clubBuildApply.getBuildApplyId());
            clubBuildApplyDTO.setUserId(clubBuildApply.getUserId());
            clubBuildApplyDTO.setBuildApplyReason(clubBuildApply.getBuildApplyReason());
            clubBuildApplyDTO.setBuildApplyTime(clubBuildApply.getBuildApplyTime());
            clubBuildApplyDTO.setStatus(clubBuildApply.getStatus());
            clubBuildApplyDTO.setAdminClubName(clubBuildApply.getAdminClubName());
            clubBuildApplyDTO.setFeedback(clubBuildApply.getFeedback());
            clubBuildApplyDTO.setUserName(userName);
            return new CommonResult(200, "查询成功", clubBuildApplyDTO);
        } else {
            return new CommonResult(400, "无记录");
        }
    }
    @GetMapping
    public CommonResult getClubBuildApplyById() {
        List<ClubBuildApplyEntity> clubBuildApply = clubBuildApplyService.list();
        log.info("****插入结果：{payment}");
        if (!clubBuildApply.isEmpty()) {
            List<ClubBuildApplyDTO> clubBuildApplyDTOList = new ArrayList<>();
            for (ClubBuildApplyEntity clubBuildApplyEntity: clubBuildApply){
                //查找申请人name
                UserInfoEntity userInfoEntity = userInfoService.getById(clubBuildApplyEntity.getUserId());
                String userName = userInfoEntity.getUserName();
                ClubBuildApplyDTO clubBuildApplyDTO = new ClubBuildApplyDTO();
                clubBuildApplyDTO.setBuildApplyId(clubBuildApplyEntity.getBuildApplyId());
                clubBuildApplyDTO.setUserId(clubBuildApplyEntity.getUserId());
                clubBuildApplyDTO.setBuildApplyReason(clubBuildApplyEntity.getBuildApplyReason());
                clubBuildApplyDTO.setBuildApplyTime(clubBuildApplyEntity.getBuildApplyTime());
                clubBuildApplyDTO.setStatus(clubBuildApplyEntity.getStatus());
                clubBuildApplyDTO.setAdminClubName(clubBuildApplyEntity.getAdminClubName());
                clubBuildApplyDTO.setFeedback(clubBuildApplyEntity.getFeedback());
                clubBuildApplyDTO.setUserName(userName);
                clubBuildApplyDTOList.add(clubBuildApplyDTO);
            }

            return new CommonResult(200, "查询成功", clubBuildApplyDTOList);
        } else {
            return new CommonResult(400, "无记录");
        }
    }

    @DeleteMapping("/{id}")
    public CommonResult removeById(@PathVariable("id") int id) {
        boolean isSuccess = clubBuildApplyService.removeById(id);
        if (isSuccess)
            return new CommonResult(200, "删除成功");
        else
            return new CommonResult(400, "删除失败");

    }

    @PutMapping
    public CommonResult updateById(@RequestBody String json) {
        JSONObject jsonObject = new JSONObject(json);
        int id = jsonObject.getInt("buildApplyId");
        String status = jsonObject.optString("status");
        String feedback =jsonObject.optString("feedback");
        ClubBuildApplyEntity clubBuildApply = clubBuildApplyService.getById(id);
        clubBuildApply.setStatus(status);
        clubBuildApply.setFeedback(feedback);
        boolean isSuccess = clubBuildApplyService.updateById(clubBuildApply);
        if(status.equals("已通过"))
        {
            ClubEntity club = new ClubEntity();
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            club.setClubCreateTime(new Timestamp(System.currentTimeMillis()));
            club.setClubName(clubBuildApply.getAdminClubName());
            try {
                clubService.save(club);
                //系统向用户发通知
                NotificationParam notificationParam = new NotificationParam();
                notificationParam.setSenderType((byte)(2));
                notificationParam.setReceiverType((byte)(2));
                notificationParam.setReceiverId(clubBuildApply.getUserId());
                notificationParam.setNotificationTitle("创建社团成功");
                notificationParam.setNotificationContent("您创建的社团：" + clubBuildApply.getAdminClubName() + "已成功创建");
                CommonResult result = restTemplate.postForObject("http://cloud-examine-service/notification/", notificationParam, CommonResult.class);
//                return new CommonResult(200, "创建成功",club);
                result.setMessage("建社成功通知已发送成功");
                return result;
            } catch (Exception e){
                return new CommonResult(400, "建社成功通知发送失败",e);
            }
        }
        else if(status.equals("已拒绝")){
            try {
                //系统向用户发通知
                NotificationParam notificationParam = new NotificationParam();
                notificationParam.setSenderType((byte)(2));
                notificationParam.setReceiverType((byte)(2));
                notificationParam.setReceiverId(clubBuildApply.getUserId());
                notificationParam.setNotificationTitle("创建社团失败");
                notificationParam.setNotificationContent("您创建的社团：" + clubBuildApply.getAdminClubName() + "审核不通过");
                CommonResult result = restTemplate.postForObject("http://cloud-examine-service/notification/", notificationParam, CommonResult.class);
//                return new CommonResult(200, "创建成功",club);
                result.setMessage("建社失败通知已发送成功");
                return result;
            } catch (Exception e){
                return new CommonResult(400, "建社失败通知发送失败",e);
            }
        }
        if (isSuccess)
            return new CommonResult(200, "修改成功");
        else
            return new CommonResult(400, "修改失败");

    }
}
