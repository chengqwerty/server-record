package som.make.mock.server.config.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import javax.annotation.Nonnull;
import java.util.concurrent.TimeUnit;

public class GuavaTokenCache implements TokenCache {

    Cache<String, TokenDetails> cache = CacheBuilder.newBuilder().maximumSize(999).expireAfterAccess(24, TimeUnit.HOURS).build();

    @Override
    public void putToken(@Nonnull String token, @Nonnull TokenDetails tokenDetails) {
        cache.put(token, tokenDetails);
    }

    @Override
    public TokenDetails getToken(String token) {
        return cache.getIfPresent(token);
    }

}
