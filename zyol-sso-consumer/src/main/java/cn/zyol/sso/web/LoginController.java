package cn.zyol.sso.web;//package cn.zyol.sso.cn.zyol.sso.web;
//
//import cn.zyol.basic.util.CookieUtils;
//import cn.zyol.basic.util.StringUtil;
//import cn.zyol.basic.util.UUIDUtils;
//import cn.zyol.sso.bean.LoginUser;
//import cn.zyol.sso.bean.SysUser;
//import cn.zyol.sso.constants.TokenManager;
//import cn.zyol.sso.filter.SsoFilter;
//import cn.zyol.sso.service.SysUserService;
//import cn.zyol.sso.utils.CaptchaHelper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.cn.zyol.sso.web.bind.annotation.RequestBody;
//import org.springframework.cn.zyol.sso.web.bind.annotation.RequestMapping;
//import org.springframework.cn.zyol.sso.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.UnsupportedEncodingException;
//import java.net.URLDecoder;
//
///**
// * @author Joe
// */
//@Controller
//@RequestMapping("/login")
//public class LoginController {
//
//    // 登录页
//    private static final String LOGIN_PATH = "/login";
//
//    @Autowired
//    private SysUserService sysUserService;
//
//    @RequestMapping(method = RequestMethod.GET)
//    @RequestBody
//    public String login(String backUrl, HttpServletRequest request) {
//        String token = CookieUtils.getCookie(request, TokenManager.TOKEN);
//        if (StringUtil.isNotBlank(token)) {
//            return "redirect:" + authBackUrl(backUrl, token);
//        } else {
//            return goLoginPath(backUrl, request);
//        }
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public String login(
//            String backUrl,
//            String account,
//            String password,
//            String captcha,
//            HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
//        if (!CaptchaHelper.validate(request, captcha)) {
//            request.setAttribute("errorMessage", "验证码不正确");
//            return goLoginPath(backUrl, request);
//        }
////		Result result = sysUserService.login(getIpAddr(request), account, PasswordProvider.encrypt(password));
////		if (!result.getCode().equals(ResultCode.SUCCESS)) {
////			request.setAttribute("errorMessage", result.getMessage());
////			return goLoginPath(backUrl, request);
////		}
////		else {
//        SysUser user = sysUserService.selectByPrimaryKey("1");
//        LoginUser loginUser = new LoginUser(user.getId(), user.getLogInName());
//        String token = CookieUtils.getCookie(request, TokenManager.TOKEN);
//        if (StringUtil.isBlank(token) ) {
//            // 没有登录的情况
//            token = createToken(loginUser);
//            addTokenInCookie(token, request, response);
//        }
//
//        // 跳转到原请求
//        backUrl = URLDecoder.decode(backUrl, "utf-8");
//        return "redirect:" + authBackUrl(backUrl, token);
//    }
////	}
//
//    private String goLoginPath(String backUrl, HttpServletRequest request) {
//        request.setAttribute("backUrl", backUrl);
//        return LOGIN_PATH;
//    }
//
//    private String authBackUrl(String backUrl, String token) {
//        StringBuilder sbf = new StringBuilder(backUrl);
//        if (backUrl.indexOf("?") > 0) {
//            sbf.append("&");
//        } else {
//            sbf.append("?");
//        }
//        sbf.append(SsoFilter.SSO_TOKEN_NAME).append("=").append(token);
//        return sbf.toString();
//    }
//
//    private String createToken(LoginUser loginUser) {
//        // 生成token
//        String token = UUIDUtils.serialNoGenerator();
//
////        // 缓存中添加token对应User
////        tokenManager.addToken(token, loginUser);
//        return token;
//    }
//
//    private void addTokenInCookie(String token, HttpServletRequest request, HttpServletResponse response) {
//        // Cookie添加token
//        Cookie cookie = new Cookie(TokenManager.TOKEN, token);
//        cookie.setPath("/");
//        if ("https".equals(request.getScheme())) {
//            cookie.setSecure(true);
//        }
//        cookie.setHttpOnly(true);
//        response.addCookie(cookie);
//    }
//}