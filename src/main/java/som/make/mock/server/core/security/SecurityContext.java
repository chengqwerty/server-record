package som.make.mock.server.core.security;

import som.make.mock.server.web.system.bean.SysRole;
import som.make.mock.server.web.system.bean.SysUser;

public class SecurityContext {

    private Authentication<SysUser, SysRole> authentication;

    public Authentication<SysUser, SysRole> getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication<SysUser, SysRole> authentication) {
        this.authentication = authentication;
    }
}
