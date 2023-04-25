package com.cluboat.springcloud.controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ClubEntity;
import com.cluboat.springcloud.entity.UserEntity;
import com.cluboat.springcloud.entity.UserInfoEntity;
import com.cluboat.springcloud.entity.apply.ClubCancelApplyEntity;
import com.cluboat.springcloud.entity.dto.ClubCancelApplyDTO;
import com.cluboat.springcloud.entity.param.ClubCancelApplyParam;
import com.cluboat.springcloud.entity.param.NotificationParam;
import com.cluboat.springcloud.service.ClubCancelApplyService;
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
@RequestMapping("/club-cancel-apply")
public class ClubCancelApplyController {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private ClubCancelApplyService clubCancelApplyService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private ClubService clubService;
    @GetMapping("/{id}")
    public CommonResult getClubCancelApplyById(@PathVariable("id") int id) {
        try {
            ClubCancelApplyEntity clubCancelApply = clubCancelApplyService.getById(id);
            if (clubCancelApply != null) {
                ClubCancelApplyDTO clubCancelApplyDTO = new ClubCancelApplyDTO();
                clubCancelApplyDTO.setCancelApplyId(clubCancelApply.getCancelApplyId());
                clubCancelApplyDTO.setClubId(clubCancelApply.getClubId());
                clubCancelApplyDTO.setUserId(clubCancelApply.getUserId());
                clubCancelApplyDTO.setCancelApplyReason(clubCancelApply.getCancelApplyReason());
                clubCancelApplyDTO.setCancelApplyTime(clubCancelApply.getCancelApplyTime());
                clubCancelApplyDTO.setStatus(clubCancelApply.getStatus());
                clubCancelApplyDTO.setFeedback(clubCancelApply.getFeedback());
                //现在开始找申请人名称和社团名称
                UserInfoEntity userInfoEntity = userInfoService.getById(clubCancelApply.getUserId());
                String userName = userInfoEntity.getUserName();
                ClubEntity clubEntity = clubService.getById(clubCancelApply.getClubId());
                String clubName = clubEntity.getClubName();
                clubCancelApplyDTO.setClubName(clubName);
                clubCancelApplyDTO.setUserName(userName);
                return new CommonResult(200, "查询成功", clubCancelApplyDTO);
            } else {
                return new CommonResult(400, "无记录");
            }
        }catch (Exception e){
            return new CommonResult(400, "出现错误", e);
        }
    }
    @GetMapping
    public CommonResult getAllClubCancelApply() {
        try {
            List<ClubCancelApplyEntity> clubCancelApply = clubCancelApplyService.list();
            if (!clubCancelApply.isEmpty()) {
                List<ClubCancelApplyDTO> clubCancelApplyDTOList = new ArrayList<>();
                for (ClubCancelApplyEntity clubCancelApplyEntity: clubCancelApply){
                    ClubCancelApplyDTO clubCancelApplyDTO = new ClubCancelApplyDTO();
                    clubCancelApplyDTO.setCancelApplyId(clubCancelApplyEntity.getCancelApplyId());
                    clubCancelApplyDTO.setClubId(clubCancelApplyEntity.getClubId());
                    clubCancelApplyDTO.setUserId(clubCancelApplyEntity.getUserId());
                    clubCancelApplyDTO.setCancelApplyReason(clubCancelApplyEntity.getCancelApplyReason());
                    clubCancelApplyDTO.setCancelApplyTime(clubCancelApplyEntity.getCancelApplyTime());
                    clubCancelApplyDTO.setStatus(clubCancelApplyEntity.getStatus());
                    clubCancelApplyDTO.setFeedback(clubCancelApplyEntity.getFeedback());
                    //现在开始找申请人名称和社团名称
                    UserInfoEntity userInfoEntity = userInfoService.getById(clubCancelApplyEntity.getUserId());
                    String userName = userInfoEntity.getUserName();
                    ClubEntity clubEntity = clubService.getById(clubCancelApplyEntity.getClubId());
                    String clubName = clubEntity.getClubName();
                    clubCancelApplyDTO.setClubName(clubName);
                    clubCancelApplyDTO.setUserName(userName);
                    clubCancelApplyDTOList.add(clubCancelApplyDTO);
                }
                return new CommonResult(200, "查询成功", clubCancelApplyDTOList);
            } else {
                return new CommonResult(400, "无记录");
            }
        }catch (Exception e){
            return new CommonResult(400, "出现错误", e);
        }

    }
    @DeleteMapping("/{id}")
    public CommonResult removeById(@PathVariable("id") int id) {
        boolean isSuccess = clubCancelApplyService.removeById(id);
        if (isSuccess)
            return new CommonResult(200, "删除成功");
        else
            return new CommonResult(400, "删除失败");

    }

