package som.make.mock.server.config.filter.security.http;

import som.make.mock.server.core.security.Authentication;
import som.make.mock.server.web.system.bean.SysRole;
import som.make.mock.server.web.system.bean.SysUser;

import java.util.Set;

public class SecurityExpression {

    private final Authentication<SysUser, SysRole> authentication;

    private Set<String> roles;

    private String defaultRolePrefix = "ROLE_";

    public String getRoleWithDefaultPrefix(String prefix, String role) {
        return prefix + role;
    }

    public SecurityExpression(Authentication<SysUser, SysRole> authentication) {
        this.authentication = authentication;
    }

    public final boolean hasAuthority(String authority) {
        return this.hasAnyAuthority(authority);
    }

    public final boolean hasAnyAuthority(String... authorities) {
        return this.hasAnyAuthorityName((String)null, authorities);
    }

    public final boolean hasRole(String role) {
        return this.hasAnyRole(role);
    }

    public final boolean hasAnyRole(String... roles) {
        return this.hasAnyAuthorityName(this.defaultRolePrefix, roles);
    }

    private boolean hasAnyAuthorityName(String prefix, String... roles) {
        Set<String> roleSet = getAuthoritySet();
        for (String role : roles) {
            String defaultedRole = getRoleWithDefaultPrefix(prefix, role);
            if (roleSet.contains(defaultedRole)) {
                return true;
            }
        }
        return false;
    }

    private Set<String> getAuthoritySet() {
        // if (this.roles == null) {
        //     Collection<? extends GrantedAuthority> userAuthorities = this.authentication.getAuthorities();
        //     if (this.roleHierarchy != null) {
        //         userAuthorities = this.roleHierarchy.getReachableGrantedAuthorities(userAuthorities);
        //     }
        //     this.roles = AuthorityUtils.authorityListToSet(userAuthorities);
        // }
        return this.roles;
    }

}
