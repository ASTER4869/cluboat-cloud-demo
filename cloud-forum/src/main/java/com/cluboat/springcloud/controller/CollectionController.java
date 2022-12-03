package com.cluboat.springcloud.controller;

import com.cluboat.springcloud.entities.CommonResult;
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
    public CommonResult InsertCollection(@PathVariable CollectionAddParam param){
        CollectionEntity collection = new CollectionEntity();
        collection.setPostId(param.getPostId());
        collection.setUserId(param.getUserId());
        boolean re = collectionService.save(collection);
        if(re){
            return new CommonResult(200, "添加成功", collection.getPostId());
        } else {
            return new CommonResult(400, "无记录");
        }
    }

    @DeleteMapping
    public CommonResult DeleteCollection(@PathVariable CollectionDeleteParam param){
        int collectionNum = collectionService.DeleteCollection(param);
        if(collectionNum != 0){
            return new CommonResult(200, "删除成功", collectionNum);
        } else {
            return new CommonResult(400, "无记录");
        }
    }
}
