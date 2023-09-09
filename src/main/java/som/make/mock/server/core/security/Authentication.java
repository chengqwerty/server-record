package som.make.mock.server.core.security;

import java.util.List;
import java.util.Set;

public class Authentication<A, B> {

    private String token;
    private A principal;
    private List<B> roles;
    private Set<String> authorities;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public A getPrincipal() {
        return principal;
    }

    public void setPrincipal(A principal) {
        this.principal = principal;
    }

    public List<B> getRoles() {
        return roles;
    }

    public void setRoles(List<B> roles) {
        this.roles = roles;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

}
