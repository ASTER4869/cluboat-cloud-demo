package com.cluboat.springcloud.Controller;

import com.alibaba.nacos.common.http.param.MediaType;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.entity.DTO.NotificationDTO;
import com.cluboat.springcloud.entity.NotReceiverEntity;
import com.cluboat.springcloud.entity.NotificationEntity;
import com.cluboat.springcloud.entity.param.GetNotificationParam;
import com.cluboat.springcloud.entity.param.NotificationParam;
import com.cluboat.springcloud.service.NotReceiverService;
import com.cluboat.springcloud.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
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

//    /* 根据社团id返回某社团所有新闻列表 */
//    @GetMapping("/{sendUserId}")
//    public CommonResult getAllClubNotificationById(@PathVariable("sendUserId") int id) {
//        List<NotificationDTO> notificationDTOList = notificationService.GetNotificationBySendUserId(id);
//        if (notificationDTOList.size() > 0) {
//            return new CommonResult(200, "查询成功",notificationDTOList);
//        } else {
//            return new CommonResult(400, "无记录");
//        }
//    }
//
//    /* 发布通知 */
//    @PostMapping
//    public CommonResult createNotification(@RequestBody NotificationParam notificationParam) {
//        NotificationEntity notification = new  NotificationEntity();
//        notificationParam.notificationTime = new Timestamp(System.currentTimeMillis());
//        notification.setNotification(notificationParam);
//        try {
//            notificationService.save(notification);
//            return new CommonResult(200, "修改成功");
//        } catch (Exception e) {
//            return new CommonResult(400, "修改失败", e);
//        }
//
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

    /* 发布通知 */
    @PostMapping
    public CommonResult createNotification(@RequestBody NotificationParam notificationParam) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.valueOf(MediaType.APPLICATION_JSON));
            HttpEntity<NotificationParam> entity = new HttpEntity<NotificationParam>(notificationParam, headers);
            CommonResult result = restTemplate.postForObject("http://cloud-examine-service/notification/", entity, CommonResult.class);
            return result;
        }catch (Exception e){
            return new CommonResult(400, "创建失败", e);
        }

    }


}
