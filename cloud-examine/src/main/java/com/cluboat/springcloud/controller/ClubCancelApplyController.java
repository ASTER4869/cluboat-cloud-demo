package com.cluboat.springcloud.controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.apply.ClubCancelApplyEntity;
import com.cluboat.springcloud.service.ClubCancelApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/clubcancelapply")
public class ClubCancelApplyController {
    @Resource
    private ClubCancelApplyService clubCancelApplyService;
    @GetMapping("/{id}")
    public CommonResult getClubCancelApplyById(@PathVariable("id") int id) {
        ClubCancelApplyEntity clubCancelApply = clubCancelApplyService.getById(id);
        log.info("****插入结果：{payment}");
        if (clubCancelApply != null) {
            return new CommonResult(200, "查询成功", clubCancelApply);
        } else {
            return new CommonResult(444, "无记录");
        }
    }

    @DeleteMapping("/{id}")
    public CommonResult removeById(@PathVariable("id") int id) {
        boolean isSuccess = clubCancelApplyService.removeById(id);
        if (isSuccess)
            return new CommonResult(200, "删除成功");
        else
            return new CommonResult(400, "删除失败");

    }

    @PostMapping("/{id}")
    public CommonResult updateById(@PathVariable("id") int id,@RequestParam byte state) {
        ClubCancelApplyEntity clubCancelApply = clubCancelApplyService.getById(id);
        clubCancelApply.setCancelApplyIsPass(state);
        boolean isSuccess = clubCancelApplyService.updateById(clubCancelApply);
        if (isSuccess)
            return new CommonResult(200, "修改成功");
        else
            return new CommonResult(400, "修改失败");

    }
}
