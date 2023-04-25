package com.cluboat.springcloud.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.*;
import com.cluboat.springcloud.entity.DTO.GetJoinApplyDTO;
import com.cluboat.springcloud.entity.param.ActivityApplyParam;
import com.cluboat.springcloud.entity.param.JoinApplyParam;
import com.cluboat.springcloud.entity.param.NotificationParam;
import com.cluboat.springcloud.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/join-apply")
public class ClubJoinApplyController {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ClubService clubService;

    @Resource
    private JoinApplyService joinApplyService;

    @Resource
    private UserInfoService userInfoService;

    /* 提交入社申请 */
    @PostMapping
    public CommonResult createJoinApply(@RequestBody JoinApplyParam joinApplyParam) {
        JoinApplyEntity joinApply = new JoinApplyEntity();
        joinApply.setFeedback(null);  //提交申请没有反馈！
        joinApply.setStatus("待审批");  //初始未通过！
        joinApply.setJoinApplyContent(joinApplyParam.getJoinApplyContent());
        joinApply.setJoinApplyTime(new Timestamp(System.currentTimeMillis()));
        joinApply.setUserId(joinApplyParam.getUserId());
        joinApply.setJoinClubId(joinApplyParam.getJoinClubId());
        try {
            joinApplyService.save(joinApply);
            return new CommonResult(200, "上传申请成功");
        } catch (Exception e) {
            return new CommonResult(400, "上传申请失败", e);
        }

    }
    @GetMapping
    public CommonResult getAllJoinApply() {
        List<JoinApplyEntity> list = joinApplyService.list();
        if (!list.isEmpty()) {
            List<GetJoinApplyDTO> joinApplyDTOList = new ArrayList<>();
            for(JoinApplyEntity joinApply : list){
                GetJoinApplyDTO joinApplyDTO = new GetJoinApplyDTO();
                UserInfoEntity userInfoEntity = userInfoService.getById(joinApply.getUserId());
                String userName = userInfoEntity.getUserName();
                joinApplyDTO.setJoinApplyId(joinApply.getJoinApplyId());
                joinApplyDTO.setUserId(joinApply.getUserId());
                joinApplyDTO.setJoinClubId(joinApply.getJoinClubId());
                joinApplyDTO.setJoinApplyContent(joinApply.getJoinApplyContent());
                joinApplyDTO.setJoinApplyTime(joinApply.getJoinApplyTime());
                joinApplyDTO.setStatus(joinApply.getStatus());
                joinApplyDTO.setFeedback(joinApply.getFeedback());
                joinApplyDTO.setUserName(userName);
                joinApplyDTOList.add(joinApplyDTO);
            }
            return new CommonResult(200, "查询成功", joinApplyDTOList);
        } else {
            return new CommonResult(400, "无记录");
        }
    }

    @Resource
    private BelongService belongService;
    @PutMapping
    public CommonResult updateById(@RequestBody String json) {
        JSONObject jsonObject = new JSONObject(json);
        int id = jsonObject.getInt("joinApplyId");
        String  status = jsonObject.optString("status");
        String feedback =jsonObject.optString("feedback");
        JoinApplyEntity joinApply = joinApplyService.getById(id);
        BelongEntity belong=new BelongEntity();
        joinApply.setStatus(status);
        joinApply.setFeedback(feedback);

        boolean isSuccess = joinApplyService.updateById(joinApply);
        ClubEntity clubEntity = clubService.getById(joinApply.getJoinClubId());
        String clubName = clubEntity.getClubName();
        if(status.equals("已通过"))
        {
            belong.setUserId(id);
            belong.setClubId(joinApply.getJoinClubId());
            belong.setState((byte)0);
            belong.setPermission((byte)0);
            belongService.save(belong);
            try {
                //系统向用户发通知
                NotificationParam notificationParam = new NotificationParam();
                notificationParam.setSenderType((byte)(2));
                notificationParam.setReceiverType((byte)(2));
                notificationParam.setReceiverId(joinApply.getUserId());
                notificationParam.setNotificationTitle("入社成功");
                notificationParam.setNotificationContent("您申请的入社申请：" + clubName + "，已成功");
                CommonResult result = restTemplate.postForObject("http://cloud-examine-service/notification/", notificationParam, CommonResult.class);
                result.setMessage("已通过该申请");
                return result;
            } catch (Exception e){
                return new CommonResult(400, "入社申请通过的通知发送失败",e);
            }
        }
        else if(status.equals("已拒绝"))
        {
            try {
                //系统向用户发通知
                NotificationParam notificationParam = new NotificationParam();
                notificationParam.setSenderType((byte)(2));
                notificationParam.setReceiverType((byte)(2));
                notificationParam.setReceiverId(joinApply.getUserId());
                notificationParam.setNotificationTitle("入社失败");
                notificationParam.setNotificationContent("您申请的入社申请：" + clubName + "，审核不通过");
                CommonResult result = restTemplate.postForObject("http://cloud-examine-service/notification/", notificationParam, CommonResult.class);
                result.setMessage("已驳回该申请");
                return result;
            } catch (Exception e){
                return new CommonResult(400, "入社申请失败的通知发送失败",e);
            }
        }
        if (isSuccess) {
            return new CommonResult(200, "操作成功");
        }
        else {
            return new CommonResult(400, "操作失败");
        }

    }
    @GetMapping("/{id}")
    public CommonResult getByClubId(@PathVariable("id") int id) {
        QueryWrapper<JoinApplyEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("join_club_id",id);
        List<JoinApplyEntity> joinApplyList = joinApplyService.list(wrapper);
        if (!joinApplyList.isEmpty()) {
            List<GetJoinApplyDTO> joinApplyDTOList = new ArrayList<>();
            for(JoinApplyEntity joinApply : joinApplyList){
                GetJoinApplyDTO joinApplyDTO = new GetJoinApplyDTO();
                UserInfoEntity userInfoEntity = userInfoService.getById(joinApply.getUserId());
                String userName = userInfoEntity.getUserName();
                joinApplyDTO.setJoinApplyId(joinApply.getJoinApplyId());
                joinApplyDTO.setUserId(joinApply.getUserId());
                joinApplyDTO.setJoinClubId(joinApply.getJoinClubId());
                joinApplyDTO.setJoinApplyContent(joinApply.getJoinApplyContent());
                joinApplyDTO.setJoinApplyTime(joinApply.getJoinApplyTime());
                joinApplyDTO.setStatus(joinApply.getStatus());
                joinApplyDTO.setFeedback(joinApply.getFeedback());
                joinApplyDTO.setUserName(userName);
                joinApplyDTOList.add(joinApplyDTO);
            }
            return new CommonResult(200, "查询成功",joinApplyDTOList);
        }
        else {
            return new CommonResult(400, "无记录");
        }

    }

}
