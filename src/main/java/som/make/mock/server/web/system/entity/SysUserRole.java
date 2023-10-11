package som.make.mock.server.web.system.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "sys_user_role", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"}))
public class SysUserRole {

    @Id
    @Column(length = 64)
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Comment("主键")
    private String userRoleId;
    @Column(name = "user_id", length = 64)
    @Comment("userId")
    private String userId;
    @Column(name = "role_id", length = 64)
    @Comment("roleId")
    private String roleId;
    @Column
    @Comment("创建时间")
    private LocalDateTime createTime;
    @Column(length = 64)
    @Comment("创建人")
    private String createUser;
    @Column
    @Comment("修改时间")
    private LocalDateTime updateTime;
    @Column(length = 64)
    @Comment("修改人")
    private String updateUser;

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
