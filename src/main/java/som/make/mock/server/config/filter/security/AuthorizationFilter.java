package som.make.mock.server.config.filter.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import som.make.mock.server.config.filter.security.http.HttpSecurity;
import som.make.mock.server.config.filter.security.http.WebSecurityExpression;
import som.make.mock.server.core.security.Authentication;
import som.make.mock.server.core.security.SecurityContextHolder;
import som.make.mock.server.web.system.entity.SysRole;
import som.make.mock.server.web.system.entity.SysUser;

import java.io.IOException;
import java.util.List;

@Component
@Order(30)
public class AuthorizationFilter implements Filter {

    private final HttpSecurity httpSecurity;

    public AuthorizationFilter(WebSecurityConfig webSecurityConfig) {
        this.httpSecurity = webSecurityConfig.getHttpSecurity();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Authentication<SysUser, SysRole> authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().getLoginName().equals("admin")) {
            List<HttpSecurity.AuthorizedUrl> authorizedUrlList = httpSecurity.getAuthorizedUrlList();
            String servletPath = httpServletRequest.getServletPath();
            WebSecurityExpression webSecurityExpression = new WebSecurityExpression(authentication);
            /*
             * 针对所有的权限配置进行过滤，如果有一个配置通过鉴权就可以访问，如果都没通过鉴权就不可以访问。
             * 如果url没有匹配任何配置默认不通过。
             */
            boolean canAccess = false;
            for (HttpSecurity.AuthorizedUrl authorizedUrl: authorizedUrlList) {
                String pattern = authorizedUrl.getPathPattern(servletPath);
                if (pattern != null && webSecurityExpression.verifyPermissions(pattern)) {
                    canAccess = true;
                    break;
                }
            }
            if (!canAccess) {
                // 如果是没有登录，执行onAuthenticationFailure，如果是没有权限，执行onAuthorizationFailure
                if ("noLoginUser".equals(SecurityContextHolder.getContext().getAuthentication().getToken())) {
                    httpSecurity.getFailureHandler().onAuthenticationFailure((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
                } else {
                    httpSecurity.getFailureHandler().onAuthorizationFailure((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
                }
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
