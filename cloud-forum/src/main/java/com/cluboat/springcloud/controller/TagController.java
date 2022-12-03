package com.cluboat.springcloud.controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.TagEntity;
import com.cluboat.springcloud.entity.dto.PostTagDTO;
import com.cluboat.springcloud.entity.param.TagAddParam;
import com.cluboat.springcloud.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/tag")
public class TagController {
    @Resource
    TagService tagService;

    @GetMapping("/{id}")
    public CommonResult getTagListByClubId(@PathVariable("id") int id){
        List<PostTagDTO> tagList = tagService.GetTagListByClubId(id);
        if (tagList != null) {
            return new CommonResult(200, "查询成功", tagList);
        } else {
            return new CommonResult(400, "无记录");
        }
    }

    @PostMapping
    public CommonResult InsertTag(@PathVariable TagAddParam param){
        TagEntity tag = new TagEntity();
        tag.setClubId(param.getClubId());
        tag.setTagName(param.getTagName());
        boolean re = tagService.save(tag);
        if (re) {
            return new CommonResult(200, "添加成功", re);
        } else {
            return new CommonResult(400, "无记录");
        }
    }


}
