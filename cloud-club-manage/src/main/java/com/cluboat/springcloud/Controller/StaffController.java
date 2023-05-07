package com.cluboat.springcloud.Controller;

import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.entity.DTO.GetClubStaffDTO;
import com.cluboat.springcloud.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/club-staff")
public class StaffController {
    @Resource
    private UserInfoService userInfoService;

    /* 返回根据club_id找到的社团人列表 需要连表查询*/
    @GetMapping("/{clubId}")
    public CommonResult getAllClubStaffById(@PathVariable("clubId") int clubId ) {
        List<GetClubStaffDTO> userList = userInfoService.GetAllStaffByUserId(clubId);
        log.info("****插入结果：{payment}");
        if (userList.size() > 0) {
            return new CommonResult(200, "查询成功", userList);
        } else {
            return new CommonResult(400, "无记录");
        }
    }

}
