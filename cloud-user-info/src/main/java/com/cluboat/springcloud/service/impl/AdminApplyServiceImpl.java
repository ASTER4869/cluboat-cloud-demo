package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.AdminApplyEntity;
import com.cluboat.springcloud.entity.NotReceiverEntity;
import com.cluboat.springcloud.entity.NotificationEntity;
import com.cluboat.springcloud.entity.dto.AdminApplyDTO;
import com.cluboat.springcloud.entity.dto.NotificationDTO;
import com.cluboat.springcloud.mapper.AdminApplyMapper;
import com.cluboat.springcloud.service.AdminApplyService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminApplyServiceImpl extends ServiceImpl<AdminApplyMapper, AdminApplyEntity> implements AdminApplyService {
    @Resource
    AdminApplyMapper adminApplyMapper;

    @Override
    public List<AdminApplyDTO> GetAdminApply(Integer userId){
        List<AdminApplyDTO> adminApplyList = adminApplyMapper.selectJoinList(AdminApplyDTO.class, new MPJLambdaWrapper<AdminApplyEntity>()
                .selectAll(AdminApplyEntity.class)
                .eq(AdminApplyEntity::getUserId, userId));
        return adminApplyList;
    }
}
