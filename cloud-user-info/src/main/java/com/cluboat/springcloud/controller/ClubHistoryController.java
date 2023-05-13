package com.cluboat.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.entity.ClubHistoryEntity;
import com.cluboat.springcloud.entity.param.AddClubHistoryParam;
import com.cluboat.springcloud.service.ClubHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;

@RestController
@Slf4j
@RequestMapping("/club-history")
public class ClubHistoryController {

    @Resource
    ClubHistoryService clubHistoryService;

    @PostMapping()
    public CommonResult RecordClubHistory(@RequestBody AddClubHistoryParam clubHistoryParam){
        try {
            LambdaQueryWrapper<ClubHistoryEntity> wrapper = new LambdaQueryWrapper<ClubHistoryEntity>()
                    .eq(ClubHistoryEntity::getClubId, clubHistoryParam.getClubId())
                    .eq(ClubHistoryEntity::getUserId, clubHistoryParam.getUserId());
            ClubHistoryEntity clubHistoryEntity = clubHistoryService.getOne(wrapper, false);
            // 如果目前还没有记录
            if (clubHistoryEntity == null){
                clubHistoryEntity = new ClubHistoryEntity();
                clubHistoryEntity.setNum(1);
            }
            else {
                clubHistoryEntity.setNum(clubHistoryEntity.getNum() + 1);
            }
            clubHistoryEntity.setWatchTime(new Timestamp(System.currentTimeMillis()));
            clubHistoryEntity.setClubId(clubHistoryParam.getClubId());
            clubHistoryEntity.setUserId(clubHistoryParam.getUserId());
            if(clubHistoryService.saveOrUpdate(clubHistoryEntity) == true){
                return new CommonResult(200, "记录成功");
            }
            else {
                return new CommonResult(401, "记录失败");
            }
        }
        catch (Exception e){
            return new CommonResult(400, "系统出现错误", e);
        }
    }
}
