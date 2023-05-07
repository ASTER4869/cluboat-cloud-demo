package com.cluboat.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cluboat.springcloud.common.CommonResult;

import com.cluboat.springcloud.entity.*;
import com.cluboat.springcloud.entity.dto.NotificationDTO;
import com.cluboat.springcloud.entity.param.GetNotificationParam;
import com.cluboat.springcloud.entity.param.NotificationParam;
import com.cluboat.springcloud.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/notification")
public class NotificationController {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private NotificationService notificationService;
    @Resource
    private NotReceiverService notReceiverService;
    @Resource
    private BelongService belongService;
    @Resource
    private SysAdminService sysAdminService;
    @Resource
    private UserService userService;


//    @GetMapping("/{id}")
//    public CommonResult getNotificationById(@PathVariable("id") int id) {
//
//
//        List<NotificationDTO> notificationList = notificationService.GetSendNotification(id);
//        if(notificationList.isEmpty()!=true){
//            return new CommonResult(200,"查询成功", notificationList);
//        }
//        else{
//            return new CommonResult(444,"无记录");
//        }
//
//        return result;
//    }

    public List<NotificationDTO> getSendNotification(GetNotificationParam notificationParam){

        List<NotificationDTO> notificationDTOList = new ArrayList<>();
        if(notificationParam.type == 0){
            LambdaQueryWrapper<NotificationEntity> wrapper = new LambdaQueryWrapper<NotificationEntity>()
                    .eq(NotificationEntity::getSendAdminId, notificationParam.id);
            //先找出所有notification
            List<NotificationEntity> notificationEntityList = notificationService.list(wrapper);

            //从NotReceiver表中获取对应的接收者
            for(NotificationEntity notificationEntity : notificationEntityList){
                LambdaQueryWrapper<NotReceiverEntity> receiverWrapper = new LambdaQueryWrapper<NotReceiverEntity>()
                        .eq(NotReceiverEntity::getNotificationId, notificationEntity.getNotificationId());
                List<NotReceiverEntity> notReceiverEntityList = notReceiverService.list(receiverWrapper);
                for(NotReceiverEntity notReceiverEntity : notReceiverEntityList){
                    NotificationDTO notificationDTO = new NotificationDTO();
                    notificationDTO.setNotificationId(notificationEntity.getNotificationId());
                    notificationDTO.setSendAdminId(notificationEntity.getSendAdminId());
                    notificationDTO.setNotificationContent(notificationEntity.getNotificationContent());
                    notificationDTO.setNotificationTime(notificationEntity.getNotificationTime());
                    notificationDTO.setSenderType(notificationEntity.getSenderType());
                    notificationDTO.setNotificationTitle(notificationEntity.getNotificationTitle());
                    notificationDTO.setReceiverId(notReceiverEntity.getReceiverId());
                    notificationDTOList.add(notificationDTO);
                }
            }
        }
        else if(notificationParam.type == 1){
            LambdaQueryWrapper<NotificationEntity> wrapper = new LambdaQueryWrapper<NotificationEntity>()
                    .eq(NotificationEntity::getSendUserId, notificationParam.id);
            //先找出所有notification
            List<NotificationEntity> notificationEntityList = notificationService.list(wrapper);

            //从NotReceiver表中获取对应的接收者
            for(NotificationEntity notificationEntity : notificationEntityList){
                LambdaQueryWrapper<NotReceiverEntity> receiverWrapper = new LambdaQueryWrapper<NotReceiverEntity>()
                        .eq(NotReceiverEntity::getNotificationId, notificationEntity.getNotificationId());
                List<NotReceiverEntity> notReceiverEntityList = notReceiverService.list(receiverWrapper);
                for(NotReceiverEntity notReceiverEntity : notReceiverEntityList){
                    NotificationDTO notificationDTO = new NotificationDTO();
                    notificationDTO.setNotificationId(notificationEntity.getNotificationId());
                    notificationDTO.setSendUserId(notificationEntity.getSendUserId());
                    notificationDTO.setNotificationContent(notificationEntity.getNotificationContent());
                    notificationDTO.setNotificationTime(notificationEntity.getNotificationTime());
                    notificationDTO.setSenderType(notificationEntity.getSenderType());
                    notificationDTO.setNotificationTitle(notificationEntity.getNotificationTitle());
                    notificationDTO.setReceiverId(notReceiverEntity.getReceiverId());
                    notificationDTOList.add(notificationDTO);
                }
            }
        }
        return notificationDTOList;
    }



