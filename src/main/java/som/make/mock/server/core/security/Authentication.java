package som.make.mock.server.core.security;

import java.util.HashSet;
import java.util.Set;

public class Authentication<A, B> {

    private String token;
    private A principal;
    private Set<B> roles = new HashSet<>();
    private Set<String> authorities = new HashSet<>();

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

    public Set<B> getRoles() {
        return roles;
    }

    public void setRoles(Set<B> roles) {
        this.roles = roles;
    }

    public void addAllRoles(Set<B> roles) {
        this.roles.addAll(roles);
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public void addAllAuthorities(Set<String> authorities) {
        this.authorities.addAll(authorities);
    }

}
