package cn.zyol.sso.filter;

import cn.zyol.sso.constants.SsoDubboConstants;
import cn.zyol.sso.service.AuthenticationRpcService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * 参数注入Filter
 */
@PropertySource(value = "classpath:/sso.properties")
public class ParamFilter {

    /**
     * 单点登录服务端URL
     */
    @Value("sso.server.url")
    protected String ssoServerUrl;

    /**
     * 单点登录服务端提供的RPC服务
     */
    @Reference(group = SsoDubboConstants.SSO_DUBBO_GROUP, version = SsoDubboConstants.SSO_DUBBO_VERSION)
    protected AuthenticationRpcService authenticationRpcService;

    public void setSsoServerUrl(String ssoServerUrl) {
        this.ssoServerUrl = ssoServerUrl;
    }

    public void setAuthenticationRpcService(AuthenticationRpcService authenticationRpcService) {
        this.authenticationRpcService = authenticationRpcService;
    }

    public AuthenticationRpcService getAuthenticationRpcService() {

//        if(authenticationRpcService == null){
//            authenticationRpcService = DubboFactory.getDubboService(AuthenticationRpcService.class, "zookeeper://121.0.0.1:12181", SsoDubboConstants.SSO_DUBBO_APPLICATION_NAME, SsoDubboConstants.SSO_DUBBO_VERSION);
//        }

        return authenticationRpcService;
    }
}