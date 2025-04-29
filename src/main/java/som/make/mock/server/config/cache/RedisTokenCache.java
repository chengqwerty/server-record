package som.make.mock.server.config.cache;

import org.springframework.data.redis.core.RedisTemplate;

public class RedisTokenCache implements TokenCache {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisTokenCache(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(String token, TokenDetails tokenDetails) {
        redisTemplate.opsForValue().set(token, tokenDetails);
    }

    @Override
    public TokenDetails get(String token) {
        return (TokenDetails) redisTemplate.opsForValue().get(token);
    }

    @Override
    public void remove(String token) {
        redisTemplate.delete(token);
    }

}
