package com.cluboat.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.entity.CollectionEntity;
import com.cluboat.springcloud.entity.dto.CollectionDTO;
import com.cluboat.springcloud.entity.param.DeleteCollectionParam;
import com.cluboat.springcloud.service.CollectionService;
import com.cluboat.springcloud.service.PostTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/collection")
public class CollectionController {
    @Resource
    CollectionService collectionService;

    @Resource
    PostTagService postTagService;

    //查看收藏的帖子
    @GetMapping("/{userId}")
    public CommonResult getCollectionList(@PathVariable Integer userId) {
        List<CollectionDTO> collectionList = collectionService.GetCollection(userId);



        if(!collectionList.isEmpty()){
            //获取每个tag对应的tag名，并赋值
            for (CollectionDTO collection:collectionList) {
                collection.setPostTag(postTagService.GetPostTagList(collection.getPostId()));
            }
            return new CommonResult(200,"查询成功", collectionList);
        }
        else{
            return new CommonResult(444,"无记录");
        }
    }

//    //取消收藏
//    @DeleteMapping("/{userId}")
//    public CommonResult deleteCollection(@PathVariable Integer userId, @RequestParam Integer postId) {
//        LambdaQueryWrapper<CollectionEntity> wrapper = new LambdaQueryWrapper<CollectionEntity>()
//                .eq(CollectionEntity::getUserId, userId)
//                .eq(CollectionEntity::getPostId, postId);
//        CollectionEntity collectionEntity = collectionService.getOne(wrapper);
//
//
//
//        if(collectionEntity!=null){
//            collectionService.remove(wrapper);
//            return new CommonResult(200,"删除成功");
//        }
//        else{
//            return new CommonResult(444,"无记录");
//        }
//    }
    //取消收藏帖子
    @DeleteMapping
    public CommonResult deleteCollection(@RequestBody DeleteCollectionParam param) {
        LambdaQueryWrapper<CollectionEntity> wrapper = new LambdaQueryWrapper<CollectionEntity>()
                .eq(CollectionEntity::getUserId, param.getUserId())
                .eq(CollectionEntity::getPostId, param.getPostId());
        CollectionEntity collectionEntity = collectionService.getOne(wrapper);



        if(collectionEntity!=null){
            collectionService.remove(wrapper);
            return new CommonResult(200,"删除成功");
        }
        else{
            return new CommonResult(444,"无记录");
        }
    }


}
