package cn.zyol.basic.client;

import com.alibaba.dubbo.config.annotation.Reference;
import service.AuthenticationRpcService;

/**
 * 参数注入Filter
 *
 */
public class ParamFilter {

    /**
     * 单点登录服务端URL
     */
    protected String ssoServerUrl;

    /**
     * 单点登录服务端提供的RPC服务
     */
    @Reference()
    protected AuthenticationRpcService authenticationRpcService;

    public void setSsoServerUrl(String ssoServerUrl) {
        this.ssoServerUrl = ssoServerUrl;
    }

    public void setAuthenticationRpcService(AuthenticationRpcService authenticationRpcService) {
        this.authenticationRpcService = authenticationRpcService;
    }

    public AuthenticationRpcService getAuthenticationRpcService() {
        return authenticationRpcService;
    }
}