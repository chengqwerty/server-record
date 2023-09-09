package som.make.mock.server.config.cache;

import som.make.mock.server.core.security.Authentication;
import som.make.mock.server.web.system.entity.SysRole;
import som.make.mock.server.web.system.entity.SysUser;

public interface TokenCache {

    void putToken(String token, Authentication<SysUser, SysRole> authentication);

    Authentication<SysUser, SysRole> getToken(String token);
}
