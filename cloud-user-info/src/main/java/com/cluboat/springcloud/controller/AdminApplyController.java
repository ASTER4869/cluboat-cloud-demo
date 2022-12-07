package com.cluboat.springcloud.controller;


import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.dto.AdminApplyDTO;
import com.cluboat.springcloud.entity.dto.MyActivityDTO;
import com.cluboat.springcloud.service.AdminApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin-apply")
public class AdminApplyController {
    @Resource
    AdminApplyService adminApplyService;

    //查看提交的管理员申请
    @GetMapping("/{userId}")
    public CommonResult getAdminApply(@PathVariable Integer userId) {
        List<AdminApplyDTO> adminApplyList = adminApplyService.GetAdminApply(userId);
        if(adminApplyList.isEmpty()!=true){
            return new CommonResult(200,"查询成功", adminApplyList);
        }
        else{
            return new CommonResult(444,"无记录");
        }

    }


}
