package som.make.mock.server.config.cache;

public interface TokenCache {

    void save(String token, TokenDetails tokenDetails);

    TokenDetails get(String token);

    void remove(String token);

}
