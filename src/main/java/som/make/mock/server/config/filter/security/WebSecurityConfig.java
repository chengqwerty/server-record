package som.make.mock.server.config.filter.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import som.make.mock.server.config.filter.security.http.HttpSecurity;
import som.make.mock.server.core.security.DefaultAuthenticationFailureHandler;

@Configuration
public class WebSecurityConfig implements InitializingBean {

    private final HttpSecurity httpSecurity = new HttpSecurity();

    public HttpSecurity getHttpSecurity() {
        return httpSecurity;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        httpSecurity.setFailureHandler(new DefaultAuthenticationFailureHandler())
                .antMatchers("/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/sys/**").access("hasRole('admin')");
    }

}
