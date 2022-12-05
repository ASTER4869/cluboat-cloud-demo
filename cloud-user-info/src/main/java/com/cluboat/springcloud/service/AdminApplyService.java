package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.AdminApplyEntity;
import com.cluboat.springcloud.entity.dto.AdminApplyDTO;

public interface AdminApplyService extends IService<AdminApplyEntity> {
    public AdminApplyDTO GetAdminApply(Integer userId);
}