    @PutMapping()
    public CommonResult updateById(@RequestBody String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            int id = jsonObject.getInt("cancelApplyId");
            String  status = jsonObject.optString("status");
            String feedback =jsonObject.optString("feedback");
            ClubCancelApplyEntity clubCancelApply = clubCancelApplyService.getById(id);
            clubCancelApply.setStatus(status);
            clubCancelApply.setFeedback(feedback);
            boolean isSuccess = clubCancelApplyService.updateById(clubCancelApply);
            if (isSuccess){
                Integer userId = clubCancelApply.getUserId();
                ClubEntity clubEntity = clubService.getById(clubCancelApply.getClubId());
                String clubName = clubEntity.getClubName();
                if(status.equals("已通过"))
                {
                    try {
                        //将该社团删去
                        clubService.removeById(clubCancelApply.getClubId());
                        //系统向用户发通知
                        NotificationParam notificationParam = new NotificationParam();
                        notificationParam.setSenderType((byte)(2));
                        notificationParam.setReceiverType((byte)(2));
                        notificationParam.setReceiverId(userId);
                        notificationParam.setNotificationTitle("注销社团成功");
                        notificationParam.setNotificationContent("您申请的社团：" + clubName + "已成功注销");
                        CommonResult result = restTemplate.postForObject("http://cloud-examine-service/notification/", notificationParam, CommonResult.class);
                        result.setMessage("注销社团成功通知已发送成功");
                        return result;
                    } catch (Exception e){
                        return new CommonResult(400, "注销社团成功通知发送失败",e);
                    }
                }
                else if(status.equals("已拒绝")){
                    try {
                        //系统向用户发通知
                        NotificationParam notificationParam = new NotificationParam();
                        notificationParam.setSenderType((byte)(2));
                        notificationParam.setReceiverType((byte)(2));
                        notificationParam.setReceiverId(userId);
                        notificationParam.setNotificationTitle("注销社团失败");
                        notificationParam.setNotificationContent("您申请的社团注销：" + clubName + "审核不通过");
                        CommonResult result = restTemplate.postForObject("http://cloud-examine-service/notification/", notificationParam, CommonResult.class);
                        result.setMessage("注销社团失败通知已发送成功");
                        return result;
                    } catch (Exception e){
                        return new CommonResult(400, "注销社团失败通知发送失败",e);
                    }
                }
            }
            else
                return new CommonResult(400, "修改失败");
        }catch (Exception e){
            return new CommonResult(400, "发生错误", e);
        }
        return new CommonResult(400, "出现异常错误");
    }

    /* 提交社团撤销  申请 */
    @PostMapping
    public CommonResult createCancelApply(@RequestBody ClubCancelApplyParam clubCancelApplyParam) {
        com.cluboat.springcloud.entity.apply.ClubCancelApplyEntity clubCancelApply = new com.cluboat.springcloud.entity.apply.ClubCancelApplyEntity();
        clubCancelApply.setFeedback(null);  //提交申请没有反馈！
        clubCancelApply.setStatus("待审批");  //初始未通过！

        clubCancelApply.setUserId(clubCancelApplyParam.userId);
        clubCancelApply.setClubId(clubCancelApplyParam.clubId);
        clubCancelApply.setCancelApplyReason(clubCancelApplyParam.cancelApplyReason);
        clubCancelApply.setCancelApplyTime(new Timestamp(System.currentTimeMillis()));
        try {
            clubCancelApplyService.save(clubCancelApply);
            return new CommonResult(200, "上传申请成功");
        } catch (Exception e) {
            return new CommonResult(400, "上传申请失败", e);
        }

    }

}
