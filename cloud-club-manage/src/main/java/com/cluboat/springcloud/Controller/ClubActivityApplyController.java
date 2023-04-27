package com.cluboat.springcloud.Controller;


import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ActivityApplyEntity;
import com.cluboat.springcloud.entity.ActivityEntity;
import com.cluboat.springcloud.entity.ClubEntity;
import com.cluboat.springcloud.entity.DTO.GetActivityApplyDTO;
import com.cluboat.springcloud.entity.UserInfoEntity;
import com.cluboat.springcloud.entity.param.ActivityApplyParam;
import com.cluboat.springcloud.entity.param.GetActivityApplyParam;
import com.cluboat.springcloud.entity.param.NotificationParam;
import com.cluboat.springcloud.service.ActivityApplyService;
import com.cluboat.springcloud.service.ClubActivityService;
import com.cluboat.springcloud.service.ClubService;
import com.cluboat.springcloud.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/activity-apply")
public class ClubActivityApplyController {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private ActivityApplyService activityApplyService;

    @Resource
    private ClubService clubService;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private ClubActivityService activityService;

    /* 提交活动申请 */
    @PostMapping
    public CommonResult createActivityApply(@RequestBody ActivityApplyParam activityApplyParam) {
        ActivityApplyEntity activityApply = new ActivityApplyEntity();
        activityApply.setFeedback(null);  //提交申请没有反馈！
        activityApply.setStatus("待审批");  //初始未通过！
        activityApply.setActivityApplyTime(new Timestamp(System.currentTimeMillis()));

        activityApply.setUserId(activityApplyParam.userId);
        activityApply.setClubId(activityApplyParam.clubId);
        activityApply.setActivityApplyReason(activityApplyParam.activityApplyReason);
        activityApply.setActivityTitle(activityApplyParam.activityTitle);
        activityApply.setActivityDate(activityApplyParam.activityDate);
        activityApply.setActivityTime(activityApplyParam.activityTime);
        activityApply.setActivityCampus(activityApplyParam.activityCampus);
        activityApply.setActivityLocation(activityApplyParam.activityLocation);
        activityApply.setActivityPeopleNum(activityApplyParam.activityPeopleNum);
        activityApply.setActivityDescription(activityApplyParam.activityDescription);

        try {
            activityApplyService.save(activityApply);
            return new CommonResult(200, "提交成功");
        } catch (Exception e) {
            return new CommonResult(400, "提交失败", e);
        }

    }

    /* 获得本社团提交的活动申请 */
    @GetMapping("/{clubId}")
    public CommonResult getActivityApplyByClubId(@PathVariable("clubId") int clubId) {
        List<ActivityApplyEntity> activityApplyList = activityApplyService.lambdaQuery()
                        .eq(ActivityApplyEntity::getClubId,clubId).list();
        if (activityApplyList.size() > 0) {
            //获取社团名称
            ClubEntity clubEntity = clubService.getById(clubId);
            String clubName = clubEntity.getClubName();
            //将数据映射到DTO中
            List<GetActivityApplyDTO> activityApplyDTOList = new ArrayList<>();
            for (ActivityApplyEntity activityApply: activityApplyList){
                GetActivityApplyDTO activityApplyDTO = new GetActivityApplyDTO();
                activityApplyDTO.setActivityApplyId(activityApply.getActivityApplyId());
                activityApplyDTO.setUserId(activityApply.getUserId());
                activityApplyDTO.setClubId(activityApply.getClubId());
                activityApplyDTO.setActivityApplyReason(activityApply.getActivityApplyReason());
                activityApplyDTO.setActivityTitle(activityApply.getActivityTitle());
                activityApplyDTO.setActivityDate(activityApply.getActivityDate());
                activityApplyDTO.setActivityTime(activityApply.getActivityTime());
                activityApplyDTO.setActivityCampus(activityApply.getActivityCampus());
                activityApplyDTO.setActivityLocation(activityApply.getActivityLocation());
                activityApplyDTO.setActivityPeopleNum(activityApply.getActivityPeopleNum());
                activityApplyDTO.setActivityDescription(activityApply.getActivityDescription());
                activityApplyDTO.setStatus(activityApply.getStatus());
                activityApplyDTO.setFeedback(activityApply.getFeedback());
                activityApplyDTO.setActivityApplyTime(activityApply.getActivityApplyTime());

                //获取用户名称
                UserInfoEntity userInfoEntity = userInfoService.getById(activityApply.getUserId());
                String userName = userInfoEntity.getUserName();
                activityApplyDTO.setUserName(userName);
                activityApplyDTO.setClubName(clubName);
                activityApplyDTOList.add(activityApplyDTO);
            }

            return new CommonResult(200, "查询成功", activityApplyDTOList);
        } else {
            return new CommonResult(400, "无记录");
        }
    }

