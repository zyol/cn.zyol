package cn.zyol.sso.service;

import cn.zyol.sso.constants.SsoDubboConstants;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class SysUserCService {
    @Reference(group = SsoDubboConstants.SSO_DUBBO_GROUP, version = SsoDubboConstants.SSO_DUBBO_VERSION)
    private UsersService usersService;

    @Reference(group = SsoDubboConstants.SSO_DUBBO_GROUP, version = SsoDubboConstants.SSO_DUBBO_VERSION)
    private SysUserService sysUserService;

    public void save(){
        usersService.save();
        sysUserService.save();
    }

}
