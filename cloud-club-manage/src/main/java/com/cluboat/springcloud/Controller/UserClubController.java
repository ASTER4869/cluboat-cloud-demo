package com.cluboat.springcloud.Controller;

import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.entity.BelongEntity;
import com.cluboat.springcloud.entity.param.BelongParam;
import com.cluboat.springcloud.service.BelongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@Slf4j
@RequestMapping("/my-club")
public class UserClubController {
    @Resource
    private BelongService belongService;

    /* 退出社团 */
    @DeleteMapping()
    public CommonResult removeUserClub(@RequestBody BelongParam belongParam) {
        boolean isSuccess =   belongService.lambdaUpdate()
                .eq(BelongEntity::getClubId,belongParam.clubId)
                .eq(BelongEntity::getUserId,belongParam.userId)
                .remove();
        if (isSuccess) {
            return new CommonResult(200, "删除成功");
        } else {
            return new CommonResult(400, "删除失败");
        }
    }
}