    /* 获得所有活动申请 */
    public CommonResult getAllActivityApply() {
        List<ActivityApplyEntity> activityApplyList = activityApplyService.lambdaQuery().list();
        if (activityApplyList.size() > 0) {
            //将数据映射到DTO中
            List<GetActivityApplyDTO> activityApplyDTOList = new ArrayList<>();
            for (ActivityApplyEntity activityApply: activityApplyList){
                //获取社团名称
                ClubEntity clubEntity = clubService.getById(activityApply.getClubId());
                String clubName = clubEntity.getClubName();
                GetActivityApplyDTO activityApplyDTO = new GetActivityApplyDTO();
                activityApplyDTO.setActivityApplyId(activityApply.getActivityApplyId());
                activityApplyDTO.setUserId(activityApply.getUserId());
                activityApplyDTO.setClubId(activityApply.getClubId());
                activityApplyDTO.setActivityApplyReason(activityApply.getActivityApplyReason());
                activityApplyDTO.setActivityTitle(activityApply.getActivityTitle());
                activityApplyDTO.setActivityDate(activityApply.getActivityDate());
                activityApplyDTO.setActivityTime(activityApply.getActivityTime());
                activityApplyDTO.setActivityCampus(activityApply.getActivityCampus());
                activityApplyDTO.setActivityLocation(activityApply.getActivityLocation());
                activityApplyDTO.setActivityPeopleNum(activityApply.getActivityPeopleNum());
                activityApplyDTO.setActivityDescription(activityApply.getActivityDescription());
                activityApplyDTO.setStatus(activityApply.getStatus());
                activityApplyDTO.setFeedback(activityApply.getFeedback());
                activityApplyDTO.setActivityApplyTime(activityApply.getActivityApplyTime());

                //获取用户名称
                UserInfoEntity userInfoEntity = userInfoService.getById(activityApply.getUserId());
                String userName = userInfoEntity.getUserName();
                activityApplyDTO.setUserName(userName);
                activityApplyDTO.setClubName(clubName);
                activityApplyDTOList.add(activityApplyDTO);
            }

            return new CommonResult(200, "查询成功", activityApplyDTOList);
        } else {
            return new CommonResult(400, "无记录");
        }
    }

    /* 获得本社团某一个状态的所有活动申请 */
    @GetMapping()
    public CommonResult getActivityApplyByClubIdAndStatus(@RequestBody(required = false) GetActivityApplyParam activityApplyParam) {
        if(activityApplyParam == null){
            return getAllActivityApply();
        }
        Integer clubId = activityApplyParam.clubId;
        String status = activityApplyParam.status;
        List<ActivityApplyEntity> activityApplyList = activityApplyService.lambdaQuery()
                .eq(ActivityApplyEntity::getClubId,clubId)
                .eq(ActivityApplyEntity::getStatus,status)
                .list();
        if (activityApplyList.size() > 0) {
            //获取社团名称
            ClubEntity clubEntity = clubService.getById(clubId);
            String clubName = clubEntity.getClubName();
            //将数据映射到DTO中
            List<GetActivityApplyDTO> activityApplyDTOList = new ArrayList<>();
            for (ActivityApplyEntity activityApply: activityApplyList){
                GetActivityApplyDTO activityApplyDTO = new GetActivityApplyDTO();
                activityApplyDTO.setActivityApplyId(activityApply.getActivityApplyId());
                activityApplyDTO.setUserId(activityApply.getUserId());
                activityApplyDTO.setClubId(activityApply.getClubId());
                activityApplyDTO.setActivityApplyReason(activityApply.getActivityApplyReason());
                activityApplyDTO.setActivityTitle(activityApply.getActivityTitle());
                activityApplyDTO.setActivityDate(activityApply.getActivityDate());
                activityApplyDTO.setActivityTime(activityApply.getActivityTime());
                activityApplyDTO.setActivityCampus(activityApply.getActivityCampus());
                activityApplyDTO.setActivityLocation(activityApply.getActivityLocation());
                activityApplyDTO.setActivityPeopleNum(activityApply.getActivityPeopleNum());
                activityApplyDTO.setActivityDescription(activityApply.getActivityDescription());
                activityApplyDTO.setStatus(activityApply.getStatus());
                activityApplyDTO.setFeedback(activityApply.getFeedback());
                activityApplyDTO.setActivityApplyTime(activityApply.getActivityApplyTime());

                //获取用户名称
                UserInfoEntity userInfoEntity = userInfoService.getById(activityApply.getUserId());
                String userName = userInfoEntity.getUserName();
                activityApplyDTO.setUserName(userName);
                activityApplyDTO.setClubName(clubName);
                activityApplyDTOList.add(activityApplyDTO);
            }

            return new CommonResult(200, "查询成功", activityApplyDTOList);
        } else {
            return new CommonResult(400, "无记录");
        }
    }

