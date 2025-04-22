package som.make.mock.server.config.filter.security.http;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;
import som.make.mock.server.core.security.AuthenticationFailureHandler;

import java.util.ArrayList;
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

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private List<AuthorizedUrl> authorizedUrlList = new ArrayList<>();
    public AuthorizedUrl unmappedMatchers;
    private AuthenticationFailureHandler failureHandler;

    public AuthenticationFailureHandler getFailureHandler() {
        return failureHandler;
    }

    public HttpSecurity setFailureHandler(AuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
        return this;
    }

    public List<AuthorizedUrl> getAuthorizedUrlList() {
        return authorizedUrlList;
    }

    public AuthorizedUrl antMatchers(String... antPatterns) {
        return antMatchers(null, antPatterns);
    }

    public AuthorizedUrl antMatchers(HttpMethod httpMethod, String... antPatterns) {
        String method = (httpMethod != null) ? httpMethod.toString() : null;
        this.unmappedMatchers = new AuthorizedUrl(method, antPatterns);
        authorizedUrlList.add(this.unmappedMatchers);
        return this.unmappedMatchers;
    }

    public class AuthorizedUrl {
        private final List<String> patternList;
        private String method;
        private String pattern;

        public AuthorizedUrl(String method, String... antPatterns) {
            this.patternList = Stream.of(antPatterns).collect(Collectors.toList());
            this.method = method;
        }

        public HttpSecurity permitAll() {
            return access(permitAll);
        }

        public HttpSecurity denyAll() {
            return access(denyAll);
        }

        public HttpSecurity access(String attribute) {
            this.pattern = attribute;
            return HttpSecurity.this;
        }

        public String getPathPattern(@NotNull String path) {
            if (this.patternList.stream().anyMatch(pattern -> antPathMatcher.match(pattern, path))) {
                return this.pattern;
            }
            return null;
        }

    }


}
