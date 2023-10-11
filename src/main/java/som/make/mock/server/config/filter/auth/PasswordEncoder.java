package som.make.mock.server.config.filter.auth;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PasswordEncoder {

    private final Argon2PasswordEncoder argon2PasswordEncoder;
    private final Pattern pattern;
    private final String prefix;

    public PasswordEncoder() {
        this.argon2PasswordEncoder = new Argon2PasswordEncoder(32, 64, 1,15*1024,2);
        String test = this.argon2PasswordEncoder.encode("test");
        String ps = "^(\\$.*,p=\\d\\$)(.*)$";
        this.pattern = Pattern.compile(ps);
        Matcher m = this.pattern.matcher(encoder("test"));
        if (m.find( )) {
            this.prefix = m.group(1);
        } else {
            throw new RuntimeException("密码解析出错！");
        }
    }

    public String getNoPrefixEncoder(String password) {
        String encoder = encoder(password);
        Matcher m = this.pattern.matcher(encoder);
        if (m.find( )) {
            return m.group(2);
        } else {
            throw new RuntimeException("密码解析出错！");
        }
    }

    public String encoder(String password) {
        return argon2PasswordEncoder.encode(password);
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return argon2PasswordEncoder.matches(rawPassword, encodedPassword);
    }

    public boolean matchesNoPrefix(CharSequence rawPassword, String encodedPassword) {
        return argon2PasswordEncoder.matches(rawPassword, this.prefix + encodedPassword);
    }
}
