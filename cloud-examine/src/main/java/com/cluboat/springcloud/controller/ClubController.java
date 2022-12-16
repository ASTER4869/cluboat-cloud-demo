package com.cluboat.springcloud.controller;

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
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/club")
public class ClubController {


    @Resource
    private ClubService clubService;
    @GetMapping("/{id}")
    public CommonResult getClubById(@PathVariable("id") int id) {
        ClubEntity club = clubService.getById(id);
        log.info("****插入结果：{payment}");
        if (club != null) {
            return new CommonResult(200, "查询成功", club);
        } else {
            return new CommonResult(400, "无记录");
        }
    }
    @GetMapping
    public CommonResult getAllClub() {
        List<ClubEntity> clubEntityList = clubService.list();
        log.info("****插入结果：{payment}");
        if (!clubEntityList.isEmpty()) {
            return new CommonResult(200, "查询成功", clubEntityList);
        } else {
            return new CommonResult(400, "无记录");
        }
    }

    @DeleteMapping("/{id}")
    public CommonResult removeById(@PathVariable("id") int id) {
        boolean isSuccess = clubService.removeById(id);
        if (isSuccess) {
            return new CommonResult(200, "删除成功");
        }
        else{
            return new CommonResult(400, "删除失败");
        }

    }

    @PostMapping
    public CommonResult createClub(@RequestBody ClubParam clubParam) {
        ClubEntity club = new ClubEntity();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        clubParam.clubCreateTime=new Timestamp(System.currentTimeMillis());
        club.setClub(clubParam);
        try {
            clubService.save(club);
            return new CommonResult(200, "创建成功");
        } catch (Exception e){
            return new CommonResult(400, "创建失败",e);
        }

    }
}
