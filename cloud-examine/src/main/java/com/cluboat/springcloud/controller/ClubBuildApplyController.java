package com.cluboat.springcloud.controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.apply.ClubBuildApplyEntity;
import com.cluboat.springcloud.service.ClubBuildApplyService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/clubbuildapply")
public class ClubBuildApplyController {
    @Resource
    private ClubBuildApplyService clubBuildApplyService;
    @GetMapping("/{id}")
    public CommonResult getClubBuildApplyById(@PathVariable("id") int id) {
        ClubBuildApplyEntity clubBuildApply = clubBuildApplyService.getById(id);
        log.info("****插入结果：{payment}");
        if (clubBuildApply != null) {
            return new CommonResult(200, "查询成功", clubBuildApply);
        } else {
            return new CommonResult(444, "无记录");
        }
    }
    @GetMapping
    public CommonResult getClubBuildApplyById() {
        List<ClubBuildApplyEntity> clubBuildApply = clubBuildApplyService.list();
        log.info("****插入结果：{payment}");
        if (clubBuildApply != null) {
            return new CommonResult(200, "查询成功", clubBuildApply);
        } else {
            return new CommonResult(444, "无记录");
        }
    }

    @DeleteMapping("/{id}")
    public CommonResult removeById(@PathVariable("id") int id) {
        boolean isSuccess = clubBuildApplyService.removeById(id);
        if (isSuccess)
            return new CommonResult(200, "删除成功");
        else
            return new CommonResult(400, "删除失败");

    }

    @PostMapping
    public CommonResult updateById(@RequestBody String json) {
        JSONObject jsonObject = new JSONObject(json);
        int id = jsonObject.getInt("id");
        int state = jsonObject.getInt("state");
        String feedback =jsonObject.optString("feedback");
        ClubBuildApplyEntity clubBuildApply = clubBuildApplyService.getById(id);
        clubBuildApply.setBuildApplyIsPass(state);
        clubBuildApply.setFeedback(feedback);
        boolean isSuccess = clubBuildApplyService.updateById(clubBuildApply);
        if (isSuccess)
            return new CommonResult(200, "修改成功");
        else
            return new CommonResult(400, "修改失败");

    }
}
