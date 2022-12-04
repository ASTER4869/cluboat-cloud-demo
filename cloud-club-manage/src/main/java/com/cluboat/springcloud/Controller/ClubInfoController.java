package com.cluboat.springcloud.Controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ClubEntity;
import com.cluboat.springcloud.entity.param.ClubParam;
import com.cluboat.springcloud.service.ClubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/club")
public class ClubInfoController {
    @Resource
    private ClubService clubService;

    /* 返回根据user id找到的社团 需要连表查询*/
    @GetMapping("/{club_id}")
    public CommonResult getClubById(@PathVariable int club_id) {
        ClubEntity clubEntity = clubService.getById(club_id);
        log.info("****插入结果：{payment}");
        if (clubEntity != null) {
            return new CommonResult(200, "查询成功", clubEntity);
        } else {
            return new CommonResult(400, "无记录");
        }
    }

}
