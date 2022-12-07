package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.AdminApplyEntity;
import com.cluboat.springcloud.entity.dto.AdminApplyDTO;

import java.util.List;

public interface AdminApplyService extends IService<AdminApplyEntity> {
    public List<AdminApplyDTO> GetAdminApply(Integer userId);
}
