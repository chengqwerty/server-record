package som.make.mock.server.common.express;

public enum ExpressCode {

    REPEAT("重复", 50001);

    private String name;
    private int code;

    ExpressCode(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
