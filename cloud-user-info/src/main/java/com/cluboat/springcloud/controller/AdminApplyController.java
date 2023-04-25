package com.cluboat.springcloud.controller;


import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.AdminApplyEntity;
import com.cluboat.springcloud.entity.UserInfoEntity;
import com.cluboat.springcloud.entity.dto.GetAdminApplyDTO;
import com.cluboat.springcloud.service.AdminApplyService;
import com.cluboat.springcloud.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin-apply")
public class AdminApplyController {
    @Resource
    AdminApplyService adminApplyService;

    @Resource
    UserInfoService userInfoService;

    /* 获得我提交的管理员申请 */
    @GetMapping("/{userId}")
    public CommonResult getAdminApplyByUserId(@PathVariable int userId) {
        try {
            List<AdminApplyEntity> adminApplyList = adminApplyService.lambdaQuery()
                    .eq(AdminApplyEntity::getUserId,userId).list();
            if ( adminApplyList.size()  > 0) {
                List<GetAdminApplyDTO> adminApplyDTOList = new ArrayList<>();
                for (AdminApplyEntity adminApply: adminApplyList){
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
                return new CommonResult(200, "查询成功", adminApplyDTOList);
            } else {
                return new CommonResult(400, "无记录");
            }
        }catch (Exception e){
            return new CommonResult(400, "出现错误", e);
        }
    }


}
