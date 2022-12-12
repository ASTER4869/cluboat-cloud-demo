package com.cluboat.springcloud.Controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ClubEntity;
import com.cluboat.springcloud.service.ClubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/club")
public class ClubInfoController {
    @Resource
    private ClubService clubService;

    /* 返回根据user id找到的社团 需要连表查询*/
    @GetMapping("/{clubId}")
    public CommonResult getClubById(@PathVariable("clubId") int id) {
        ClubEntity clubEntity = clubService.getById(id);
        log.info("****插入结果：{payment}");
        if (clubEntity != null ) {
            return new CommonResult(200, "查询成功", clubEntity);
        } else {
            return new CommonResult(400, "无记录");
        }
    }
}
