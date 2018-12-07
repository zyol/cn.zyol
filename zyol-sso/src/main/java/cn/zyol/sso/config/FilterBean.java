package cn.zyol.sso.config;

import cn.zyol.basic.client.LogoutFilter;
import cn.zyol.basic.client.SsoFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterBean {

    @Bean(name = "ssoFilter")
    public SsoFilter generateSsoFilter() {
        return new SsoFilter();
    }

    @Bean(name = "logoutFilter")
    public LogoutFilter generateLogoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setSsoBackUrl("/index");
        logoutFilter.setPattern("/logout");
        return logoutFilter;
    }
}
