package som.make.mock.server.config.cache;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class TokenCacheConfig {

    @Bean("tokenCache")
    @ConditionalOnBean(type = "org.springframework.data.redis.core.RedisTemplate")
    public TokenCache redisTokenCache(RedisTemplate<String, Object> redisTemplate) {
        System.out.println("This is redisTokenCache");
        return new RedisTokenCache(redisTemplate);
    }

    @Bean("tokenCache")
    @ConditionalOnClass(name = "com.google.common.cache.Cache")
    @ConditionalOnMissingBean(TokenCache.class)
    public TokenCache guavaTokenCache() {
        System.out.println("This is guavaTokenCache");
        return new GuavaTokenCache();
    }

}
