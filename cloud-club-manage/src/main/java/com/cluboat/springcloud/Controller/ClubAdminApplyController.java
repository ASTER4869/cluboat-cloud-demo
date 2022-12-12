package com.cluboat.springcloud.Controller;


import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.AdminApplyEntity;
import com.cluboat.springcloud.entity.param.AdminApplyParam;
import com.cluboat.springcloud.service.AdminApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/administrator")
public class ClubAdminApplyController {

    @Resource
    private AdminApplyService adminApplyService;

    /* 提交管理员  申请 */
    @PostMapping
    public CommonResult createAdminApply(@RequestBody AdminApplyParam adminApplyParam) {
        AdminApplyEntity adminApply = new AdminApplyEntity();
        adminApply.setFeedback(null);  //提交申请没有反馈！
        adminApply.setAdminApplyIsPass((byte) 0);  //初始未通过！

        adminApplyParam.adminApplyTime= new Timestamp(System.currentTimeMillis());
        adminApply.setAdminApply(adminApplyParam);
        try {
            adminApplyService.save(adminApply);
            return new CommonResult(200, "上传申请成功");
        } catch (Exception e) {
            return new CommonResult(400, "上传申请失败", e);
        }

    }

    /* 获得我提交的管理员申请 */
    @GetMapping("/{userId}")
    public CommonResult getAdminApplyByUserId(@PathVariable("userId") int id) {
        List<AdminApplyEntity> adminApplyList = adminApplyService.lambdaQuery()
                .eq(AdminApplyEntity::getUserId,id).list();
        log.info("****插入结果：{payment}");
        if ( adminApplyList.size()  > 0) {
            return new CommonResult(200, "查询成功", adminApplyList);
        } else {
            return new CommonResult(400, "无记录");
        }
    }

}
