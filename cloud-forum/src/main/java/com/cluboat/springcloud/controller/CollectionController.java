package com.cluboat.springcloud.controller;

import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.entity.CollectionEntity;
import com.cluboat.springcloud.entity.param.CollectionAddParam;
import com.cluboat.springcloud.entity.param.CollectionDeleteParam;
import com.cluboat.springcloud.service.CollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/collection")
public class CollectionController {
    @Resource
    private CollectionService collectionService;

    @PostMapping
    public CommonResult InsertCollection(@RequestBody CollectionAddParam param){
        CollectionEntity collectionEntity = new CollectionEntity();
        collectionEntity.setPostId(param.getPostId());
        collectionEntity.setUserId(param.getUserId());
        try {
            boolean re = collectionService.save(collectionEntity);
            if(re){
                return new CommonResult(200, "添加成功", collectionEntity.getPostId());
            } else {
                return new CommonResult(400, "添加失败");
            }
        }catch (Exception e){
            return new CommonResult(400, "添加失败");
        }
    }

    @DeleteMapping
    public CommonResult DeleteCollection(@RequestBody CollectionDeleteParam param){
        int collectionNum = collectionService.DeleteCollection(param);
        if(collectionNum != 0){
            return new CommonResult(200, "删除成功", collectionNum);
        } else {
            return new CommonResult(444, "删除失败");
        }
    }
}
