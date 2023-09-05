package som.make.mock.server.common.express;

public class ExpressException extends Exception {

    private int code;

    public ExpressException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
