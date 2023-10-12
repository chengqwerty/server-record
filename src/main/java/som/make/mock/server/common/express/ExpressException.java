package som.make.mock.server.common.express;

public class ExpressException extends Exception {

    private int code;

    public ExpressException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ExpressException(ExpressCode expressCode) {
        super(expressCode.getName());
        this.code = expressCode.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
