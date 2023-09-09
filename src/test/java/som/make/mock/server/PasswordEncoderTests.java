package som.make.mock.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

@SpringBootTest
public class PasswordEncoderTests {

    @Test
    public void test1() {
        Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder(32, 64, 1,15*1024,2);
        String encode = argon2PasswordEncoder.encode("1T,)FaE1^W}9R]RLX6U)");
        System.out.println(encode);
        System.out.println(argon2PasswordEncoder.matches("1T,)FaE1^W}9R]RLX6U)", encode));
    }
}
