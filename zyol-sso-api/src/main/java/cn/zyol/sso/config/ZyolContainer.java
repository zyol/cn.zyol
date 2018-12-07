package cn.zyol.sso.config;


import cn.zyol.sso.filter.ClientFilter;
import cn.zyol.sso.filter.ParamFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
@WebFilter(urlPatterns = "/*")
@ConfigurationProperties(prefix = "zyolFilter")
//@PropertySource("classpath:ES/elasticsearch.properties")
public class ZyolContainer extends ParamFilter implements Filter {
    /**
     * 是否服务端，默认为false
     */
    private boolean isServer;

    private List<String> filterNames = new LinkedList<>();

    private List<ClientFilter> filters = new LinkedList<>();

    private PathMatcher pathMatcher = new AntPathMatcher();


    public List<String> getFilterNames() {
        return filterNames;
    }

    public void setFilterNames(List<String> filterNames) {
        this.filterNames = filterNames;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (filterNames != null && !filterNames.isEmpty()) {
            for (String name : filterNames) {
                ApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
                ClientFilter clientFilter = (ClientFilter) app.getBean(name);
                if (clientFilter == null) {
                    continue;
                }
                clientFilter.setSsoServerUrl(ssoServerUrl);
                clientFilter.setAuthenticationRpcService(authenticationRpcService);
                clientFilter.init(filterConfig);
                filters.add(clientFilter);
            }
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
