package som.make.mock.server.config.cache;

public interface TokenCache {

    void putToken(String token, TokenDetails tokenDetails);

    TokenDetails getToken(String token);
}
