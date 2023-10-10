package som.make.mock.server.config.filter.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import som.make.mock.server.config.cache.TokenCache;
import som.make.mock.server.config.cache.TokenDetails;
import som.make.mock.server.config.filter.security.http.HttpSecurity;
import som.make.mock.server.core.security.Authentication;
import som.make.mock.server.core.security.SecurityContextHolder;
import som.make.mock.server.web.system.entity.SysRole;
import som.make.mock.server.web.system.entity.SysUser;

import java.io.IOException;

@Component
@Order(20)
public class AuthencationFilter implements Filter {

    private final TokenCache tokenCache;
    private final HttpSecurity httpSecurity;

    public AuthencationFilter(TokenCache tokenCache, WebSecurityConfig webSecurityConfig) {
        this.tokenCache = tokenCache;
        this.httpSecurity = webSecurityConfig.getHttpSecurity();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * 1、验证是否登录，如果登录获取Authentication，放入SecurityContextHolder
     * 2、如果没有登录，进入权限控制逻辑
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1、先删除之前的认证信息，验证是否登录，如果登录获取Authentication
        SecurityContextHolder.clearContext();
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = httpServletRequest.getHeader("Authorization-Token");
        if (token != null) {
            TokenDetails tokenDetail = tokenCache.getToken(token);
            if (tokenDetail != null) {
                Authentication<SysUser, SysRole> authentication = tokenDetail.getAuthentication();
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
