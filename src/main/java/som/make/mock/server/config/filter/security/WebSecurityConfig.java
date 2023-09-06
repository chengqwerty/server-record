package som.make.mock.server.config.filter.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import som.make.mock.server.config.filter.security.http.HttpSecurity;

@Configuration
public class WebSecurityConfig implements InitializingBean {

    private final HttpSecurity httpSecurity = new HttpSecurity();

    public HttpSecurity getHttpSecurity() {
        return httpSecurity;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        httpSecurity.antMatchers("/login").permitAll();
    }

}
