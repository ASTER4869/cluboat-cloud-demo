package com.cluboat.springcloud.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.BelongEntity;
import com.cluboat.springcloud.entity.DTO.NotificationDTO;
import com.cluboat.springcloud.entity.NewsEntity;
import com.cluboat.springcloud.entity.param.GetPermissionParam;
import com.cluboat.springcloud.service.BelongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/permission")
public class ClubPermissionController {
    @Resource
    BelongService belongService;

    @PostMapping
    public CommonResult getAllClubNotificationById(@RequestBody GetPermissionParam param) {
        LambdaQueryWrapper<BelongEntity> wrapper = new LambdaQueryWrapper<BelongEntity>()
                .eq(BelongEntity::getUserId, param.getUserId())
                .eq(BelongEntity::getClubId, param.getClubId());
        BelongEntity belongEntity = belongService.getOne(wrapper);
        if(belongEntity==null){
            return new CommonResult(444, "无记录");
        }
        int permission = belongEntity.getPermission();
            return new CommonResult(200, "查询成功",permission);

    }
}
