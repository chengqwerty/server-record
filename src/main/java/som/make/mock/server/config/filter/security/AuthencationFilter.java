package som.make.mock.server.config.filter.security;

import jakarta.servlet.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import som.make.mock.server.config.filter.security.http.HttpSecurity;
import som.make.mock.server.core.security.Authentication;
import som.make.mock.server.core.security.SecurityContextHolder;
import som.make.mock.server.web.system.bean.SysRole;
import som.make.mock.server.web.system.bean.SysUser;

import java.io.IOException;

@Component
@Order(20)
public class AuthencationFilter implements Filter {

    private final HttpSecurity httpSecurity;

    public AuthencationFilter(WebSecurityConfig webSecurityConfig) {
        this.httpSecurity = webSecurityConfig.getHttpSecurity();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * 1、验证是否登录，如果登录获取Authentication
     * 2、如果没有登录，进入权限控制逻辑
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1、验证是否登录，如果登录获取Authentication
        boolean authencated = true;
        Authentication<SysUser, SysRole> authentication = new Authentication<>();
        if (authencated) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
