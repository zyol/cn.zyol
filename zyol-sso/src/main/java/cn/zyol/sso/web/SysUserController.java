package cn.zyol.sso.web;

import cn.zyol.sso.constants.SsoDubboConstants;
import cn.zyol.sso.service.AuthenticationRpcService;
import cn.zyol.sso.service.SysUserService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    @Reference(group = SsoDubboConstants.SSO_DUBBO_GROUP, version = SsoDubboConstants.SSO_DUBBO_VERSION)
    private AuthenticationRpcService authenticationRpcService;

    @RequestMapping("/report/shutdownreportlist")
    public String save() {
        sysUserService.in();
       return null;
    }


    @RequestMapping("/report/list")
    public void list() {
        PageHelper.startPage(1, 3);
        sysUserService.selectAll();
    }

}
