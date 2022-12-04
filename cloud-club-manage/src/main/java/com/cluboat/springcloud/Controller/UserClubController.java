package com.cluboat.springcloud.Controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.UserClubEntity;
import com.cluboat.springcloud.entity.param.UserClubParam;
import com.cluboat.springcloud.service.UserClubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@Slf4j
@RequestMapping("/my-club")
public class UserClubController {
    @Resource
    private UserClubService userClubService;

    @DeleteMapping()
    public CommonResult removeUserClub(@RequestBody UserClubParam userClubParam) {
        boolean isSuccess =  userClubService.lambdaUpdate()
                .eq(UserClubEntity::getClubId,userClubParam.clubId)
                .eq(UserClubEntity::getUserId,userClubParam.userId)
                .remove();
        if (isSuccess) {
            return new CommonResult(200, "删除成功");
        } else {
            return new CommonResult(400, "删除失败");
        }
    }
}
