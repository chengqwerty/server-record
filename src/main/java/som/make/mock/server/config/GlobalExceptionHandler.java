package som.make.mock.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import som.make.mock.server.common.ResultBean;
import som.make.mock.server.common.express.ExpressCode;
import som.make.mock.server.common.express.ExpressException;
import som.make.mock.server.core.security.ForbiddenException;
import som.make.mock.server.core.security.UnauthorizedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<String> handleForbiddenException(ForbiddenException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(ExpressException.class)
    public ResponseEntity<ResultBean<String>> handleExpressException(ExpressException ex) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResultBean<>(ex.getCode(), ex.getMessage()));
    }

    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public ResponseEntity<ResultBean<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    //     return ResponseEntity.status(HttpStatus.OK).body(new ResultBean<>(ExpressCode.PARAMETERS_ERROR));
    // }
}