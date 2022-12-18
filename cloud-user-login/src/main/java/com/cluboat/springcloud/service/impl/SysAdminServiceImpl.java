package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.SysAdminEntity;
import com.cluboat.springcloud.mapper.SysAdminMapper;
import com.cluboat.springcloud.service.SysAdminService;
import org.springframework.stereotype.Service;

@Service
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdminEntity> implements SysAdminService {
}
