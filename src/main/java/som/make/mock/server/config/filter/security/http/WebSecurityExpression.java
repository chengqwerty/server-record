package som.make.mock.server.config.filter.security.http;

import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import som.make.mock.server.core.security.Authentication;
import som.make.mock.server.web.system.entity.SysRole;
import som.make.mock.server.web.system.entity.SysUser;

import java.util.ArrayList;
import java.util.HashSet;

public class WebSecurityExpression {

    private final StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
    private final SpelExpressionParser spelExpressionParser = new SpelExpressionParser();

    public WebSecurityExpression(Authentication<SysUser, SysRole> authentication) {
        SecurityExpression securityExpression = new SecurityExpression(authentication);
        standardEvaluationContext.setRootObject(securityExpression);
    }

    public boolean verifyPermissions(String pattern) {
        Object value = spelExpressionParser.parseExpression(pattern).getValue(standardEvaluationContext);
        if (value != null) {
            return (boolean) value;
        } else {
            return false;
        }
    }

}
