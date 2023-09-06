package som.make.mock.server.config.filter.security.http;

import org.springframework.http.HttpMethod;
import som.make.mock.server.core.security.AuthenticationFailureHandler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HttpSecurity {

    static final String permitAll = "permitAll";

    private static final String denyAll = "denyAll";

    private static final String anonymous = "anonymous";

    private static final String authenticated = "authenticated";

    private static final String fullyAuthenticated = "fullyAuthenticated";

    private static final String rememberMe = "rememberMe";

    private List<AuthorizedUrl> authorizedUrlList;
    public AuthorizedUrl unmappedMatchers;
    private AuthenticationFailureHandler failureHandler;

    public HttpSecurity setFailureHandler(AuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
        return this;
    }

    public AuthorizedUrl antMatchers(String... antPatterns) {
        return antMatchers(null, antPatterns);
    }

    public AuthorizedUrl antMatchers(HttpMethod httpMethod, String... antPatterns) {
        String method = (httpMethod != null) ? httpMethod.toString() : null;
        this.unmappedMatchers = new AuthorizedUrl(method, antPatterns);
        return this.unmappedMatchers;
    }

    public class AuthorizedUrl {
        private List<String> patternList;
        private String method;
        private String pattern;

        public AuthorizedUrl(String method, String... antPatterns) {
            this.patternList = Stream.of(antPatterns).collect(Collectors.toList());
            this.method = method;
        }

        public HttpSecurity permitAll() {
            return access(permitAll);
        }

        public HttpSecurity access(String attribute) {
            this.pattern = permitAll;
            return HttpSecurity.this;
        }
    }


}
