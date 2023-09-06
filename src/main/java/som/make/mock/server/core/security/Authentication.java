package som.make.mock.server.core.security;

import java.util.List;

public class Authentication<A, B> {

    private A principal;
    private List<B> roles;

    private List<String> authorities;

    private boolean authenticated;

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

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}
