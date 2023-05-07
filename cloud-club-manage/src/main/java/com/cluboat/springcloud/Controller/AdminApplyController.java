package com.cluboat.springcloud.Controller;


import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.entity.AdminApplyEntity;
import com.cluboat.springcloud.entity.BelongEntity;
import com.cluboat.springcloud.entity.ClubEntity;
import com.cluboat.springcloud.entity.DTO.GetAdminApplyDTO;
import com.cluboat.springcloud.entity.UserInfoEntity;
import com.cluboat.springcloud.entity.param.AdminApplyParam;
import com.cluboat.springcloud.entity.param.NotificationParam;
import com.cluboat.springcloud.service.AdminApplyService;
import com.cluboat.springcloud.service.BelongService;
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
@RequestMapping("/admin-apply")
public class AdminApplyController {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private AdminApplyService adminApplyService;

    @Resource
    UserInfoService userInfoService;

    @Resource
    private BelongService belongService;

    @Resource
    ClubService clubService;

    /* 提交管理员  申请 */
    @PostMapping
    public CommonResult createAdminApply(@RequestBody AdminApplyParam adminApplyParam) {
        AdminApplyEntity adminApply = new AdminApplyEntity();
        adminApply.setFeedback(null);  //提交申请没有反馈！
        adminApply.setStatus("待审批");  //初始未通过！

        adminApply.setUserId(adminApplyParam.userId);
        adminApply.setAdminApplyReason(adminApplyParam.adminApplyReason);
        adminApply.setAdminApplyTime(new Timestamp(System.currentTimeMillis()));
        adminApply.setAdminClubId(adminApplyParam.adminClubId);
        try {
            adminApplyService.save(adminApply);
            return new CommonResult(200, "上传申请成功");
        } catch (Exception e) {
            return new CommonResult(400, "上传申请失败", e);
        }

    }


    //获取某社团提交的所有管理员申请
    @GetMapping("/{clubId}")
    public CommonResult getPaymentByClubId(@PathVariable("clubId") int id){
        List<AdminApplyEntity> list = adminApplyService.lambdaQuery().eq(AdminApplyEntity::getAdminClubId, id).list();
        log.info("****插入结果：{payment}");
        if(!list.isEmpty()){
            List<GetAdminApplyDTO> adminApplyDTOList = new ArrayList<>();
            for (AdminApplyEntity adminApply: list) {
                UserInfoEntity userInfoEntity = userInfoService.getById(adminApply.getUserId());
                String userName = userInfoEntity.getUserName();
                GetAdminApplyDTO adminApplyDTO = new GetAdminApplyDTO();
                adminApplyDTO.setAdminApplyId(adminApply.getAdminApplyId());
                adminApplyDTO.setUserId(adminApply.getUserId());
                adminApplyDTO.setAdminClubId(adminApply.getAdminClubId());
                adminApplyDTO.setAdminApplyTime(adminApply.getAdminApplyTime());
                adminApplyDTO.setAdminApplyReason(adminApply.getAdminApplyReason());
                adminApplyDTO.setFeedback(adminApply.getFeedback());
                adminApplyDTO.setStatus(adminApply.getStatus());
                adminApplyDTO.setUserName(userName);
                adminApplyDTOList.add(adminApplyDTO);
            }
            return new CommonResult(200,"查询成功",adminApplyDTOList);
        }else {
            return new CommonResult(400,"无记录");
        }
    }

    @PutMapping
    public CommonResult updateById(@RequestBody String json) {
        JSONObject jsonObject = new JSONObject(json);
        int id = jsonObject.getInt("administratorApplyId");
        String status = jsonObject.optString("status");
        String feedback =jsonObject.optString("feedback");
        AdminApplyEntity adminApply = adminApplyService.getById(id);
        adminApply.setStatus(status);
        adminApply.setFeedback(feedback);
        BelongEntity belong=new BelongEntity();
        boolean isSuccess = adminApplyService.updateById(adminApply);
        ClubEntity clubEntity = clubService.getById(adminApply.getAdminClubId());
        String clubName = clubEntity.getClubName();
        if(status.equals("已通过"))
        {
            belong.setUserId(adminApply.getUserId());
            belong.setClubId(adminApply.getAdminClubId());
            belong.setState((byte)0);
            belong.setPermission((byte)1);
            belongService.save(belong);
            try {
                //系统向用户发通知
                NotificationParam notificationParam = new NotificationParam();
                notificationParam.setSenderType((byte)(2));
                notificationParam.setReceiverType((byte)(2));
                notificationParam.setReceiverId(adminApply.getUserId());
                notificationParam.setNotificationTitle("管理员申请成功");
                notificationParam.setNotificationContent("您申请的" + clubName + "管理员申请已成功");
                CommonResult result = restTemplate.postForObject("http://cloud-examine-service/notification/", notificationParam, CommonResult.class);
                result.setMessage("已通过该申请");
                return result;
            } catch (Exception e){
                return new CommonResult(400, "管理员申请通过的通知发送失败",e);
            }
        }
        else if(status.equals("已拒绝"))
        {
            try {
                //系统向用户发通知
                NotificationParam notificationParam = new NotificationParam();
                notificationParam.setSenderType((byte)(2));
                notificationParam.setReceiverType((byte)(2));
                notificationParam.setReceiverId(adminApply.getUserId());
                notificationParam.setNotificationTitle("管理员申请失败");
                notificationParam.setNotificationContent("您申请的" + clubName + "管理员申请审核未通过");
                CommonResult result = restTemplate.postForObject("http://cloud-examine-service/notification/", notificationParam, CommonResult.class);
                result.setMessage("已驳回该申请");
                return result;
            } catch (Exception e){
                return new CommonResult(400, "管理员申请失败的通知发送失败",e);
            }
        }
        if (isSuccess) {
            return new CommonResult(200, "修改成功");
        }
        else {
            return new CommonResult(400, "操作失败");
        }

    }
}
