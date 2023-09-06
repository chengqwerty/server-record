package som.make.mock.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import som.make.mock.server.core.security.Authentication;
import som.make.mock.server.config.filter.security.http.WebSecurityExpression;

@SpringBootTest
public class WebSecurityExpressionTest {

    @Test
    public void test1() {
        WebSecurityExpression webSecurityExpression = new WebSecurityExpression(new Authentication<>());
    }
}
