package cn.zyol.sso.service.impl;

import cn.zyol.basic.service.impl.BaseServiceImpl;
import cn.zyol.sso.bean.Users;
import cn.zyol.sso.constants.SsoDubboConstants;
import cn.zyol.sso.dao.UsersMapper;
import cn.zyol.sso.service.UsersService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = UsersService.class, group = SsoDubboConstants.SSO_DUBBO_GROUP, version = SsoDubboConstants.SSO_DUBBO_VERSION)
public class UsersServiceImpl extends BaseServiceImpl<Users> implements UsersService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public void save() {
        for (int i = 0; i < 10; i++) {
            Users sysUser = new Users();
            sysUser.setId(i + "");
            sysUser.setUserSex(i + "");
            sysUser.setUserName(i + "");
            sysUser.setPassword(i + "");
            usersMapper.insert(sysUser);
        }
    }
}
