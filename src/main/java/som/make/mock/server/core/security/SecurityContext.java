package som.make.mock.server.core.security;

import som.make.mock.server.web.system.entity.SysRole;
import som.make.mock.server.web.system.entity.SysUser;

public class SecurityContext {

    public static SecurityContext createNoLoginUser() {
        SecurityContext securityContext = new SecurityContext();
        Authentication<SysUser, SysRole> authentication = new Authentication<>();
        authentication.setToken("noLoginUser");
        SysUser sysUser = new SysUser();
        sysUser.setLoginName("noLoginUser");
        authentication.setPrincipal(sysUser);
        securityContext.setAuthentication(authentication);
        return securityContext;
    }

    private SecurityContext() {
    }

    private Authentication<SysUser, SysRole> authentication;

    public Authentication<SysUser, SysRole> getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication<SysUser, SysRole> authentication) {
        this.authentication = authentication;
    }
}
