package com.cluboat.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.CommentEntity;
import com.cluboat.springcloud.entity.TagEntity;
import com.cluboat.springcloud.entity.dto.PostTagDTO;
import com.cluboat.springcloud.entity.param.TagAddParam;
import com.cluboat.springcloud.mapper.TagMapper;
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

    @Resource
    TagMapper tagMapper;

    @GetMapping("/{id}")
    public CommonResult getTagListByClubId(@PathVariable("id") int id){
        List<PostTagDTO> tagList = tagService.GetTagListByClubId(id);
        if (tagList.isEmpty()==false) {
            return new CommonResult(200, "查询成功", tagList);
        } else {
            return new CommonResult(444, "无记录");
        }
    }

    @PostMapping
    public CommonResult InsertTag(@RequestBody TagAddParam param){

        try {
            TagEntity tag = new TagEntity();
            tag.setClubId(param.getClubId());
            tag.setTagName(param.getTagName());
            LambdaQueryWrapper<TagEntity> wrapper = new LambdaQueryWrapper<TagEntity>()
                    .eq(TagEntity::getTagName, tag.getTagName())
                    .eq(TagEntity::getClubId, tag.getClubId());
            //如果已存在
            if(tagMapper.exists(wrapper)){
                return new CommonResult(400, "添加失败");
            }
            boolean re = tagService.save(tag);
            if (re) {
                return new CommonResult(200, "添加成功", re);
            } else {
                return new CommonResult(400, "添加失败");
            }
        }
        catch (Exception e){
            return new CommonResult(400, "添加失败");
        }
    }


}
