package som.make.mock.server.config.cache;

import org.springframework.data.redis.core.RedisTemplate;
import som.make.mock.server.core.security.Authentication;
import som.make.mock.server.web.system.entity.SysRole;
import som.make.mock.server.web.system.entity.SysUser;

public class RedisTokenCache implements TokenCache {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisTokenCache(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void putToken(String token, Authentication<SysUser, SysRole> authentication) {
        redisTemplate.opsForValue().set(token, authentication);
    }

    @Override
    public Authentication<SysUser, SysRole> getToken(String token) {
        return (Authentication<SysUser, SysRole>) redisTemplate.opsForValue().get(token);
    }

}
