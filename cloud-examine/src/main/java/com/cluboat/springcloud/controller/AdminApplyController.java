package com.cluboat.springcloud.controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.Belong;
import com.cluboat.springcloud.entity.apply.AdminApplyEntity;
import com.cluboat.springcloud.service.AdminApplyService;
import com.cluboat.springcloud.service.BelongService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/adminApply")
public class AdminApplyController {

    @Resource
    private AdminApplyService adminApplyService;


    @GetMapping("/{id}")
    public CommonResult getPaymentById(@PathVariable("id") int id){
        List<AdminApplyEntity> list = adminApplyService.lambdaQuery().eq(AdminApplyEntity::getAdminClubId, id).list();
        log.info("****插入结果：{payment}");
        if(list!=null){
            return new CommonResult(200,"查询成功",list);
        }else {
            return new CommonResult(444,"无记录");
        }
    }
    @Resource
    private BelongService belongService;
    @PostMapping
    public CommonResult updateById(@RequestBody String json) {
        JSONObject jsonObject = new JSONObject(json);
        int id = jsonObject.getInt("id");
        int state = jsonObject.getInt("state");
        String feedback =jsonObject.optString("feedback");
        AdminApplyEntity adminApply = adminApplyService.getById(id);
        adminApply.setAdminApplyIsPass(state);
        adminApply.setFeedback(feedback);
        Belong belong=new Belong();
        boolean isSuccess = adminApplyService.updateById(adminApply);
        if(state==1) {
            belong.setPermission(1);
            belong.setClubId(adminApply.getAdminClubId());
            belong.setUserId(adminApply.getUserId());
            belong.setState(0);
            isSuccess = belongService.save(belong);
        }
        if (isSuccess) {
            return new CommonResult(200, "修改成功");
        }
        else {
            return new CommonResult(400, "修改失败");
        }

    }
}
