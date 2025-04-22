package som.make.mock.server.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler{

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put("error", "用户没有登录");
        response.getOutputStream().write(objectMapper.writeValueAsString(data).getBytes(StandardCharsets.ISO_8859_1));
        // response.getOutputStream().close();
    }

    @Override
    public void onAuthorizationFailure(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        Map<String, Object> data = new HashMap<>();
        data.put("error", "用户没有权限");
        response.getOutputStream().write(objectMapper.writeValueAsString(data).getBytes(StandardCharsets.ISO_8859_1));
        // response.getOutputStream().close();
    }
}
