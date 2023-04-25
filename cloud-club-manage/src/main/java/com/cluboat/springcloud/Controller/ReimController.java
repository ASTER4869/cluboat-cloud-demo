package com.cluboat.springcloud.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.*;
import com.cluboat.springcloud.entity.DTO.GetReimAttachDTO;
import com.cluboat.springcloud.entity.DTO.GetReimDTO;
import com.cluboat.springcloud.entity.param.CreateReimAttachParam;
import com.cluboat.springcloud.entity.param.CreateReimParam;
import com.cluboat.springcloud.entity.param.NotificationParam;
import com.cluboat.springcloud.entity.param.UpdateReimParam;
import com.cluboat.springcloud.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/reimbursements")
public class ReimController {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    ReimbursementService reimbursementService;

    @Resource
    ReimAttachService reimAttachService;


    @Resource
    ClubService clubService;
    @Resource
    BelongService belongService;

    @Resource
    UserInfoService userInfoService;

    @GetMapping("/{clubId}")
    public CommonResult GetReimListByClub(@PathVariable Integer clubId){
        try {
            LambdaQueryWrapper<ReimbursementsEntity> wrapper = new LambdaQueryWrapper<ReimbursementsEntity>()
                    .eq(ReimbursementsEntity::getClubId, clubId);
            List<ReimbursementsEntity> reimbursementsEntityList = reimbursementService.list(wrapper);
            if(reimbursementsEntityList == null || reimbursementsEntityList.isEmpty()){
                return new CommonResult(444, "无记录");
            }
            //将得到的数据映射到结果数据结构中
            List<GetReimDTO> reimDTOList = new ArrayList<>();
            for(ReimbursementsEntity reimbursementsEntity : reimbursementsEntityList){
                //先获取申请者名称和社团名称
                LambdaQueryWrapper<UserInfoEntity> userWrapper = new LambdaQueryWrapper<UserInfoEntity>()
                        .eq(UserInfoEntity::getUserId, reimbursementsEntity.getUserId());
                UserInfoEntity userInfoEntity  = userInfoService.getOne(userWrapper, false);
                LambdaQueryWrapper<ClubEntity> clubWrapper = new LambdaQueryWrapper<ClubEntity>()
                        .eq(ClubEntity::getClubId, reimbursementsEntity.getClubId());
                ClubEntity clubEntity  = clubService.getOne(clubWrapper, false);
                String userName = userInfoEntity.getUserName();
                String clubName = clubEntity.getClubName();
                //再获取报销的图片数组
                LambdaQueryWrapper<ReimAttachEntity> reimApplyWrapper = new LambdaQueryWrapper<ReimAttachEntity>()
                        .eq(ReimAttachEntity::getReimId, reimbursementsEntity.getReimId());
                List<ReimAttachEntity> reimAttachEntityList = reimAttachService.list(reimApplyWrapper);
                List<GetReimAttachDTO> reimAttachDTOList = new ArrayList<>();
                for(ReimAttachEntity reimAttachEntity : reimAttachEntityList){
                    GetReimAttachDTO reimAttachDTO = new GetReimAttachDTO();
                    reimAttachDTO.setAttachUrl(reimAttachEntity.getAttachUrl());
                    reimAttachDTOList.add(reimAttachDTO);
                }
                //将以上所有字段进行拼接
                GetReimDTO reimDTO = new GetReimDTO();
                reimDTO.setReimId(reimbursementsEntity.getReimId());
                reimDTO.setClubId(reimbursementsEntity.getClubId());
                reimDTO.setUserId(reimbursementsEntity.getUserId());
                reimDTO.setTitle(reimbursementsEntity.getTitle());
                reimDTO.setAmount(reimbursementsEntity.getAmount());
                reimDTO.setDescription(reimbursementsEntity.getDescription());
                reimDTO.setCreateTime(reimbursementsEntity.getCreateTime());
                reimDTO.setStatus(reimbursementsEntity.getStatus());
                reimDTO.setFeedback(reimbursementsEntity.getFeedback());
                //拼接url数组
                reimDTO.setAttachments(reimAttachDTOList);
                //拼接申请人名称和社团名称
                reimDTO.setUserName(userName);
                reimDTO.setClubName(clubName);

                reimDTOList.add(reimDTO);
            }
            return new CommonResult(200, "获取成功", reimDTOList);

        }catch (Exception e){
            return new CommonResult(400, "获取失败", e);
        }
    }

