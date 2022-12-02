package com.cluboat.springcloud.controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.PubnotEntity;
import com.cluboat.springcloud.entity.param.ClubParam;
import com.cluboat.springcloud.entity.param.PubnotParam;
import com.cluboat.springcloud.service.PubnotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/pubnot")
public class PubnotController {
    @Resource
    private PubnotService pubnotService;
    @GetMapping("/{id}")
    public CommonResult getPubnotById(@PathVariable("id") int id) {
        PubnotEntity pubnot = pubnotService.getById(id);
        log.info("****插入结果：{payment}");
        if (pubnot != null) {
            return new CommonResult(200, "查询成功", pubnot);
        } else {
            return new CommonResult(400, "无记录");
        }
    }
    @GetMapping
    public CommonResult getAllPubnot() {
        List<PubnotEntity> pubnotEntityList = pubnotService.list();
        log.info("****插入结果：{payment}");
        if (pubnotEntityList != null) {
            return new CommonResult(200, "查询成功", pubnotEntityList);
        } else {
            return new CommonResult(400, "无记录");
        }
    }

    @DeleteMapping("/{id}")
    public CommonResult removeById(@PathVariable("id") int id) {
        boolean isSuccess = pubnotService.removeById(id);
        if (isSuccess) {
            return new CommonResult(200, "删除成功");
        }
        else{
            return new CommonResult(400, "删除失败");
        }

    }

    @PostMapping
    public CommonResult createPubnot(PubnotParam pubnotParam) {
        PubnotEntity pubnot = new PubnotEntity();
        pubnotParam.pubnotTime=new Timestamp(System.currentTimeMillis());
        System.out.println(pubnotParam);
        pubnot.setPubnot(pubnotParam);
        try {
            pubnotService.save(pubnot);
            return new CommonResult(200, "创建成功");
        } catch (Exception e){
            return new CommonResult(400, "修改失败",e);
        }
    }

    @PostMapping("/{id}")
    public CommonResult editPubnot(@PathVariable("id") int id,PubnotParam pubnotParam) {
        PubnotEntity pubnot = pubnotService.getById(id);
        if(pubnotParam.pubnotContent!=""){
            pubnot.setPubnotContent(pubnotParam.pubnotContent);
        }
        if(pubnotParam.pubnotTitle!=""){
            pubnot.setPubnotTitle(pubnotParam.pubnotTitle);
        }
        if(pubnotService.updateById(pubnot)){
            return new CommonResult(200, "更新成功");
        }
        else {
            return new CommonResult(400, "更新失败");
        }
    }


}
