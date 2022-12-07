package com.cluboat.springcloud.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.apply.ClubCancelApplyEntity;
import com.cluboat.springcloud.mapper.ClubCancelApplyMapper;
import com.cluboat.springcloud.service.ClubCancelApplyService;
import org.springframework.stereotype.Service;

@Service
public class ClubCancelApplyServiceImpl extends ServiceImpl<ClubCancelApplyMapper, ClubCancelApplyEntity> implements ClubCancelApplyService {
}