    @GetMapping()
    public CommonResult GetAllReimList(){
        try {
            List<ReimbursementsEntity> reimbursementsEntityList = reimbursementService.list();
            if(reimbursementsEntityList == null || reimbursementsEntityList.isEmpty()){
                return new CommonResult(444, "无记录");
            }
            //将得到的数据映射到结果数据结构中
            List<GetReimDTO> reimDTOList = new ArrayList<>();
            for(ReimbursementsEntity reimbursementsEntity : reimbursementsEntityList){
                //先获取申请者名称和社团名称
                LambdaQueryWrapper<UserInfoEntity> userWrapper = new LambdaQueryWrapper<UserInfoEntity>()
                        .eq(UserInfoEntity::getUserId, reimbursementsEntity.getUserId());
                UserInfoEntity userInfoEntity  = userInfoService.getOne(userWrapper, false);
                LambdaQueryWrapper<ClubEntity> clubWrapper = new LambdaQueryWrapper<ClubEntity>()
                        .eq(ClubEntity::getClubId, reimbursementsEntity.getClubId());
                ClubEntity clubEntity  = clubService.getOne(clubWrapper, false);
                String userName = userInfoEntity.getUserName();
                String clubName = clubEntity.getClubName();
                //再获取报销的图片数组
                LambdaQueryWrapper<ReimAttachEntity> reimApplyWrapper = new LambdaQueryWrapper<ReimAttachEntity>()
                        .eq(ReimAttachEntity::getReimId, reimbursementsEntity.getReimId());
                List<ReimAttachEntity> reimAttachEntityList = reimAttachService.list(reimApplyWrapper);
                List<GetReimAttachDTO> reimAttachDTOList = new ArrayList<>();
                for(ReimAttachEntity reimAttachEntity : reimAttachEntityList){
                    GetReimAttachDTO reimAttachDTO = new GetReimAttachDTO();
                    reimAttachDTO.setAttachUrl(reimAttachEntity.getAttachUrl());
                    reimAttachDTOList.add(reimAttachDTO);
                }
                //将以上所有字段进行拼接
                GetReimDTO reimDTO = new GetReimDTO();
                reimDTO.setReimId(reimbursementsEntity.getReimId());
                reimDTO.setClubId(reimbursementsEntity.getClubId());
                reimDTO.setUserId(reimbursementsEntity.getUserId());
                reimDTO.setTitle(reimbursementsEntity.getTitle());
                reimDTO.setAmount(reimbursementsEntity.getAmount());
                reimDTO.setDescription(reimbursementsEntity.getDescription());
                reimDTO.setCreateTime(reimbursementsEntity.getCreateTime());
                reimDTO.setStatus(reimbursementsEntity.getStatus());
                reimDTO.setFeedback(reimbursementsEntity.getFeedback());
                //拼接url数组
                reimDTO.setAttachments(reimAttachDTOList);
                //拼接申请人名称和社团名称
                reimDTO.setUserName(userName);
                reimDTO.setClubName(clubName);

                reimDTOList.add(reimDTO);
            }
            return new CommonResult(200, "获取成功", reimDTOList);

        }catch (Exception e){
            return new CommonResult(400, "获取失败", e);
        }
    }

