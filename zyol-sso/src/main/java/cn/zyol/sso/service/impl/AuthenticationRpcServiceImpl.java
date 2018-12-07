package cn.zyol.sso.service.impl;


import cn.zyol.sso.bean.RpcPermission;
import cn.zyol.sso.bean.RpcUser;
import cn.zyol.sso.constants.SsoDubboConstants;
import cn.zyol.sso.service.AuthenticationRpcService;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.List;

@Service(group = SsoDubboConstants.SSO_DUBBO_GROUP, version = SsoDubboConstants.SSO_DUBBO_VERSION)
public class AuthenticationRpcServiceImpl implements AuthenticationRpcService {


    @Override
    public boolean validate(String token) {
        return false;
    }

    @Override
    public RpcUser findAuthInfo(String token) {

        return null;
    }

    @Override
    public List<RpcPermission> findPermissionList(String token, String appCode) {
        return new ArrayList<RpcPermission>(0);
    }
}
