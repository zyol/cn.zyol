package cn.zyol.sso.service.impl;

import cn.zyol.basic.service.impl.BaseServiceImpl;
import cn.zyol.sso.bean.SysUser;
import cn.zyol.sso.dao.SysUserMapper;
import cn.zyol.sso.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


}
