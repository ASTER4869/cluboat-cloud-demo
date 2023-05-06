package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.ReportEntity;
import com.cluboat.springcloud.mapper.ReportMapper;
import com.cluboat.springcloud.service.ReportService;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, ReportEntity> implements ReportService {
}