    @GetMapping()
    public CommonResult getNotification(@RequestBody(required = false) GetNotificationParam notificationParam) {
        //如果无参，则返回全部
        if(notificationParam == null){
            CommonResult result = restTemplate.getForObject("http://cloud-user-info-service/notification/", CommonResult.class);
            return result;
        }

        try {
            if(notificationParam.id == null){
                return new CommonResult(400,"id为空");
            }
            //MyBatisPlus-Join的Mapper无法被识别，问题暂时无法解决
//            List<NotificationDTO> notificationList = notificationService.GetSendNotification(notificationParam);
//            List<NotificationDTO> notificationList = notReceiverService.GetSendNotification(notificationParam);
            List<NotificationDTO> notificationList = getSendNotification(notificationParam);
            if(notificationList.isEmpty()!=true){
                return new CommonResult(200,"查询成功", notificationList);
            }
            else{
                return new CommonResult(444,"无记录");
            }
        }catch (Exception e){
            return new CommonResult(400,"获取失败", e);
        }

    }


//    @GetMapping()
//    public CommonResult getAllNotification() {
////        List<NotificationEntity> notificationEntityList = notificationService.list();
////        log.info("****插入结果：{payment}");
////        if (!notificationEntityList.isEmpty()) {
////            return new CommonResult(200, "查询成功", notificationEntityList);
////        } else {
////            return new CommonResult(400, "无记录");
////        }
//
//        CommonResult result = restTemplate.getForObject("http://cloud-user-info-service/notification/", CommonResult.class);
//        return result;
//    }

    public boolean checkPermission(NotificationParam notificationParam, Integer type){
        //如果发起人是社团，需要检查发起人是否在该社团以及是否有管理员或以上的权限
        if(type == 0){
            LambdaQueryWrapper<Belong> wrapper = new LambdaQueryWrapper<Belong>()
                    .eq(Belong::getClubId, notificationParam.receiverId)
                    .eq(Belong::getUserId, notificationParam.sendUserId);
            Belong userBelong = belongService.getOne(wrapper);
            //如果是社员或者根本不在该社团中
            if(userBelong == null || userBelong.getPermission() == 0){
                return false;
            }
            return true;
        }
        //如果发起人是社联，需要检查其id是否为社联中的一员
        else if(type == 1){
            LambdaQueryWrapper<SysAdminEntity> wrapper = new LambdaQueryWrapper<SysAdminEntity>()
                    .eq(SysAdminEntity::getAdminId, notificationParam.sendAdminId);
            SysAdminEntity admin = sysAdminService.getOne(wrapper);
            //如果不是社联管理员
            if(admin == null){
                return false;
            }
            return true;
        }
        else if(type == 2){
            LambdaQueryWrapper<Belong> wrapper = new LambdaQueryWrapper<Belong>()
                    .eq(Belong::getPermission, 2)
                    .eq(Belong::getUserId, notificationParam.sendUserId);
            Belong userBelong = belongService.getOne(wrapper, false);
            //如果是社员或者根本不在该社团中
            if(userBelong == null || userBelong.getPermission() == 0){
                return false;
            }
            return true;
        }
        return true;

    }

    public CommonResult sendToClub(NotificationEntity notification, NotificationParam notificationParam, Integer type){
        try {
            if(checkPermission(notificationParam, type) == false){
                return new CommonResult(400, "发送失败，用户权限不足",null);
            }
            if(type == 0){
                notification.setSendUserId(notificationParam.sendUserId);
            }
            else if(type == 1){
                notification.setSendAdminId(notificationParam.sendAdminId);
            }
            //是系统则不设置userId或adminId
            LambdaQueryWrapper<Belong> wrapper2 = new LambdaQueryWrapper<Belong>()
                    .eq(Belong::getClubId, notificationParam.receiverId);
            List<Belong> belongList = belongService.list(wrapper2);
            notificationService.save(notification);
            //获取刚刚生成的not的id
            Integer notId = getNotId();

//            System.out.println(belongList);


            for (Belong belong : belongList)  {
                NotReceiverEntity notReceiverEntity = new NotReceiverEntity();
                notReceiverEntity.setNotificationId(notId);
                notReceiverEntity.setReceiverId(belong.getUserId());
                notReceiverService.save(notReceiverEntity);
            }
            return new CommonResult(200, "创建成功");
        }catch (Exception e){
            return new CommonResult(400, "发送失败",e);
        }

    }