    @PutMapping
    public CommonResult updateById(@RequestBody String json) {
        JSONObject jsonObject = new JSONObject(json);
        int id = jsonObject.getInt("activityApplyId");
        String status = jsonObject.optString("status");
        String feedback =jsonObject.optString("feedback");
        ActivityApplyEntity activityApply = activityApplyService.getById(id);
        activityApply.setStatus(status);
        activityApply.setFeedback(feedback);
        ActivityEntity activity = new ActivityEntity();
        boolean isSuccess = activityApplyService.updateById(activityApply);
        if(status.equals("已通过"))
        {
            try {
                ActivityEntity activityEntity = new ActivityEntity();
                activityEntity.setClubId(activityApply.getClubId());
                activityEntity.setActivityTitle(activityApply.getActivityTitle());
                activityEntity.setActivityDate(activityApply.getActivityDate());
                activityEntity.setActivityTime(activityApply.getActivityTime());
                activityEntity.setActivityCampus(activityApply.getActivityCampus());
                activityEntity.setActivityLocation(activityApply.getActivityLocation());
                activityEntity.setActivityPeopleNum(activityApply.getActivityPeopleNum());
                activityEntity.setActivityDescription(activityApply.getActivityDescription());
                activityService.save(activityEntity);
                //系统向用户发通知
                NotificationParam notificationParam = new NotificationParam();
                notificationParam.setSenderType((byte)(2));
                notificationParam.setReceiverType((byte)(2));
                notificationParam.setReceiverId(activityApply.getUserId());
                notificationParam.setNotificationTitle("活动申请成功");
                notificationParam.setNotificationContent("您申请的活动：" + activityApply.getActivityTitle() + "，已成功创建");
                CommonResult result = restTemplate.postForObject("http://cloud-examine-service/notification/", notificationParam, CommonResult.class);
                result.setMessage("已通过该申请");
                return result;
            } catch (Exception e){
                return new CommonResult(400, "操作失败",e);
            }
        }
        else if(status.equals("已拒绝")){
            try {
                //系统向用户发通知
                NotificationParam notificationParam = new NotificationParam();
                notificationParam.setSenderType((byte)(2));
                notificationParam.setReceiverType((byte)(2));
                notificationParam.setReceiverId(activityApply.getUserId());
                notificationParam.setNotificationTitle("活动申请失败");
                notificationParam.setNotificationContent("您申请的活动：" + activityApply.getActivityTitle() + "，审核不通过");
                CommonResult result = restTemplate.postForObject("http://cloud-examine-service/notification/", notificationParam, CommonResult.class);
                result.setMessage("已驳回该申请");
                return result;
            } catch (Exception e){
                return new CommonResult(400, "操作失败",e);
            }
        }

        if (isSuccess) {
            return new CommonResult(200, "修改成功");
        }
        else {
            return new CommonResult(400, "修改失败");
        }

    }



}
