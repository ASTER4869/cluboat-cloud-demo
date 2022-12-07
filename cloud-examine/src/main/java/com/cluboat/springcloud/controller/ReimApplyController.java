package com.cluboat.springcloud.controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ReimApplyEntity;
import com.cluboat.springcloud.service.ReimApplyService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/reimapply")
public class ReimApplyController {
    @Resource
    private ReimApplyService reimApplyService;
    @GetMapping("/{id}")
    public CommonResult getReimApplyById(@PathVariable("id") int id) {
        ReimApplyEntity reimApply = reimApplyService.getById(id);
        log.info("****插入结果：{payment}");
        if (reimApply != null) {
            return new CommonResult(200, "查询成功", reimApply);
        } else {
            return new CommonResult(444, "无记录");
        }
    }
    @GetMapping
    public CommonResult getReimApplyById() {
        List<ReimApplyEntity> reimApply = reimApplyService.list();
        log.info("****插入结果：{payment}");
        if (reimApply != null) {
            return new CommonResult(200, "查询成功", reimApply);
        } else {
            return new CommonResult(444, "无记录");
        }
    }
    @DeleteMapping("/{id}")
    public CommonResult removeById(@PathVariable("id") int id) {
        boolean isSuccess = reimApplyService.removeById(id);
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
        ReimApplyEntity reimApply = reimApplyService.getById(id);
        reimApply.setReimApplyIsPass(state);
        reimApply.setFeedback(feedback);
        boolean isSuccess = reimApplyService.updateById(reimApply);
        if (isSuccess)
            return new CommonResult(200, "修改成功");
        else
            return new CommonResult(400, "修改失败");

    }
}