    @PostMapping()
    public CommonResult CreateReim(@RequestBody CreateReimParam reimParam){
        try {
            //直接判断该用户是不是在该社团中
            LambdaQueryWrapper<BelongEntity> belongWrapper = new LambdaQueryWrapper<BelongEntity>()
                    .eq(BelongEntity::getUserId, reimParam.getUserId())
                    .eq(BelongEntity::getClubId, reimParam.getClubId());
            BelongEntity belongEntity = belongService.getOne(belongWrapper, false);
            if(belongEntity == null){
                return new CommonResult(444, "提交失败，该用户不在该社团中");
            }
            //先将该信息存入reim表中
            ReimbursementsEntity reimbursementsEntity = new ReimbursementsEntity();
            reimbursementsEntity.setClubId(reimParam.getClubId());
            reimbursementsEntity.setUserId(reimParam.getUserId());
            reimbursementsEntity.setAmount(reimParam.getAmount());
            reimbursementsEntity.setTitle(reimParam.getTitle());
            reimbursementsEntity.setDescription(reimParam.getDescription());
            reimbursementsEntity.setStatus("待审批");
            reimbursementsEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
            if(reimbursementService.save(reimbursementsEntity) == true){
                //再将url存入reimAttach表中
                List<CreateReimAttachParam> reimAttachParamList = reimParam.getAttachments();
                //如果没有url数组，则直接而返回
                if(reimAttachParamList == null){
                    return new CommonResult(200, "提交成功");
                }

                //获取最新的reim的id

                LambdaQueryWrapper<ReimbursementsEntity> getIdWrapper = new LambdaQueryWrapper<ReimbursementsEntity>()
                        .orderByDesc(ReimbursementsEntity::getReimId);
                ReimbursementsEntity tmp = reimbursementService.getOne(getIdWrapper, false);
                Integer reimId = tmp.getReimId();
                for (CreateReimAttachParam reimAttachParam : reimAttachParamList){
                    ReimAttachEntity reimAttachEntity = new ReimAttachEntity();
                    reimAttachEntity.setAttachUrl(reimAttachParam.getAttachUrl());
                    reimAttachEntity.setReimId(reimId);
                    reimAttachService.save(reimAttachEntity);
                }
            }

            return new CommonResult(200, "提交成功");
        }catch (Exception e){
            return new CommonResult(400, "提交失败", e);
        }
    }

    @PutMapping()
    public CommonResult UpdateReim(@RequestBody UpdateReimParam reimParam){
        try {
            //先判断该报销是否存在
            LambdaQueryWrapper<ReimbursementsEntity> reimWrapper = new LambdaQueryWrapper<ReimbursementsEntity>()
                    .eq(ReimbursementsEntity::getReimId, reimParam.getReimId());
            ReimbursementsEntity reimbursementsEntity = reimbursementService.getOne(reimWrapper, false);
            if (reimbursementsEntity == null){
                return new CommonResult(444, "操作失败，该报销不存在");
            }
            //判断目标状态是否合法
            List<String> status = Arrays.asList("待审批", "已通过", "已拒绝");
            if(status.indexOf(reimParam.getStatus()) < 0){
                return new CommonResult(400, "操作失败，目标状态非法");
            }

            //再判断状态是否与当前状态一致
            if (reimbursementsEntity.getStatus().equals(reimParam.getStatus())){
                return new CommonResult(400, "操作失败，该预算状态已经是" + reimParam.getStatus() + "，无需修改");
            }

            reimbursementsEntity.setStatus(reimParam.getStatus());
            reimbursementsEntity.setFeedback(reimParam.getFeedback());

            reimbursementService.saveOrUpdate(reimbursementsEntity);

            if(reimParam.getStatus().equals("已通过")){
                //系统向用户发通知
                NotificationParam notificationParam = new NotificationParam();
                notificationParam.setSenderType((byte)(2));
                notificationParam.setReceiverType((byte)(2));
                notificationParam.setReceiverId(reimbursementsEntity.getUserId());
                notificationParam.setNotificationTitle("报销申请成功");
                notificationParam.setNotificationContent("您申请的报销：" + reimbursementsEntity.getTitle() + "，已成功通过");
                CommonResult result = restTemplate.postForObject("http://cloud-examine-service/notification/", notificationParam, CommonResult.class);

                return new CommonResult(200, "已通过该申请");
            }
            else if(reimParam.getStatus().equals("已拒绝")){
                //系统向用户发通知
                NotificationParam notificationParam = new NotificationParam();
                notificationParam.setSenderType((byte)(2));
                notificationParam.setReceiverType((byte)(2));
                notificationParam.setReceiverId(reimbursementsEntity.getUserId());
                notificationParam.setNotificationTitle("报销申请失败");
                notificationParam.setNotificationContent("您申请的报销：" + reimbursementsEntity.getTitle() + "，审核不通过");
                return new CommonResult(200, "已驳回该申请");
            }
            return new CommonResult(200, "修改成功");


        }catch (Exception e){
            return new CommonResult(400, "操作失败", e);
        }
    }

}
