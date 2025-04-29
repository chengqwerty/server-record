package som.make.mock.server.web.common;

import som.make.mock.server.web.system.entity.SysUser;

import java.time.LocalDateTime;

public interface EntityInfoSetter {

    String getCreateUser();
    void setCreateUser(String createUser);

    LocalDateTime getCreateTime();
    void setCreateTime(LocalDateTime createTime);

    String getUpdateUser();
    void setUpdateUser(String updateUser);

    LocalDateTime getUpdateTime();
    void setUpdateTime(LocalDateTime updateTime);

    // 默认方法，设置创建信息
    default void setCreationInfo(SysUser user) {
        if (getCreateTime() == null) {
            setCreateUser(user.getLoginName());
            LocalDateTime now = LocalDateTime.now();
            setCreateTime(now);
            setUpdateTime(now);
        }
    }

    // 默认方法，设置修改信息
    default void setUpdateInfo(SysUser user) {
        setUpdateUser(user.getLoginName());
        setUpdateTime(LocalDateTime.now());
    }

}
