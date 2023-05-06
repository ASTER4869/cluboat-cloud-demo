package com.cluboat.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.CommentEntity;
import com.cluboat.springcloud.entity.PostEntity;
import com.cluboat.springcloud.entity.ReportEntity;
import com.cluboat.springcloud.entity.dto.ReportGetDetailDTO;
import com.cluboat.springcloud.entity.param.NotificationParam;
import com.cluboat.springcloud.entity.param.ReportAddParam;
import com.cluboat.springcloud.entity.param.ReportGetParam;
import com.cluboat.springcloud.entity.param.ReportUpdateParam;
import com.cluboat.springcloud.service.CommentService;
import com.cluboat.springcloud.service.PostService;
import com.cluboat.springcloud.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/report")
public class ReportController {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    ReportService reportService;

    @Resource
    PostService postService;

    @Resource
    CommentService commentService;

    //添加举报
    @PostMapping()
    public CommonResult AddReport(@RequestBody ReportAddParam reportAddParam){
        try {
            ReportEntity reportEntity = new ReportEntity();
            //因为举报者有可能为空值
            if (reportAddParam.getReporterId() != null){
                reportEntity.setReporterId(reportAddParam.getReporterId());
            }

            if(reportAddParam.getTargetType().equals("帖子")){
                if(postService.getById(reportAddParam.getTargetId()) == null){
                    return new CommonResult(400, "该帖子不存在");
                }
            }
            else if(reportAddParam.getTargetType().equals("评论")){
                if(commentService.getById(reportAddParam.getTargetId()) == null){
                    return new CommonResult(400, "该评论不存在");
                }
            }

            reportEntity.setReportReason(reportAddParam.getReportReason());
            reportEntity.setTargetId(reportAddParam.getTargetId());
            reportEntity.setReportTime(new Timestamp(System.currentTimeMillis()));
            reportEntity.setStatus("待审批");
            reportEntity.setTargetType(reportAddParam.getTargetType());
            //punish初始为NULL

            reportService.save(reportEntity);
            return new CommonResult(200, "举报添加成功");

        }catch (Exception e){
            return new CommonResult(400, "系统出现错误", e);
        }

    }

    //根据status和clubId获取举报列表
    @GetMapping()
    public CommonResult GetReportByClubId(@RequestBody(required = false) ReportGetParam reportGetParam){
        try {
            List<ReportEntity> reportEntityList;
            if(reportGetParam == null){
                reportEntityList = reportService.list();
            }
            else {
                LambdaQueryWrapper<ReportEntity> wrapper = new LambdaQueryWrapper<ReportEntity>();
                if (reportGetParam.getStatus() != null){
                    wrapper = wrapper.eq(ReportEntity::getStatus, reportGetParam.getStatus());
                }
                reportEntityList = reportService.list(wrapper);
                if (reportGetParam.getClubId() != null){
                    for (ReportEntity reportEntity : reportEntityList){
                        //如果该举报是针对帖子的
                        if (reportEntity.getTargetType().equals("帖子")){
                            Integer postId = reportEntity.getTargetId();
                            PostEntity postEntity = postService.getById(postId);
                            //如果该帖子不存在或者该帖子不是该社团的
                            if(postEntity == null || postEntity.getClubId() != reportGetParam.getClubId()){
                                reportEntityList.remove(reportEntity);
                            }
                        }
                        //如果该举报是针对评论的
                        else if (reportEntity.getTargetType().equals("评论")){
                            Integer commentId = reportEntity.getTargetId();
                            CommentEntity commentEntity = commentService.getById(commentId);
                            if(commentEntity != null){
                                Integer postId = commentEntity.getPostId();
                                PostEntity postEntity = postService.getById(postId);
                                //如果该帖子不存在或者该帖子不是该社团的
                                if(postEntity == null || postEntity.getClubId() != reportGetParam.getClubId()){
                                    reportEntityList.remove(reportEntity);
                                }
                            }
                            //如果该评论不存在
                            else {
                                reportEntityList.remove(reportEntity);
                            }
                        }
                    }
                }
            }
            return new CommonResult(200, "获取成功", reportEntityList);

        }catch (Exception e){
            return new CommonResult(400, "系统出现错误", e);
        }
    }

    //根据举报id获取举报对应帖子/评论的内容
    @GetMapping("/detail/{reportId}")
    private CommonResult GetReportContent(@PathVariable Integer reportId){
        try {
            ReportEntity reportEntity = reportService.getById(reportId);
            if (reportEntity == null){
                return new CommonResult(444, "该举报不存在");
            }
            ReportGetDetailDTO reportGetDetailDTO = new ReportGetDetailDTO();
            if(reportEntity.getTargetType().equals("帖子")){
                PostEntity post = postService.getById(reportEntity.getTargetId());
                if (post == null){
                    return new CommonResult(444, "该举报对应的帖子不存在");
                }
                reportGetDetailDTO.setReportContent(post.getPostTitle());
            }
            else if(reportEntity.getTargetType().equals("评论")){
                CommentEntity comment = commentService.getById(reportEntity.getTargetId());
                if(comment == null){
                    if (comment == null){
                        return new CommonResult(444, "该举报对应的评论不存在");
                    }
                }
                reportGetDetailDTO.setReportContent(comment.getCommentContent());
            }
            return new CommonResult(200, "获取成功", reportGetDetailDTO);

        }catch (Exception e){
            return new CommonResult(400, "系统出现错误", e);
        }
    }

