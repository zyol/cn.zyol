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


    @Override
    public void in() {
        for (int i = 0; i < 10; i++) {
            SysUser sysUser = new SysUser();
            sysUser.setId(i + "");
            sysUser.setLogInName(i + "");
            sysUser.setName(i + "");
            sysUser.setPassword(i + "");
            sysUserMapper.insert(sysUser);
        }
        sysUserMapper.deleteByIds("1,2,3,4,5");
       int i= 1/0;
    }
}
