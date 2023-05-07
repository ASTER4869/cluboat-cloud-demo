package com.cluboat.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.entity.BelongEntity;
import com.cluboat.springcloud.entity.dto.MyClubDTO;
import com.cluboat.springcloud.entity.param.DeleteMyClubParam;
import com.cluboat.springcloud.service.BelongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/my-club")
public class MyClubController {
    @Resource
    BelongService belongService;

    //查看加入的社团列表
    @GetMapping("/{userId}")
    public CommonResult getClubList(@PathVariable Integer userId) {
        List<MyClubDTO> myClub = belongService.GetMyClub(userId);
        if(myClub.isEmpty()!=true){
            return new CommonResult(200,"查询成功", myClub);
        }
        else{
            return new CommonResult(444,"无记录");
        }
    }

    @DeleteMapping
    public CommonResult quitFromClub(@RequestBody DeleteMyClubParam param) {
        try {
            LambdaQueryWrapper<BelongEntity> wrapper = new LambdaQueryWrapper<BelongEntity>()
                    .eq(BelongEntity::getUserId, param.getUserId())
                    .eq(BelongEntity::getClubId, param.getClubId());
            BelongEntity belongEntity = belongService.getOne(wrapper);
            if(belongEntity!=null){
                //如果该用户是社长
                if(belongEntity.getPermission() == 2){
                    return new CommonResult(400, "您是该社团社长，无法直接退出社团");
                }
                belongService.remove(wrapper);
                return new CommonResult(200,"删除成功");
            }
            else{
                return new CommonResult(444,"无记录");
            }
        }catch (Exception e){
            return new CommonResult(400, "系统出现错误", e);
        }
    }


//    @Resource
//    UserClubService userClubService;
//
//    //查看加入的社团列表
//    @GetMapping("/{userId}")
//    public CommonResult getClubList(@PathVariable Integer userId) {
//        List<MyClubDTO> myClub = userClubService.GetMyClub(userId);
//        if(myClub.isEmpty()!=true){
//            return new CommonResult(200,"查询成功", myClub);
//        }
//        else{
//            return new CommonResult(444,"无记录");
//        }
//    }
//
//
//    @DeleteMapping
//    public CommonResult quitFromClub(@RequestBody DeleteMyClubParam param) {
//        LambdaQueryWrapper<UserClubEntity> wrapper = new LambdaQueryWrapper<UserClubEntity>()
//                .eq(UserClubEntity::getUserId, param.getUserId())
//                .eq(UserClubEntity::getClubId, param.getClubId());
//        UserClubEntity userClubEntity = userClubService.getOne(wrapper);
//        if(userClubEntity!=null){
//            userClubService.remove(wrapper);
//            return new CommonResult(200,"删除成功");
//        }
//        else{
//            return new CommonResult(444,"无记录");
//        }
//    }
}
