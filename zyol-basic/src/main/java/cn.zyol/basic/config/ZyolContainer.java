package cn.zyol.basic.config;

import cn.zyol.basic.client.ClientFilter;
import cn.zyol.basic.client.ParamFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import service.AuthenticationRpcService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebFilter(urlPatterns = "/*")
@Component
@ConfigurationProperties(prefix = "zyolContainer")
public class ZyolContainer extends ParamFilter implements Filter {

    /**
     * 是否服务端，默认为false
     */
    private boolean isServer;

    private List<String> filterNames;

    private List<ClientFilter> filters;

    private PathMatcher pathMatcher = new AntPathMatcher();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (isServer) {
            ssoServerUrl = filterConfig.getServletContext().getContextPath();
        } else if (StringUtils.isEmpty(ssoServerUrl)) {
            throw new IllegalArgumentException("ssoServerUrl不能为空");
        }
        authenticationRpcService = (AuthenticationRpcService) ApplicationContextHelper.getBean("authenticationRpcService");
        if (authenticationRpcService == null) {
            throw new IllegalArgumentException("authenticationRpcService初始化失败");
        }
        if (filterNames == null || filterNames.size() == 0) {
            throw new IllegalArgumentException("filterNames不能为空");
        }
        filters = new LinkedList<>();
        for (String name : filterNames) {
            ClientFilter clientFilter = (ClientFilter) ApplicationContextHelper.getBean(name);
            if (clientFilter == null) {
                continue;
            }
            clientFilter.setSsoServerUrl(ssoServerUrl);
            clientFilter.setAuthenticationRpcService(authenticationRpcService);
            clientFilter.init(filterConfig);
            filters.add(clientFilter);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        for (ClientFilter filter : filters) {
            if (matchPath(filter.getPattern(), httpRequest.getServletPath())
                    && !filter.isAccessAllowed(httpRequest, httpResponse)) {
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        if (filters == null || filters.size() == 0) {
            return;
        }
        for (ClientFilter filter : filters) {
            filter.destroy();
        }
    }

    private boolean matchPath(String pattern, String path) {
        return StringUtils.isEmpty(pattern) || pathMatcher.match(pattern, path);
    }
}
