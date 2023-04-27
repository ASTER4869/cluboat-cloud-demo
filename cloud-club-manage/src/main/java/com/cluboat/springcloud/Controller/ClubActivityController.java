package com.cluboat.springcloud.Controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ActivityEntity;
import com.cluboat.springcloud.entity.DTO.GetActivityDTO;
import com.cluboat.springcloud.entity.DTO.GetActivityRoughDTO;
import com.cluboat.springcloud.entity.UserInfoEntity;
import com.cluboat.springcloud.entity.param.ActivityChangeParam;
import com.cluboat.springcloud.entity.param.ActivityParam;
import com.cluboat.springcloud.service.ClubActivityService;
import com.cluboat.springcloud.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/activity")

public class ClubActivityController {
    @Resource
    UserInfoService userInfoService;
    @Resource
    private ClubActivityService clubActivityService;

    /* 获取本社团所有活动 */
    @GetMapping("/{clubId}")
    public CommonResult getClubActivityById(@PathVariable("clubId") int id) {
        try {
            List<ActivityEntity> activityEntityList = clubActivityService.lambdaQuery()
                    .eq(ActivityEntity::getClubId, id).list();

            if (activityEntityList.size() > 0) {
                List<GetActivityRoughDTO> activityRoughDTOList = new ArrayList<>();
                for (ActivityEntity activityEntity: activityEntityList){
                    GetActivityRoughDTO activityRoughDTO = new GetActivityRoughDTO();
                    activityRoughDTO.setActivityId(activityEntity.getActivityId());
                    activityRoughDTO.setActivityTitle(activityEntity.getActivityTitle());
                    activityRoughDTO.setActivityDate(activityEntity.getActivityDate());
                    activityRoughDTO.setActivityCampus(activityEntity.getActivityCampus());
                    activityRoughDTO.setActivityDescription(activityEntity.getActivityDescription());

                    activityRoughDTOList.add(activityRoughDTO);
                }
                return new CommonResult(200, "查询成功", activityRoughDTOList);
            } else {
                return new CommonResult(400, "无记录");
            }
        }catch (Exception e){
            return new CommonResult(400, "系统出现错误", e);
        }
    }
    @GetMapping("/detail/{activityId}")
    public CommonResult getActivityById(@PathVariable("activityId") int activityId) {
        try {
            ActivityEntity activityEntity = clubActivityService.lambdaQuery()
                    .eq(ActivityEntity::getActivityId, activityId).one();

            if (activityEntity != null) {
                //获取申请人名称
                UserInfoEntity userInfoEntity = userInfoService.getById(activityEntity.getUserId());
                String userName = userInfoEntity.getUserName();
                GetActivityDTO activityDTO = new GetActivityDTO();
                activityDTO.setActivityId(activityEntity.getActivityId());
                activityDTO.setUserId(activityEntity.getUserId());
                activityDTO.setClubId(activityEntity.getClubId());
                activityDTO.setActivityTitle(activityEntity.getActivityTitle());
                activityDTO.setActivityDate(activityEntity.getActivityDate());
                activityDTO.setActivityTime(activityEntity.getActivityTime());
                activityDTO.setActivityCampus(activityEntity.getActivityCampus());
                activityDTO.setActivityLocation(activityEntity.getActivityLocation());
                activityDTO.setActivityPeopleNum(activityEntity.getActivityPeopleNum());
                activityDTO.setActivityDescription(activityEntity.getActivityDescription());

                activityDTO.setUserName(userName);
                return new CommonResult(200, "查询成功", activityDTO);
            } else {
                return new CommonResult(400, "无记录");
            }
        }catch (Exception e){
            return new CommonResult(400, "系统出现错误", e);
        }
    }

    /* 删除活动 */
    @DeleteMapping("/{activityId}")
    public CommonResult removeActivityById(@PathVariable("activityId") int activityId) {
        boolean isSuccess = clubActivityService.removeById(activityId);
        if (isSuccess) {
            return new CommonResult(200, "删除成功");
        } else {
            return new CommonResult(400, "删除失败");
        }
    }

//
//    //* 发布某一活动 */
//    @PostMapping
//    public CommonResult PostActivity(@RequestBody ActivityParam activityParam) {
//        ActivityEntity activity = new ActivityEntity();
//        activity.setActivity(activityParam);
//        activity.setActivityIsPass((byte) 1);
//
//        if (clubActivityService.save(activity)) {
//            return new CommonResult(200, "更新成功");
//        } else {
//            return new CommonResult(400, "更新失败");
//        }
//    }



//    //* 改某一活动 */
//    @PutMapping
//    public CommonResult updateActivity(@RequestBody ActivityChangeParam activityChangeParam) {
//        ActivityEntity activity = clubActivityService.getById(activityChangeParam.activityId);
//        ActivityParam activityParam = new ActivityParam();
//        activityParam.clubId = activityChangeParam.clubId;
//        activityParam.activityArea = activityChangeParam.activityArea;
//        activityParam.activityContent = activityChangeParam.activityContent;
//        activityParam.activityName = activityChangeParam.activityName;
//        activityParam.activityStartTime = activityChangeParam.activityStartTime;
//        activityParam.activityEndTime = activityChangeParam.activityEndTime;
//        activity.setActivity(activityParam);
//        //activity.setActivityIsPass((byte) 1);  //重新送审
//
//        if (clubActivityService.updateById(activity)) {
//            return new CommonResult(200, "更新成功");
//        } else {
//            return new CommonResult(400, "更新失败");
//        }
//    }
}


