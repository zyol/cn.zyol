package cn.zyol.sso.web;

import cn.zyol.sso.bean.SysUser;
import cn.zyol.sso.service.SysUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/report/shutdownreportlist")
    public void save(){
       for(int i = 0; i< 10 ; i++){
           SysUser sysUser = new SysUser();
           sysUser.setId(i+"");
           sysUser.setLogInName(i+"");
           sysUser.setName(i+"");
           sysUser.setPassword(i+"");
           sysUserService.insert(sysUser);
       }
    }


    @RequestMapping("/report/list")
    public void list(){
        PageHelper.startPage(1,3);
        sysUserService.selectAll();
    }

}
