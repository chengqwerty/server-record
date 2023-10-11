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
        if (authentication != null && authentication.getPrincipal().getLoginName().equals("admin")) {
            // admin 作为超级管理员跳过鉴权这一步。
        } else if (!httpServletRequest.getMethod().equals(HttpMethod.OPTIONS.name())) {
            List<HttpSecurity.AuthorizedUrl> authorizedUrlList = httpSecurity.getAuthorizedUrlList();
            String servletPath = httpServletRequest.getServletPath();
            WebSecurityExpression webSecurityExpression = new WebSecurityExpression(authentication);
            for (HttpSecurity.AuthorizedUrl authorizedUrl: authorizedUrlList) {
                String pattern = authorizedUrl.getPathPattern(servletPath);
                if (pattern != null) {
                    if (!webSecurityExpression.verifyPermissions(pattern)) {
                        // 如果是没有登录，执行onAuthenticationFailure，如果是没有权限，执行onAuthorizationFailure
                        if (SecurityContextHolder.getContext().getAuthentication() == null) {
                            httpSecurity.getFailureHandler().onAuthenticationFailure((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
                        } else {
                            httpSecurity.getFailureHandler().onAuthorizationFailure((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
                        }
                    }
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