    public CommonResult sendToAllUsers(NotificationEntity notification, NotificationParam notificationParam){
        try {
            LambdaQueryWrapper<SysAdminEntity> wrapper = new LambdaQueryWrapper<SysAdminEntity>()
                    .eq(SysAdminEntity::getAdminId, notificationParam.sendAdminId);
            SysAdminEntity admin = sysAdminService.getOne(wrapper);
            //首先判断是否是系统
            if (notificationParam.senderType != 2){
                //如果不是社联管理员
                if(admin == null){
                    return new CommonResult(400, "发送失败，用户权限不足",null);
                }
                notification.setSendAdminId(notificationParam.sendAdminId);
            }
            List<UserEntity> userList = userService.list();
            notificationService.save(notification);
            //获取刚刚生成的not的id
            Integer notId = getNotId();
            for (UserEntity user : userList)  {
                NotReceiverEntity notReceiverEntity = new NotReceiverEntity();
                notReceiverEntity.setNotificationId(notId);
                notReceiverEntity.setReceiverId(user.getUserId());
                notReceiverService.save(notReceiverEntity);
            }
            return new CommonResult(200, "创建成功");
        }catch (Exception e){
            return new CommonResult(400, "发送失败",e);
        }

    }

    public Integer getNotId(){
        LambdaQueryWrapper<NotificationEntity> wrapper = new LambdaQueryWrapper<NotificationEntity>()
                .orderByDesc(NotificationEntity::getNotificationId);
        NotificationEntity newNot = notificationService.getOne(wrapper, false);
        Integer notId = newNot.getNotificationId();
        if (notId == null){
            return -1;
        }
//        System.out.println("当前通知编号为:" + notId);
        return notId;
    }


    @PostMapping
    public CommonResult createNotification(@RequestBody NotificationParam notificationParam) {
        NotificationEntity notification = new NotificationEntity();
        System.out.println(notificationParam);

        notification.setNotificationTitle(notificationParam.notificationTitle);
        notification.setNotificationContent(notificationParam.notificationContent);
        notification.setNotificationTime(new Timestamp(System.currentTimeMillis()));
        notification.setSenderType(notificationParam.senderType);

        try {
            //如果发送信息的是社团
            if(notificationParam.senderType == 0){
                if(notificationParam.sendUserId == null){
                    return new CommonResult(400, "发送失败",null);
                }
                if(notificationParam.receiverType == 0){
                    return new CommonResult(400, "发送失败，社团管理员权限不足",null);
                }
                //如果是社团管理员向全体社团成员发消息
                else if(notificationParam.receiverType == 1){
                    return sendToClub(notification, notificationParam, 0);
                }
                else{
                    if(checkPermission(notificationParam, 2) == false){
                        return new CommonResult(400, "发送失败，用户权限不足",null);
                    }
                    else {
                        notification.setSendUserId(notificationParam.sendUserId);
                        notificationService.save(notification);
                        //获取刚刚生成的not的id
                        Integer notId = getNotId();
                        NotReceiverEntity notReceiverEntity = new NotReceiverEntity();
                        notReceiverEntity.setNotificationId(notId);
                        notReceiverEntity.setReceiverId(notificationParam.receiverId);
                        notReceiverService.save(notReceiverEntity);
                    }
                }
            }
            //如果是社联管理员
            else if(notificationParam.senderType == 1){
                if(notificationParam.sendAdminId == null){
                    return new CommonResult(400, "修改失败",null);
                }
                //如果是社联管理员向全体成员发消息
                if(notificationParam.receiverType == 0){
                    return sendToAllUsers(notification, notificationParam);
                }
                //如果是社联管理员向全体社团成员发消息
                else if(notificationParam.receiverType == 1){
                    return sendToClub(notification, notificationParam, 1);
                }
                else{
                    if(checkPermission(notificationParam, 1) == false){
                        return new CommonResult(400, "发送失败，用户权限不足",null);
                    }
                    else {
                        notification.setSendAdminId(notificationParam.sendAdminId);
                        notificationService.save(notification);
                        //获取刚刚生成的not的id
                        Integer notId = getNotId();
                        NotReceiverEntity notReceiverEntity = new NotReceiverEntity();
                        notReceiverEntity.setNotificationId(notId);
                        notReceiverEntity.setReceiverId(notificationParam.receiverId);
                        notReceiverService.save(notReceiverEntity);
                    }
                }
            }
            //如果是系统
            else if(notificationParam.senderType == 2){
                //如果是系统向全体成员发消息
                if(notificationParam.receiverType == 0){
                    return sendToAllUsers(notification, notificationParam);
                }
                //如果是系统管理员向全体社团成员发消息
                else if(notificationParam.receiverType == 1){
                    return sendToClub(notification, notificationParam, 2);
                }
                else{
                    notificationService.save(notification);
                    //获取刚刚生成的not的id
                    Integer notId = getNotId();
                    NotReceiverEntity notReceiverEntity = new NotReceiverEntity();
                    notReceiverEntity.setNotificationId(notId);
                    notReceiverEntity.setReceiverId(notificationParam.receiverId);
                    notReceiverService.save(notReceiverEntity);
                }
            }
            return new CommonResult(200, "创建成功");
        } catch (Exception e){
            return new CommonResult(400, "修改失败",e);
        }
    }


}
