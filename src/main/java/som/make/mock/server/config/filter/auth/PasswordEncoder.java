package som.make.mock.server.config.filter.auth;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    private final Argon2PasswordEncoder argon2PasswordEncoder;

    public PasswordEncoder() {
        this.argon2PasswordEncoder = new Argon2PasswordEncoder(32, 64, 1,15*1024,2);
    }

    public String encoder(String password) {
        return argon2PasswordEncoder.encode(password);
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return argon2PasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
