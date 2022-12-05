package com.cluboat.springcloud.controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ClubEntity;
import com.cluboat.springcloud.entity.dto.ClubDTO;
import com.cluboat.springcloud.service.ClubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/club")
public class ClubController {
    @Resource
    ClubService clubService;
    //查看提交的管理员申请
    @GetMapping("/{clubId}")
    public CommonResult getClub(@PathVariable Integer clubId) {
        ClubDTO club = clubService.GetClub(clubId);
        if(club!=null){
            return new CommonResult(200,"查询成功", club);
        }
        else{
            return new CommonResult(444,"无记录");
        }

    }

}
