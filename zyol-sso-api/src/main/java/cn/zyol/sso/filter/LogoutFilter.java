package cn.zyol.sso.filter;

import cn.zyol.sso.utils.SessionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 单点退出Filter
 */
public class LogoutFilter extends ClientFilter {

    /**
     * 单点退出成功后跳转页(配置当前应用上下文相对路径，不设置或为空表示项目根目录)
     */
    private String ssoBackUrl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (StringUtils.isEmpty(pattern)) {
            throw new IllegalArgumentException("pattern不能为空");
        }
        if (StringUtils.isEmpty(ssoBackUrl)) {
            throw new IllegalArgumentException("ssoBackUrl不能为空");
        }
    }

    @Override
    public boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        SessionUtils.invalidate(request);
        String logoutUrl = new StringBuilder(ssoServerUrl)
                .append("/logout?backUrl=").append(getLocalUrl(request)).append(ssoBackUrl).toString();
        response.sendRedirect(logoutUrl);
        return false;
    }

    /**
     * 获取当前上下文路径
     *
     * @param request
     * @return
     */
    private String getLocalUrl(HttpServletRequest request) {
        return new StringBuilder().append(request.getScheme()).append("://").append(request.getServerName()).append(":")
                .append(request.getServerPort() == 80 ? "" : request.getServerPort()).append(request.getContextPath())
                .toString();
    }

    public void setSsoBackUrl(String ssoBackUrl) {
        this.ssoBackUrl = ssoBackUrl;
    }
}