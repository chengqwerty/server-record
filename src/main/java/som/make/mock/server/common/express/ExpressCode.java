package som.make.mock.server.common.express;

/**
 * 自定义异常编码
 * 4000 系列是权限相关的异常
 */
public enum ExpressCode {

    REPEAT("重复", 50001),
    USER_NOT_EXISTS("用户不存在！", 4002),
    USER_LOGIN_ERROR("用户名或者密码错误！", 4003),
    UPDATE_NOT_EXIST("修改的数据不存在！", 4103);

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
