package som.make.mock.server.config.filter.security.http;

import som.make.mock.server.core.security.Authentication;
import som.make.mock.server.web.system.entity.SysRole;
import som.make.mock.server.web.system.entity.SysUser;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SecurityExpression {

    private final Authentication<SysUser, SysRole> authentication;

    private Set<String> authoritySet;

    private final String defaultRolePrefix = "ROLE_";

    public String getRoleWithDefaultPrefix(String prefix, String role) {
        return prefix + role;
    }

    public SecurityExpression(Authentication<SysUser, SysRole> authentication) {
        this.authentication = authentication;
    }

    public final boolean permitAll() {
        return true;
    }

    public final boolean denyAll() {
        return false;
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
        Set<String> authoritySet = getAuthoritySet();
        for (String role : roles) {
            String defaultedRole = getRoleWithDefaultPrefix(prefix, role);
            if (authoritySet.contains(defaultedRole)) {
                return true;
            }
        }
        return false;
    }

    private Set<String> getAuthoritySet() {
        if (this.authoritySet == null) {
            this.authoritySet = new HashSet<>();
            if (this.authentication != null) {
                this.authoritySet.addAll(this.authentication.getRoles().stream().map(sysRole -> getRoleWithDefaultPrefix(this.defaultRolePrefix,
                        sysRole.getRoleCode())).collect(Collectors.toSet()));
                this.authoritySet.addAll(this.authentication.getAuthorities());
            }
        }
        return this.authoritySet;
    }

}
