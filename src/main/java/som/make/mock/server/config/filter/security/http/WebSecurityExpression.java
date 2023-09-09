package som.make.mock.server.config.filter.security.http;

import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import som.make.mock.server.core.security.Authentication;
import som.make.mock.server.web.system.entity.SysRole;
import som.make.mock.server.web.system.entity.SysUser;

public class WebSecurityExpression {

    private final SecurityExpression securityExpression;
    private final StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
    private final SpelExpressionParser spelExpressionParser = new SpelExpressionParser();

    public WebSecurityExpression(Authentication<SysUser, SysRole> authentication) {
        this.securityExpression = new SecurityExpression(authentication);
        standardEvaluationContext.setRootObject(this.securityExpression);
        Object value = spelExpressionParser.parseExpression("hasRole('abc')").getValue(this.securityExpression);
        System.out.println(value);
    }
}
