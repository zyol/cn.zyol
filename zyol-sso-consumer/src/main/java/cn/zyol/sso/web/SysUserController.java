package cn.zyol.sso.web;

import cn.zyol.sso.constants.SsoDubboConstants;
import cn.zyol.sso.service.AuthenticationRpcService;
import cn.zyol.sso.service.SysUserCService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SysUserController {

    @Reference(group = SsoDubboConstants.SSO_DUBBO_GROUP, version = SsoDubboConstants.SSO_DUBBO_VERSION)
    private AuthenticationRpcService authenticationRpcService;

    @Autowired
    private SysUserCService sysUserCService;

    @RequestMapping("/report/shutdownreportlist")
    public String save() {
        sysUserCService.save();
        return null;
    }
}
