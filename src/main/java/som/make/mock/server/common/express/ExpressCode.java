package som.make.mock.server.common.express;

/**
 * 自定义异常编码
 * 40 系列是权限相关的异常
 * 41 系列是用户错误
 * 50 系列是服务器错误
 */
public enum ExpressCode {

    REPEAT("重复", 4101),
    COMMON_ERROR("通用错误，需要自己传入错误信息。", 4100),
    USER_NOT_EXISTS("用户不存在！", 4002),
    USER_LOGIN_ERROR("用户名或者密码错误！", 4003),
    UPDATE_NOT_EXIST("修改的数据不存在！", 4103),
    DELETE_NOT_EXIST("删除的数据不存在！", 4104),
    PARAMETERS_ERROR("参数错误！", 4105);

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
