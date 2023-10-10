package som.make.mock.server.core.security;

import org.springframework.util.Assert;

public class SecurityContextHolder {

    private static final ThreadLocal<SecurityContext> contextHolder = new ThreadLocal<>();

    public static void clearContext() {
        contextHolder.remove();
    }

    public static SecurityContext getContext() {
        SecurityContext ctx = contextHolder.get();
        if (ctx == null) {
            ctx = createEmptyContext();
            contextHolder.set(ctx);
        }
        return ctx;
    }

    public static void setContext(SecurityContext context) {
        Assert.notNull(context, "Only non-null SecurityContext instances are permitted");
        contextHolder.set(context);
    }

    public static SecurityContext createEmptyContext() {
        return new SecurityContext();
    }

}
