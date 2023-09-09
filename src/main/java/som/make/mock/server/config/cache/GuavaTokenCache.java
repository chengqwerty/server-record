package som.make.mock.server.config.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import som.make.mock.server.core.security.Authentication;
import som.make.mock.server.web.system.entity.SysRole;
import som.make.mock.server.web.system.entity.SysUser;

import javax.annotation.Nonnull;
import java.util.concurrent.TimeUnit;

public class GuavaTokenCache implements TokenCache {

    Cache<String, Authentication<SysUser, SysRole>> cache = CacheBuilder.newBuilder().maximumSize(999).expireAfterAccess(24, TimeUnit.HOURS).build();

    @Override
    public void putToken(@Nonnull String token, @Nonnull Authentication<SysUser, SysRole> authentication) {
        cache.put(token, authentication);
    }

    @Override
    public Authentication<SysUser, SysRole> getToken(String token) {
        return cache.getIfPresent(token);
    }

}
