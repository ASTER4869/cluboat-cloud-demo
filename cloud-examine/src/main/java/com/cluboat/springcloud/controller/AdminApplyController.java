package com.cluboat.springcloud.controller;

import com.cluboat.springcloud.entities.CommonResult;
<<<<<<< Updated upstream
import com.cluboat.springcloud.entity.ActivityEntity;
import com.cluboat.springcloud.entity.ClubAdminEntity;
import com.cluboat.springcloud.entity.apply.ActivityApplyEntity;
import com.cluboat.springcloud.entity.apply.AdminApplyEntity;
import com.cluboat.springcloud.entity.apply.JoinApplyEntity;
import com.cluboat.springcloud.service.AdminApplyService;
import com.cluboat.springcloud.service.ClubAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
=======
import com.cluboat.springcloud.entity.apply.AdminApplyEntity;
import com.cluboat.springcloud.service.AdminApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
>>>>>>> Stashed changes


@RestController
@Slf4j
<<<<<<< Updated upstream
@RequestMapping("/adminApply")
=======
@RequestMapping("/AdminApply")
>>>>>>> Stashed changes
public class AdminApplyController {

    @Resource
    private AdminApplyService adminApplyService;


<<<<<<< Updated upstream
    @GetMapping("/{id}")
    public CommonResult getPaymentById(@PathVariable("id") int id){
        List<AdminApplyEntity> list = adminApplyService.lambdaQuery().eq(AdminApplyEntity::getAdminClubId, id).list();
        log.info("****插入结果：{payment}");
        if(list!=null){
            return new CommonResult(200,"查询成功",list);
=======
    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") int id){
        AdminApplyEntity adminApply = adminApplyService.getById(id);
        log.info("****插入结果：{payment}");
        if(adminApply!=null){
            return new CommonResult(200,"查询成功",adminApply);
>>>>>>> Stashed changes
        }else {
            return new CommonResult(444,"无记录");
        }
    }
<<<<<<< Updated upstream
    @Resource
    private ClubAdminService clubAdminService;
    @PostMapping("/{id}")
    public CommonResult updateById(@PathVariable("id") int id,@RequestParam byte state) {
        AdminApplyEntity adminApply = adminApplyService.getById(id);
        adminApply.setAdminApplyIsPass(state);
        ClubAdminEntity clubAdminEntity=new ClubAdminEntity();
        boolean isSuccess = adminApplyService.updateById(adminApply);
        log.info("****插入结果：{state}");
        if(state==1) {
            clubAdminEntity.setPermission((byte)(0));
            clubAdminEntity.setClubId(adminApply.getAdminClubId());
            clubAdminEntity.setUserId(adminApply.getUserId());
            isSuccess = clubAdminService.save(clubAdminEntity);
        }




        if (isSuccess) {
            return new CommonResult(200, "修改成功");
        }
        else {
            return new CommonResult(400, "修改失败");
        }

    }
=======
>>>>>>> Stashed changes
}
