package cn.zyol.sso.service.impl;

import bean.RpcPermission;
import bean.RpcUser;
import com.alibaba.dubbo.config.annotation.Service;
import constants.SsoDubboConstants;
import service.AuthenticationRpcService;

import java.util.ArrayList;
import java.util.List;

//@Component("authenticationRpcService")
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