    @PutMapping
    private CommonResult UpdateStatus(@RequestBody ReportUpdateParam reportUpdateParam){
        try {
            //先查看是否有该举报
            ReportEntity reportEntity = reportService.getById(reportUpdateParam.getReportId());
            if (reportEntity == null){
                return new CommonResult(444, "该举报不存在");
            }
            if (reportEntity.getStatus().equals("已审批")){
                return new CommonResult(400, "审批失败，该举报是已审批状态");
            }
            reportEntity.setStatus(reportUpdateParam.getStatus());
            reportEntity.setPunish(reportUpdateParam.getPunish());
            reportEntity.setFeedback(reportUpdateParam.getFeedback());
            reportService.updateById(reportEntity);


            Integer targetUserId;
            //获取被举报者的id
            PostEntity post = new PostEntity();
            CommentEntity comment = new CommentEntity();
            if (reportEntity.getTargetType().equals("帖子")){
                post = postService.getById(reportEntity.getTargetId());
                targetUserId = post.getUserId();
            }
            else {
                comment = commentService.getById(reportEntity.getTargetId());
                post = postService.getById(comment.getPostId());
                targetUserId = comment.getUserId();
            }

            //将审批信息发送给相关用户
            String notContent;
            //如果本举报是AI判别违规的举报，则不用向举报者发信息
            if(reportEntity.getReporterId() != null){
                //系统向用户发通知
                NotificationParam notificationParam = new NotificationParam();
                notificationParam.setSenderType((byte)(2));
                notificationParam.setReceiverType((byte)(2));
                notificationParam.setReceiverId(reportEntity.getReporterId());
                notificationParam.setNotificationTitle("举报审批信息通知");
                if (reportEntity.getTargetType().equals("帖子")){
                    notContent = "您于" + reportEntity.getReportTime() + "提交的对于帖子\"" + post.getPostTitle() +
                            "\"的举报已审批，管理员的反馈为\"" + reportEntity.getFeedback() + "\"，对该用户的惩罚为：\"" + reportEntity.getPunish() + "\"";
                }
                else {
                    notContent = "您于" + reportEntity.getReportTime() + "提交的对于评论\"" + comment.getCommentContent() +
                            "\"的举报已审批，管理员的反馈为\"" + reportEntity.getFeedback() + "\"，对该用户的惩罚为：\"" + reportEntity.getPunish() + "\"";
                }
                notificationParam.setNotificationContent(notContent);
                restTemplate.postForObject("http://cloud-examine-service/notification/", notificationParam, CommonResult.class);
            }

            NotificationParam notificationParam2 = new NotificationParam();
            notificationParam2.setSenderType((byte)(2));
            notificationParam2.setReceiverType((byte)(2));
            notificationParam2.setReceiverId(targetUserId);
            notificationParam2.setNotificationTitle("被举报审批信息通知");
            String notContent2;

            //如果该帖子/评论没问题
            if (reportEntity.getPunish() == null || reportEntity.getPunish().equals("无")){
                //如果是AI举报，则向被举报者发送审核通过信息
                if(reportEntity.getReporterId() == null){
                    if (reportEntity.getTargetType().equals("帖子")){
                        notContent2 = "您的帖子：\"" + post.getPostTitle() + "\"经管理员审核，判定为无违规，"
                                + "管理员反馈为：\"" + reportEntity.getFeedback() + "\"";
                        post.setStatus("正常");
                        postService.updateById(post);
                    }
                    else {
                        notContent2 = "您的评论：\"" + post.getPostTitle() + "\"经管理员审核，判定为无违规，"
                                + "管理员反馈为：\"" + reportEntity.getFeedback() + "\"";
                        comment.setStatus("正常");
                        commentService.updateById(comment);
                    }
                    notificationParam2.setNotificationContent(notContent2);
                    restTemplate.postForObject("http://cloud-examine-service/notification/", notificationParam2, CommonResult.class);
                }
                //否则被举报者理论上是不知道自己被举报了的
                return new CommonResult(200, "审批成功");
            }

            String status;
            if (reportEntity.getReporterId() == null){
                status = "系统判定违规";
            }
            else {
                status = "被用户举报";
            }

            //设置通知信息，并进行相应惩罚处理
            if (reportEntity.getTargetType().equals("帖子")){
                post.setStatus(status);
                postService.updateById(post);
                notContent2 = "您的帖子：\"" + post.getPostTitle() + "\"于 " + reportEntity.getReportTime() + " 被举报，"
                        + "管理员反馈为：\"" + reportEntity.getFeedback() + "\"，对您的惩罚为：\"" + reportEntity.getPunish() + "\"";
            }
            else {
                comment.setStatus(status);
                commentService.updateById(comment);
                notContent2 = "您在帖子：\"" + post.getPostTitle() + "\"下的评论：\"" + comment.getCommentContent() + "\"，于 " + reportEntity.getReportTime() + " 被举报，"
                        + "管理员反馈为：\"" + reportEntity.getFeedback() + "\"，对您的惩罚为：\"" + reportEntity.getPunish() + "\"";
            }
            notificationParam2.setNotificationContent(notContent2);
            restTemplate.postForObject("http://cloud-examine-service/notification/", notificationParam2, CommonResult.class);
            return new CommonResult(200, "审批成功");


        }catch (Exception e){
            return new CommonResult(400, "系统出现错误", e);
        }
    }


}
